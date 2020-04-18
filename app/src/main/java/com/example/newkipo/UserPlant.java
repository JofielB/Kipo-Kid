package com.example.newkipo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class UserPlant {
    private String plantName;
    private Plant plant;
    private int pot, plantImage;
    private ArrayList<String> listOfInfo;
    private long dayBorn;


    public UserPlant(String plantName, Plant plant, int pot, long dayBorn) {
        this.plantName = plantName;
        this.plant = plant;
        plantImage = plant.getResourceIdImage();
        this.pot = pot;
        listOfInfo = plant.getPlantInfo();
        this.dayBorn = dayBorn;
    }

    public String getRandomInfo(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, listOfInfo.size());
        return listOfInfo.get(randomNum);
    }

    public long getDayBorn() {
        return dayBorn;
    }

    public void setDayBorn(long dayBorn) {
        this.dayBorn = dayBorn;
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


}
