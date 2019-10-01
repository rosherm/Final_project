package org.launchcode.Final_Project.models;


import javax.persistence.*;

@Entity
public class GameChampionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "item_id")
    private Item Item;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "gamechampion_id")
    private GameChampion gameChampion;

    public GameChampionItem(org.launchcode.Final_Project.models.Item item, GameChampion gameChampion) {
        Item = item;
        this.gameChampion = gameChampion;
    }

    public GameChampionItem() {
    }

    public int getId() {
        return id;
    }

    public org.launchcode.Final_Project.models.Item getItem() {
        return Item;
    }

    public void setItem(org.launchcode.Final_Project.models.Item item) {
        Item = item;
    }

    public GameChampion getGameChampion() {
        return gameChampion;
    }

    public void setGameChampion(GameChampion gameChampion) {
        this.gameChampion = gameChampion;
    }
}
