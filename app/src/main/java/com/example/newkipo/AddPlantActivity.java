package com.example.newkipo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class AddPlantActivity extends AppCompatActivity {
    private TextView txtAdd;
    private Button btnAddPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_add_plant);

        txtAdd = findViewById(R.id.txtAdd);
        btnAddPlant = findViewById(R.id.btnAddPlant);

        //CREATE THE ANIMATIONS THAT THE COMPONENT HAVE TO DO
        ObjectAnimator animationUP = ObjectAnimator.ofFloat(txtAdd, "translationY", -50f);
        //ANIMATION NOTE: I PUT THE SECOND ANIMATION TO 0F BECAUSE IT IS THE ORIGINAL Y COORDINATE
        ObjectAnimator animationStart = ObjectAnimator.ofFloat(txtAdd, "translationY", 0f);

        //SET THE DURATION
        animationUP.setDuration(1000);

        animationStart.setDuration(1000);

        //CREATE THE ANIMATION SET, DECIDE THE ORDER OF THE ANIMATIONS, START THE ANIMATION SET
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animationUP).before(animationStart);
        animatorSet.start();


        //WE PUT A LISTENER TO THE LAS ANIMATION THAT WILL BE REPRODUCE SO WHEN IT END THE ANIMATIONSET RESTART CREATING A LOOP
        animationStart.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.start();
            }
        });

        btnAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPlantActivity.this, CreatePlantActivity.class);
                startActivity(intent);
            }
        });
    }
}
