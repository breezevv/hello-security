package example.hellosecurity;

import example.hellosecurity.domain.User;
import example.hellosecurity.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaTest {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncode() {
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        System.out.println(passwordEncoder.matches("123456", encode));
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("$2a$10$VkMpaXsEpGs06IicbFyvyOyBdTL6KtSMujVSPmNzfiI9YryWOCnYm");
        user.setNickname("哈哈哈");
        user.setEmail("user@test.com");
        userRepository.save(user);

    }
}
