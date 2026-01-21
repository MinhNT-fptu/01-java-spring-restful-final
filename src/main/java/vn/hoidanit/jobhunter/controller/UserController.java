package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

//@Controller → thường dùng cho web MVC + view (JSP/HTML)
@RestController // dùng cho API trả JSON/text
public class UserController {

    // UserController inject UserService (DI)
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/create") // tạo endpoint(API), http method: GET, path: /user/create
    public String createNewUser() {

        User user = new User();
        user.setEmail("ntminh1302@gmail.com");
        user.setName("Ryan");
        user.setPassword("123456");

        this.userService.handleCreateUser(user);

        return "Create new user";
    }
}
