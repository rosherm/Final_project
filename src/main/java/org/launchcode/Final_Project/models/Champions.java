package org.launchcode.Final_Project.models;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Blob;

@Entity
public class Champions {

    @Id
    private int id;

    @NotEmpty
    private String championName;

    @NotEmpty
    private String championPicUrl;

    public Champions(String championName, String championPicUrl) {
        this.championName = championName;
        this.championPicUrl = championPicUrl;
    }

    public Champions() {
    }

    public int getId() {
        return id;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getChampionPicUrl() {
        return championPicUrl;
    }

    public void setChampionPicUrl(String championPicUrl) {
        this.championPicUrl = championPicUrl;
    }
}
