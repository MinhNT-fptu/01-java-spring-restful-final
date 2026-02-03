package vn.hoidanit.jobhunter.service;

import vn.hoidanit.jobhunter.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import vn.hoidanit.jobhunter.repository.UserRepository;

@Service // khai báo đây là tầng Service(xử lý logic) -> tạo bean để DI
public class UserService {

    // UserService inject UserRepository (DI)
    private final UserRepository userRepository;

    // constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // xử lý logic tạo user
    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    // xử lý logic xóa user theo id
    public void handleDeleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

    // xử lý logic lấy user theo id
    public User fetchUserById(long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    // xử lý logic lấy tất cả user
    public List<User> fetchAllUsers() {
        return this.userRepository.findAll();
    }

    // xử lý logic update user
    public User handleUpdateUser(User updateUser) {
        User user = this.fetchUserById(updateUser.getId());
        if (user != null) {
            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());
            user.setPassword(updateUser.getPassword());
            // update user trong DB
            this.userRepository.save(user); // create hay update phải save thì DB mới thay đổi
        }
        return user;
    }

    // xử lý logic lấy user by email
    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }
}
