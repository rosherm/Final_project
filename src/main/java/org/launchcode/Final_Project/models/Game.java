package org.launchcode.Final_Project.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {

    @OneToMany
    @JoinColumn(name = "game_id")
    private List<GameChampion> gameChampion = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "game_id")
    private List<GameItem> gameItem = new ArrayList<>();

    private int placement;

    private int LP;

    @Id
    private int id;

    public Game(int placement, int LP) {
        this.placement = placement;
        this.LP = LP;

    }

    public Game() {
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public int getLP() {
        return LP;
    }

    public void setLP(int LP) {
        this.LP = LP;
    }


    public int getId() {
        return id;
    }

    public List<GameChampion> getGameChampion() {
        return gameChampion;
    }

    public List<GameItem> getGameItem() {
        return gameItem;
    }
}
