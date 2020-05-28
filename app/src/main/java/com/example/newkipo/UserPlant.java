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

    private long dateLastTask1;
    private long dateLastTask2;
    private long dateLastTask3;
    private long dateLastTask4;

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
        dateLastTask1 = dayBorn;
        dateLastTask2 = dayBorn;
        dateLastTask3 = dayBorn;
        dateLastTask4 = dayBorn;
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

    public long getDateLastTask1() {
        return dateLastTask1;
    }

    public long getDateLastTask2() {
        return dateLastTask2;
    }

    public long getDateLastTask3() {
        return dateLastTask3;
    }

    public long getDateLastTask4() {
        return dateLastTask4;
    }

    public void setDaysUntilTask1(int daysUntilTask1) {
        this.daysUntilTask1 = daysUntilTask1;
    }

    public void setDaysUntilTask2(int daysUntilTask2) {
        this.daysUntilTask2 = daysUntilTask2;
    }

    public void setDaysUntilTask3(int daysUntilTask3) {
        this.daysUntilTask3 = daysUntilTask3;
    }

    public void setDaysUntilTask4(int daysUntilTask4) {
        this.daysUntilTask4 = daysUntilTask4;
    }

    public void setDateLastTask1(long dateLastTask1) {
        this.dateLastTask1 = dateLastTask1;
    }

    public void setDateLastTask2(long dateLastTask2) {
        this.dateLastTask2 = dateLastTask2;
    }

    public void setDateLastTask3(long dateLastTask3) {
        this.dateLastTask3 = dateLastTask3;
    }

    public void setDateLastTask4(long dateLastTask4) {
        this.dateLastTask4 = dateLastTask4;
    }
}
