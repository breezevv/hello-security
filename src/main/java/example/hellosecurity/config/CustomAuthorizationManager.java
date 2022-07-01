package example.hellosecurity.config;

import example.hellosecurity.domain.Authority;
import example.hellosecurity.domain.CustomUserDetails;
import example.hellosecurity.domain.RoleAuthority;
import example.hellosecurity.domain.UserRole;
import example.hellosecurity.repository.AuthorityRepository;
import example.hellosecurity.repository.RoleAuthorityRepository;
import example.hellosecurity.repository.UserRoleRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Resource
    private UserRoleRepository userRoleRepository;

    @Resource
    private RoleAuthorityRepository roleAuthorityRepository;

    @Resource
    private AuthorityRepository authorityRepository;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.get().getPrincipal();
        // 通过用户 id 查询角色信息
        List<UserRole> userRoles = userRoleRepository.findUserRolesByUserId(userDetails.getId());
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        // 通过角色 id 查询权限信息
        List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findRoleAuthoritiesByRoleIdIn(roleIds);
        List<Long> authorityIds = roleAuthorities.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        List<Authority> authorities = authorityRepository.findAllById(authorityIds);
        HttpServletRequest request = requestAuthorizationContext.getRequest();
        boolean hasPermission = false;
        for (Authority authority : authorities) {
            AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(authority.getUrl(), authority.getMethod());
            if (antPathRequestMatcher.matches(request)) {
                hasPermission = true;
                break;
            }
        }
        if (hasPermission) {
            return new AuthorizationDecision(hasPermission);
        }
        throw new AccessDeniedException("没有权限访问！");
    }
}
