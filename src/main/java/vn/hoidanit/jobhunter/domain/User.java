package vn.hoidanit.jobhunter.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // báo class User là một thực thể sẽ được lưu/đọc từ DB
@Table(name = "users") // ánh xạ class User với bảng users trong DB
public class User {
    @Id // đánh dấu là PK của bảng
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id sẽ được DB sinh theo cơ chế identity

    private long id;
    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}