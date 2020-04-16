package com.example.newkipo;

public class UserPlant {
    String plantName;
    Plant plant;
    int pot, daysOfLife, plantImage;

    public UserPlant(String plantName, Plant plant, int pot) {
        this.plantName = plantName;
        this.plant = plant;
        plantImage = plant.getResourceIdImage();
        this.pot = pot;
    }

    public int getPlantImage(){
        return plantImage;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public int getDaysOfLife() {
        return daysOfLife;
    }

    public void setDaysOfLife(int daysOfLife) {
        this.daysOfLife = daysOfLife;
    }
}
