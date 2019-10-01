package org.launchcode.Final_Project.models;

import javax.persistence.*;

@Entity
public class GameItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "item_id")
    private Item Item;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "game_id")
    private Game game;

    public GameItem(org.launchcode.Final_Project.models.Item item, Game game) {
        Item = item;
        this.game = game;
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
