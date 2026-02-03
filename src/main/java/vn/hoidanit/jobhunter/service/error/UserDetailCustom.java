package vn.hoidanit.jobhunter.service.error;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import vn.hoidanit.jobhunter.service.UserService;

// === MỘT PHẦN TRONG AUTHENTICATION ===

@Component("userDetailsService") // ghi đè bean gián tiếp
public class UserDetailCustom implements UserDetailsService {

    // DI
    private final UserService userService;

    public UserDetailCustom(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Spring security sẽ gọi loadUserByUsername
        // thằng này so sánh password người dùng nhập với password lấy từ DB (qua
        // PasswordEncoder),
        // đúng thì đăng nhập thành công, sai thì fail.

        vn.hoidanit.jobhunter.domain.User user = this.userService.handleGetUserByUsername(username);
        // lấy data user trong DB đưa vào object

        // đóng gói dữ liệu thành UserDetails (Spring Security User)
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

    }

}
