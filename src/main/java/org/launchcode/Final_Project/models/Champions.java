package org.launchcode.Final_Project.models;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Champions {

    @Id
    private int id;

    @NotEmpty
    private String championName;

    @NotEmpty
    private String championPicUrl;

    @OneToMany
    @JoinColumn(name = "champion_id")
    private List<GameChampion> GameChampion = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "Attribute_1_id")
    private Attribute attribute1;

    @ManyToOne
    @JoinColumn(name = "Attribute_2_id")
    private Attribute attribute2;

    @ManyToOne
    @JoinColumn(name = "Attribute_3_id")
    private Attribute attribute3;


    public Champions(String championName, String championPicUrl, Attribute attribute1, Attribute attribute2, Attribute attribute3) {
        this.championName = championName;
        this.championPicUrl = championPicUrl;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
    }

    public Champions() {
    }

    public int getId() {
        return id;
    }

    public Attribute getAttribute1() {
        return attribute1;
    }

    public Attribute getAttribute2() {
        return attribute2;
    }

    public Attribute getAttribute3() {
        return attribute3;
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

    public List<org.launchcode.Final_Project.models.GameChampion> getGameChampion() {
        return GameChampion;
    }
}
