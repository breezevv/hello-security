package example.hellosecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CustomConfig {

    @Resource
    private IgnoreRequest ignoreRequest;

    @Bean
    public RequestMatcher[] requestMatchers() {
        List<RequestMatcher> requestMatchers = new ArrayList<>();
        ignoreRequest.getPost().forEach(path -> requestMatchers.add(new AntPathRequestMatcher(path, "POST")));
        ignoreRequest.getGet().forEach(path -> requestMatchers.add(new AntPathRequestMatcher(path, "GET")));
        return requestMatchers.toArray(new RequestMatcher[0]);
    }
}
