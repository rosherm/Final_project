package org.launchcode.Final_Project.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Game {

    private Boolean win;

    @Id
    private int id;

    @ManyToOne
    private user user;

    public Game(Boolean win) {
        this.win = win;
    }

    public org.launchcode.Final_Project.models.user getUser() {
        return user;
    }

    public void setUser(org.launchcode.Final_Project.models.user user) {
        this.user = user;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public int getId() {
        return id;
    }


}