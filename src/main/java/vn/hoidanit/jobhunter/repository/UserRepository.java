package vn.hoidanit.jobhunter.repository;

import vn.hoidanit.jobhunter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // đánh dấu đây là Repository(tầng truy cập DB) cho entity User
public interface UserRepository extends JpaRepository<User, Long> { // user là entity, Long là kdl của PK
    // muốn extends thì phải có interface
    // userRepository kế thừa JpaRepository với các phương thức CRUD sẵn có
    // dễ DI và sử dụng trong service
}
