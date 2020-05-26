package com.example.newkipo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class UserPlant {
    private String plantName;
    private Plant plant;
    private int pot, plantImage;
    private ArrayList<String> listOfInfo;
    private long dayBorn;
    private int daysUntilTask1;
    private int daysUntilTask2;
    private int daysUntilTask3;
    private int daysUntilTask4;
    private final int maxDaysUntilTask1;
    private final int maxDaysUntilTask2;
    private final int maxDaysUntilTask3;
    private final int maxDaysUntilTask4;



    public UserPlant(String plantName, Plant plant, int pot, long dayBorn) {
        this.plantName = plantName;
        this.plant = plant;
        plantImage = plant.getResourceIdImage();
        this.pot = pot;
        listOfInfo = plant.getPlantInfo();
        this.dayBorn = dayBorn;
        daysUntilTask1 = plant.getDaysTask1();
        daysUntilTask2 = plant.getDaysTask2();
        daysUntilTask3 = plant.getDaysTask3();
        daysUntilTask4 = plant.getDaysTask4();
        maxDaysUntilTask1 = plant.getMaxDaysUtilTask1();
        maxDaysUntilTask2 = plant.getMaxDaysUtilTask2();
        maxDaysUntilTask3 = plant.getMaxDaysUtilTask3();
        maxDaysUntilTask4 = plant.getMaxDaysUtilTask4();
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

    public int getDaysUntilTask1() {
        return daysUntilTask1;
    }

    public int getDaysUntilTask2() {
        return daysUntilTask2;
    }

    public int getDaysUntilTask3() {
        return daysUntilTask3;
    }

    public int getDaysUntilTask4() {
        return daysUntilTask4;
    }

    public int getMaxDaysUntilTask1() {
        return maxDaysUntilTask1;
    }

    public int getMaxDaysUntilTask2() {
        return maxDaysUntilTask2;
    }

    public int getMaxDaysUntilTask3() {
        return maxDaysUntilTask3;
    }

    public int getMaxDaysUntilTask4() {
        return maxDaysUntilTask4;
    }
}
