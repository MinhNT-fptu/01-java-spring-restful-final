package vn.hoidanit.jobhunter.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.service.error.IdInvalidException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

//@Controller → thường dùng cho web MVC + view (JSP/HTML)
@RestController // dùng cho API trả JSON/text
public class UserController {

    // UserController inject UserService (DI)
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // @GetMapping("/users/create") // tạo endpoint(API), http method: GET, path:
    // /users/create
    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User postManUser) {
        // JACKSON tự động map JSON từ body
        // request thành object
        // User(POJO)
        String hashPassword = this.passwordEncoder.encode(postManUser.getPassword()); // tạo mã hóa mk
        postManUser.setPassword(hashPassword); // ghi đè lại mk

        User ryanUser = this.userService.handleCreateUser(postManUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(ryanUser);

    }

    @DeleteMapping("/users/{id}") // truyền động qua path

    // nếu không muốn trả dữ liệu cho generic thành void
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") long id) throws IdInvalidException {
        // map path
        // variable vào
        // tham số
        // id
        if (id >= 1500) {
            throw new IdInvalidException("Id khong duoc lon hon 1500!"); // trả ra exception ghi trong terminal
        }
        this.userService.handleDeleteUserById(id);

        // return ResponseEntity.ok("Delete user successfully"); // cách viết ngắn hơn
        return ResponseEntity.noContent().build(); // Quốc Tế: delete trả về mã lỗi 204
        // return ResponseEntity.status(HttpStatus.OK).body("Delete user successfully");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {

        // Optional<User> user = this.userService.handleGetUserById(id);
        User user = this.userService.fetchUserById(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() { // lấy tất cả user java tự convert List<User> thành JSON array
        return ResponseEntity.ok(this.userService.fetchAllUsers());
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUserById(@RequestBody User updateUser) {
        User updatedUser = this.userService.handleUpdateUser(updateUser);
        return ResponseEntity.ok(updatedUser);
    }

}
