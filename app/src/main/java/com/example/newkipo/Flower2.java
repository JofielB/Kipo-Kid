package com.example.newkipo;

import java.util.ArrayList;

public class Flower2 extends Plant{

    private final String plantName = "Flower 2";
    private final int idImage = R.drawable.plant_2;
    private ArrayList<String> infoSunflower = new ArrayList<>();

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


}
