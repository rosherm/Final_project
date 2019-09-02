package org.launchcode.Final_Project.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Attribute {

    @Id
    private int id;

    private String Name;

    private String Description;

    @OneToMany
    @JoinColumn(name = "Attribute_1_id")
    private List<Champions> champion1 = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "Attribute_2_id")
    private List<Champions> champion2 = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "Attribute_3_id")
    private List<Champions> champion3 = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "Bonus_1_id")
    private Bonus bonus1;

    @ManyToOne
    @JoinColumn(name = "Bonus_2_id")
    private Bonus bonus2;

    @ManyToOne
    @JoinColumn(name = "Bonus_3_id")
    private Bonus bonus3;

    public Attribute(String name, String description, Bonus bonus1, Bonus bonus2, Bonus bonus3) {
        Name = name;
        Description = description;
        this.bonus1 = bonus1;
        this.bonus2 = bonus2;
        this.bonus3 = bonus3;
    }

    public Attribute() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Bonus getBonus1() {
        return bonus1;
    }

    public void setBonus1(Bonus bonus1) {
        this.bonus1 = bonus1;
    }

    public Bonus getBonus2() {
        return bonus2;
    }

    public void setBonus2(Bonus bonus2) {
        this.bonus2 = bonus2;
    }

    public Bonus getBonus3() {
        return bonus3;
    }

    public void setBonus3(Bonus bonus3) {
        this.bonus3 = bonus3;
    }

    public List<Champions> getChampion1() {
        return champion1;
    }

    public void setChampion1(List<Champions> champion1) {
        this.champion1 = champion1;
    }

    public List<Champions> getChampion2() {
        return champion2;
    }

    public void setChampion2(List<Champions> champion2) {
        this.champion2 = champion2;
    }

    public List<Champions> getChampion3() {
        return champion3;
    }

    public void setChampion3(List<Champions> champion3) {
        this.champion3 = champion3;
    }
}
