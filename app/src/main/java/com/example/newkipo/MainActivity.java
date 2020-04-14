package com.example.newkipo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnTriangleR, btnTriangleL;
    private int index = 0;
    private ImageView plant,pot;
    private UserPlant[] userPlants = {
            new UserPlant("Solovino",new Sunflower(),R.drawable.maceta_uno),
            new UserPlant("Melquiades",new Sunflower(),R.drawable.pot_3),
            new UserPlant("Frida",new Sunflower(),R.drawable.pot_2),
            new UserPlant("Plantin",new Sunflower(),R.drawable.pot_3),
            new UserPlant("Kipo",new Sunflower(),R.drawable.maceta_uno),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        plant = findViewById(R.id.imgPlant);
        pot = findViewById(R.id.imageView7);

        btnTriangleR = findViewById(R.id.btnTriangleRight);
        btnTriangleL = findViewById(R.id.btnTriangleLeft);
        btnTriangleR.setOnClickListener(this);
        btnTriangleL.setOnClickListener(this);

        //HERE I NEED TO VERIFY IF THE USER HAD ANY PLANTS, DEPENDING OF THAT ONE ACTIVITY WILL BE LAUNCH OR ANOTHER
        /*Intent intent = new Intent(this,AddPlantActivity.class);
        startActivity(intent);
        finish();*/
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnTriangleRight){
            index++;
            if(index>=(userPlants.length-1)){
                index = 0;
            }
        }else{
            index--;
            if(index<0){
                index = userPlants.length-1;
            }
        }

        plant.setImageResource(userPlants[index].getPlant().getResourceIdImage());
        pot.setImageResource(userPlants[index].getPot());
    }

}
