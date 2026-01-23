package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@Controller → thường dùng cho web MVC + view (JSP/HTML)
@RestController // dùng cho API trả JSON/text
public class UserController {

    // UserController inject UserService (DI)
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // @GetMapping("/user/create") // tạo endpoint(API), http method: GET, path:
    // /user/create
    @PostMapping("/user/create")
    public User createNewUser(
            @RequestBody User postManUser) {

        User ryanUser = this.userService.handleCreateUser(postManUser);

        return ryanUser;
    }
}
