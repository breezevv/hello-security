package example.hellosecurity;

import example.hellosecurity.domain.CustomUserDetails;
import example.hellosecurity.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@test.com");
        user.setPassword("123456");

        CustomUserDetails userDetails = new CustomUserDetails(user);
        redisTemplate.opsForValue().set(userDetails.getUsername(), userDetails);
        Object o = redisTemplate.opsForValue().get(userDetails.getUsername());
        System.out.println();
    }
}
