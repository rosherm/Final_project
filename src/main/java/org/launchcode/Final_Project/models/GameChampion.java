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
    private Champions Champions;

    @OneToMany
    @JoinColumn(name = "gamechampion_id")
    private List<GameChampionItem> GameChampionItem = new ArrayList<>();

    @ManyToOne
    private Game game;

    public GameChampion(Champions champions) {
        Champions = champions;
    }

    public GameChampion() {
    }

    public org.launchcode.Final_Project.models.Champions getChampions() {
        return Champions;
    }

    public void setChampions(org.launchcode.Final_Project.models.Champions champions) {
        Champions = champions;
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
