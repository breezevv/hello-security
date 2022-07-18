package example.hellosecurity.config;

import example.hellosecurity.filter.JWTAuthenticationFilter;
import example.hellosecurity.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.Resource;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Resource
    private CustomUserDetailService userDetailService;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("test").password("123456").roles("USER").build());
//        return manager;
//    }

    @Resource
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private CustomAuthorizationManager customAuthorizationManager;

    @Resource
    private RequestMatcher[] requestMatchers;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(requestMatchers).permitAll()
                        .anyRequest().access(customAuthorizationManager)
                )
                .formLogin().disable()
                .addFilterAfter(jwtAuthenticationFilter, ExceptionTranslationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("text/plain;charset=UTF-8");
                    response.getWriter().write(authException.getMessage());
                }).accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setContentType("text/plain;charset=UTF-8");
                    response.getWriter().write(accessDeniedException.getMessage());
                });
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }


}
