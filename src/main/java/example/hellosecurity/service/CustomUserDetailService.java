package example.hellosecurity.service;

import example.hellosecurity.domain.CustomUserDetails;
import example.hellosecurity.domain.User;
import example.hellosecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User customUser = this.userRepository.findUserByUsername(username);
        if (customUser == null) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
        return new CustomUserDetails(customUser);
    }
}
