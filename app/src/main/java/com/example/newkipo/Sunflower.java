package com.example.newkipo;

public class Sunflower extends Plant{

    private final String plantName = "Sunflower";
    private final int idImage = R.drawable.plant;

    public Sunflower() {
    }

    @Override
    String getPlanName() {
        return plantName;
    }

    @Override
    int getResourceIdImage() {
        return idImage;
    }
}
