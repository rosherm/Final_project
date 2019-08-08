package org.launchcode.Final_Project.models;


import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class user {

    @NotNull
    @Size(min=3,max=15)
    private String username;

    @NotNull
    @Size(min=8,max=100)
    private String password;

    @NotNull
    @Email(message="Please provide a valid email address")
    private String email;

    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Game> Games = new ArrayList<>();



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

}
