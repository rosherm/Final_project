package org.launchcode.Final_Project.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class GameItem {

    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item Item = new Item();

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
}
