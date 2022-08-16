package com.Maksim.SimpleToDo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String username;

    private String password;

    public String toString(){
        return "Username: " + username + "\nEmail: " + email + "\nPassword: " + password + "\n";
    }
}
