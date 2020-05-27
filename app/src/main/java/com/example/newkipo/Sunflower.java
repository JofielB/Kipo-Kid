package com.example.newkipo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Sunflower extends Plant{

    private final String plantName = "Sunflower";
    private final int idImage = R.drawable.plant;
    private ArrayList<String> infoSunflower = new ArrayList<>();
    private int daysUntilTask1 = 5;
    private int daysUntilTask2 = 3;
    private int daysUntilTask3 = 1;
    private int daysUntilTask4 = 0;
    private final int maxDaysUntilTask1 = 5;
    private final int maxDaysUntilTask2 = 4;
    private final int maxDaysUntilTask3 = 3;
    private final int maxDaysUntilTask4 = 2;

    public Sunflower() {
        infoSunflower.add("Info 1 Sunflower");
        infoSunflower.add("Info 2 Sunflower");
        infoSunflower.add("Info 3 Sunflower");
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


    public int getDaysTask1() { return daysUntilTask1; }

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
    public int getMaxDaysUtilTask1() { return maxDaysUntilTask1; }

    @Override
    public int getMaxDaysUtilTask2() { return maxDaysUntilTask2; }

    @Override
    public int getMaxDaysUtilTask3() {
        return maxDaysUntilTask3;
    }

    @Override
    public int getMaxDaysUtilTask4() {
        return maxDaysUntilTask4;
    }
}
