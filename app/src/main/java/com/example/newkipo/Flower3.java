package com.example.newkipo;

import java.util.ArrayList;

public class Flower3 extends Plant{

    private final String plantName = "Flower 3";
    private final int idImage = R.drawable.plant_3;
    private ArrayList<String> infoSunflower = new ArrayList<>();
    private int daysUntilTask1 = 0;
    private int daysUntilTask2 = 0;
    private int daysUntilTask3 = 0;
    private int daysUntilTask4 = 0;
    private final int maxDaysUntilTask1 = 10;
    private final int maxDaysUntilTask2 = 9;
    private final int maxDaysUntilTask3 = 7;
    private final int maxDaysUntilTask4 = 6;

    public Flower3() {
        infoSunflower.add("Info 1 Flower 3");
        infoSunflower.add("Info 2 Flower 3");
        infoSunflower.add("Info 3 Flower 3");
    }
    @Override
    public String getPlanName() {
        return plantName;
    }

    @Override
    public int getResourceIdImage() {
        return idImage;
    }

    @Override
    public ArrayList<String> getPlantInfo() {
        return infoSunflower;
    }

    @Override
    public int getDaysTask1() {
        return daysUntilTask1;
    }

    @Override
    public int getDaysTask2() {
        return daysUntilTask2;
    }

    @Override
    public int getDaysTask3() {
        return daysUntilTask3;
    }

    @Override
    public int getDaysTask4() {
        return daysUntilTask4;
    }

    @Override
    public int getMaxDaysUtilTask1() {
        return maxDaysUntilTask1;
    }

    @Override
    public int getMaxDaysUtilTask2() {
        return maxDaysUntilTask2;
    }

    @Override
    public int getMaxDaysUtilTask3() {
        return maxDaysUntilTask3;
    }

    @Override
    public int getMaxDaysUtilTask4() {
        return maxDaysUntilTask4;
    }
}
