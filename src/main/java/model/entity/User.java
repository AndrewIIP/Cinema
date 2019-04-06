package model.entity;

import lombok.Data;
import model.spec.Role;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private Role role;
}
