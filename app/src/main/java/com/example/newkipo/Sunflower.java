package com.example.newkipo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Sunflower extends Plant{

    private final String plantName = "Sunflower";
    private final int idImage = R.drawable.plant;
    private ArrayList<String> infoSunflower = new ArrayList<>();

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



}
