package vn.hoidanit.jobhunter.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

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
    @PostMapping("/user")
    public User createNewUser(@RequestBody User postManUser) {// JACKSON tự động map JSON từ body request thành object
                                                              // User(POJO)

        User ryanUser = this.userService.handleCreateUser(postManUser);

        return ryanUser;
    }

    @DeleteMapping("/user/{id}") // truyền động qua path
    public String deleteUserById(@PathVariable("id") long id) { // map path variable vào tham số id

        this.userService.handleDeleteUserById(id);

        return "delete success";
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") long id) {

        // Optional<User> user = this.userService.handleGetUserById(id);
        User user = this.userService.fetchUserById(id);

        return user;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() { // lấy tất cả user java tự convert List<User> thành JSON array
        return this.userService.fetchAllUsers();
    }

    @PutMapping("/user")
    public User updateUserById(@RequestBody User updateUser) {
        User updatedUser = this.userService.handleUpdateUser(updateUser);
        return updatedUser;
    }

}
