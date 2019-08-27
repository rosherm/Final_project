package org.launchcode.Final_Project.models;

import javax.persistence.*;

@Entity
public class GameItem {

    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item Item = new Item();

    @ManyToOne
    private Game game;

    public GameItem(org.launchcode.Final_Project.models.Item item) {
        Item = item;
    }

    public GameItem() {
    }

    public org.launchcode.Final_Project.models.Item getItem() {
        return Item;
    }

    public void setItem(org.launchcode.Final_Project.models.Item item) {
        Item = item;
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
}