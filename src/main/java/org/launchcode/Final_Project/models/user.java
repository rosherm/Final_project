package org.launchcode.Final_Project.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class user {



    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{8,30}$", message = "Username must only use letters, numbers, and be between 8-30 characters long")
    private String username;


    @NotEmpty
    @Pattern(regexp = "^[\\S]{8,100}$", message = "No spaces allowed in password and be between 8-30 characters long")
    private String password;



    @Email(message="Please provide a valid email address")
    private String email;

    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Game> Games = new ArrayList<>();

    public user() {
    }

    public user(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public List<Game> getGames() {
        return Games;
    }

    public void setGames(List<Game> games) {
        Games = games;
    }

    public void addGames(Game game){
        Games.add(game);
    }

}
