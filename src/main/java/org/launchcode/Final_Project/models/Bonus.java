package org.launchcode.Final_Project.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bonus {

    @Id
    @GeneratedValue
    private int id;

    private int ChampionsRequired;

    private String Effect;

    @OneToMany
    @JoinColumn(name = "Bonus_1_id")
    private List<Attribute> attribute1 = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "Bonus_2_id")
    private List<Attribute> attribute2 = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "Bonus_3_id")
    private List<Attribute> attribute3 = new ArrayList<>();

    public Bonus(int championsRequired, String effect) {
        ChampionsRequired = championsRequired;
        Effect = effect;
    }

    public Bonus() {
    }

    public int getId() {
        return id;
    }

    public int getChampionsRequired() {
        return ChampionsRequired;
    }

    public void setChampionsRequired(int championsRequired) {
        ChampionsRequired = championsRequired;
    }

    public String getEffect() {
        return Effect;
    }

    public void setEffect(String effect) {
        Effect = effect;
    }

    public List<Attribute> getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(List<Attribute> attribute1) {
        this.attribute1 = attribute1;
    }

    public List<Attribute> getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(List<Attribute> attribute2) {
        this.attribute2 = attribute2;
    }

    public List<Attribute> getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(List<Attribute> attribute3) {
        this.attribute3 = attribute3;
    }
}

