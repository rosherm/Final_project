package org.launchcode.Final_Project.models;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class GameChampion {

    @Id
    private int id;

    @OneToMany
    @JoinColumn(name = "gamechampion_id")
    private List<GameChampionItem> GameChampionItem = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "champion_id")
    private Champions champions;

    public GameChampion(Champions champions) {
        this.champions = champions;
    }

    public GameChampion() {
    }

    public Champions getChampions() {
        return champions;
    }

    public void setChampions(Champions champions) {
        this.champions = champions;
    }

    public int getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<org.launchcode.Final_Project.models.GameChampionItem> getGameChampionItem() {
        return GameChampionItem;
    }
}
