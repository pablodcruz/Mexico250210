package com.revature.models;

import com.revature.models.Animal;

public class Owner {
    private String name;
    private Animal pet;

    public Owner(){

    }

    public Owner(String name, Animal pet) {
        this.name = name;
        this.pet = pet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPet(Animal pet) {
        this.pet = pet;
    }

    public String getName() {
        return name;
    }

    public Animal getPet() {
        return pet;
    }
}