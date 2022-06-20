package example.hellosecurity.controller;

import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import example.hellosecurity.controller.params.LoginParams;
import example.hellosecurity.domain.CustomUserDetails;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestController
class LoginController {

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/login")
    public String login(@RequestBody LoginParams loginParams) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                UsernamePasswordAuthenticationToken.unauthenticated(loginParams.getUsername(), loginParams.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", userDetails.getUsername());
        payload.put("loginTime", LocalTime.now());
        JWTSigner jwtSigner = JWTSignerUtil.hs512("yingyingying".getBytes(StandardCharsets.UTF_8));
        String token = JWTUtil.createToken(payload, jwtSigner);
        redisTemplate.opsForHash().put("login-user", userDetails.getUsername(), userDetails);
        return token;
    }
}
