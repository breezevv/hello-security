package example.hellosecurity.filter;


import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import example.hellosecurity.domain.CustomUserDetails;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        if (StrUtil.isEmpty(token)) {
            throw new RuntimeException("令牌为空，请重新登录!");
        }
        if (!StrUtil.startWith(token, "Bearer ")) {
            throw new RuntimeException("令牌类型错误，请重新登录");
        }
        token = token.substring(7);
        JWTSigner jwtSigner = JWTSignerUtil.hs512("yingyingying".getBytes(StandardCharsets.UTF_8));
        if (!JWTUtil.verify(token, jwtSigner)) {
            throw new RuntimeException("令牌错误，请重新登录!");
        }
        JWT jwt = JWTUtil.parseToken(token);
        String username = (String) jwt.getPayload().getClaim("username");
        CustomUserDetails userDetails = (CustomUserDetails) redisTemplate.opsForHash().get("login-user", username);
        if (userDetails == null) {
            throw new RuntimeException("令牌超时， 请重新登录");
        }
        SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken.authenticated(userDetails, null, null));
        filterChain.doFilter(request, response);
    }
}