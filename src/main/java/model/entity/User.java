package model.entity;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String email;
}
