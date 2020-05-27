package com.example.newkipo;

import java.util.ArrayList;

public class Flower2 extends Plant{

    private final String plantName = "Flower 2";
    private final int idImage = R.drawable.plant_2;
    private ArrayList<String> infoSunflower = new ArrayList<>();
    private int daysUntilTask1 = 7;
    private int daysUntilTask2 = 5;
    private int daysUntilTask3 = 4;
    private int daysUntilTask4 = 4;
    private final int maxDaysUntilTask1 = 7;
    private final int maxDaysUntilTask2 = 5;
    private final int maxDaysUntilTask3 = 4;
    private final int maxDaysUntilTask4 = 4;

    public Flower2() {
        infoSunflower.add("Info 1 Flower 2");
        infoSunflower.add("Info 2 Flower 2");
        infoSunflower.add("Info 3 Flower 2");
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
