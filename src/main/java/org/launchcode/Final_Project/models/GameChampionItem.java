package org.launchcode.Final_Project.models;


import javax.persistence.*;

@Entity
public class GameChampionItem {

    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item Item = new Item();

    @ManyToOne
    private GameChampion gameChampion;

    public GameChampionItem(org.launchcode.Final_Project.models.Item item) {
        Item = item;
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
