package com.example.newkipo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class UserPlant {
    String plantName;
    Plant plant;
    int pot, daysOfLife, plantImage;
    ArrayList<String> listOfInfo;

    public UserPlant(String plantName, Plant plant, int pot) {
        this.plantName = plantName;
        this.plant = plant;
        plantImage = plant.getResourceIdImage();
        this.pot = pot;
        listOfInfo = plant.getPlantInfo();
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

    public String getRandomInfo(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, listOfInfo.size());
        return listOfInfo.get(randomNum);
    }
}
