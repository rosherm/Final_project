package org.launchcode.Final_Project.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item{


    @Id
    private int id;

    @NotEmpty
    private ArrayList<String> itemName;

    @NotEmpty
    private ArrayList<String> itemPic;

    @NotEmpty
    private ArrayList<String> itemEffect;

    @OneToOne
    @JoinColumn(name = "RootItemId1")
    private Item RootItemId1;

    @OneToOne
    @JoinColumn(name = "RootItemId2")
    private Item RootItemId2;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<GameChampionItem> GameChampionItem = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<GameItem> gameItem = new ArrayList<>();


    public Item(ArrayList<String> itemName, ArrayList<String> itemPic, ArrayList<String> itemEffect, Item rootItemId1, Item rootItemId2) {
        this.itemName = itemName;
        this.itemPic = itemPic;
        this.itemEffect = itemEffect;
        RootItemId1 = rootItemId1;
        RootItemId2 = rootItemId2;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getItemName() {
        return itemName;
    }

    public void setItemName(ArrayList<String> itemName) {
        this.itemName = itemName;
    }

    public ArrayList<String> getItemPic() {
        return itemPic;
    }

    public void setItemPic(ArrayList<String> itemPic) {
        this.itemPic = itemPic;
    }

    public ArrayList<String> getItemEffect() {
        return itemEffect;
    }

    public void setItemEffect(ArrayList<String> itemEffect) {
        this.itemEffect = itemEffect;
    }

    public Item getRootItemId1() {
        return RootItemId1;
    }

    public void setRootItemId1(Item rootItemId1) {
        RootItemId1 = rootItemId1;
    }

    public Item getRootItemId2() {
        return RootItemId2;
    }

    public void setRootItemId2(Item rootItemId2) {
        RootItemId2 = rootItemId2;
    }

    public List<org.launchcode.Final_Project.models.GameChampionItem> getGameChampionItem() {
        return GameChampionItem;
    }

    public List<GameItem> getGameItem() {
        return gameItem;
    }
}
