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

    private int pots[] = {
            R.drawable.maceta_uno,
            R.drawable.pot_2,
            R.drawable.pot_3
    };
    private int plants[] = {
            R.drawable.plant,
            R.drawable.plant_2,
            R.drawable.plant_3
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
            if(index>=3){
                index = 0;
            }
        }else{
            index--;
            if(index<0){
                index = 2;
            }
        }

        plant.setImageResource(plants[index]);
        pot.setImageResource(pots[index]);
    }

}
