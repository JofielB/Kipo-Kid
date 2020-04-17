package com.example.newkipo;

import java.util.ArrayList;

public class Flower3 extends Plant{

    private final String plantName = "Flower 3";
    private final int idImage = R.drawable.plant_3;
    private ArrayList<String> infoSunflower = new ArrayList<>();

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



}
