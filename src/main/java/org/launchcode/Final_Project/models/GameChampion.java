package org.launchcode.Final_Project.models;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class GameChampion {

    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "champions_id")
    private Champions Champion = new Champions();

    @OneToMany
    @JoinColumn(name = "game_champion_id")
    private List<GameChampionItem> GameChampionItem = new ArrayList<>();

    @ManyToOne
    private Game game;

    public GameChampion(Champions champion) {
        Champion = champion;
    }

    public GameChampion() {
    }

    public Champions getChampion() {
        return Champion;
    }

    public void setChampion(Champions champion) {
        Champion = champion;
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
