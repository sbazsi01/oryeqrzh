package com.example.oryeqrzh.model;

import javax.persistence.*;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name="brand",unique = true,nullable = false)
    private String brand;


    @Column(name="model")
    private String model;


    @Column(name="hp")
    private Integer hp;

    public Car() {

    }

    public Car(String brand, String model, Integer hp) {
        this.brand = brand;
        this.model = model;
        this.hp = hp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) throws Exception {
        if(hp<0){
            throw new Exception();
        }
        this.hp = hp;
    }
}

