package com.example.newkipo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Sunflower extends Plant{

    private final String plantName = "Sunflower";
    private final int idImage = R.drawable.plant;
    private ArrayList<String> infoSunflower = new ArrayList<>();

    public Sunflower() {
        infoSunflower.add("Info 1");
        infoSunflower.add("Info 2");
        infoSunflower.add("Info 3");
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
    public String getPlantInfo() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, infoSunflower.size());
        return infoSunflower.get(randomNum);
    }


}
