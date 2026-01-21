package vn.hoidanit.jobhunter.service;

import vn.hoidanit.jobhunter.domain.User;
import org.springframework.stereotype.Service;

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
    public void handleCreateUser(User user) {
        this.userRepository.save(user);
    }

}
