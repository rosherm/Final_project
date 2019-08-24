package org.launchcode.Final_Project.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Blob;
import java.util.ArrayList;

@Entity
public class Item{


    @Id
    private int id;

    @NotEmpty
    private ArrayList<String> itemName;

    @NotEmpty
    private ArrayList<byte[]> itemPic;

    public Item(ArrayList<String> itemName, ArrayList<byte[]> itemPic) {
        this.itemName = itemName;
        this.itemPic = itemPic;
    }

    public Item() {
    }

    public ArrayList<String> getItemName() {
        return itemName;
    }

    public void setItemName(ArrayList<String> itemName) {
        this.itemName = itemName;
    }

    public ArrayList<byte[]> getItemPic() {
        return itemPic;
    }

    public void setItemPic(ArrayList<byte[]> itemPic) {
        this.itemPic = itemPic;
    }
}
