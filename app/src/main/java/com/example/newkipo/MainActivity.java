package com.example.newkipo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnTriangleR, btnTriangleL, btnInfo, btnAddPlant;
    private int index = 0;
    private ImageView plant,pot;
    private TextView plantName, txtDialogAlert;
    private ArrayList<UserPlant> userPlants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);


        userPlants.add( new UserPlant("Solovino",new Sunflower(),R.drawable.maceta_uno));
        userPlants.add( new UserPlant("Melquiades",new Sunflower(),R.drawable.pot_3));
        userPlants.add( new UserPlant("Frida",new Sunflower(),R.drawable.pot_2));
        userPlants.add( new UserPlant("Plantin",new Sunflower(),R.drawable.pot_3));
        userPlants.add( new UserPlant("Kipo",new Sunflower(),R.drawable.pot_2));

        saveData(userPlants);

        //TEXT
        plantName = findViewById(R.id.editText2);

        //IMAGE
        plant = findViewById(R.id.imgPlant);
        pot = findViewById(R.id.imageView7);

        //BUTTONS
        btnAddPlant = findViewById(R.id.btnAddPlantMain);
        btnInfo = findViewById(R.id.btnInfo);
        btnTriangleR = findViewById(R.id.btnTriangleRight);
        btnTriangleL = findViewById(R.id.btnTriangleLeft);
        btnTriangleR.setOnClickListener(this);
        btnTriangleL.setOnClickListener(this);

        btnAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreatePlantActivity.class);
                startActivity(intent);
            }
        });

        //CREATE THE DIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.normal_dialog_alert, null));
        final AlertDialog dialog = builder.create();
        //SET TRANSPARENT THE BACKGROUND AND SHOW IT
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                //THE WIDGETS INSIDE THE DIALOG CAN BE MODIFY ONLY AFTER ITS SHOW
                txtDialogAlert = dialog.findViewById(R.id.txtInfo);
                txtDialogAlert.setText(userPlants.get(index).getPlant().getPlantInfo());
                Button btnDialog = dialog.findViewById(R.id.btnDialog);
                btnDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        //HERE I NEED TO VERIFY IF THE USER HAD ANY PLANTS, DEPENDING OF THAT ONE ACTIVITY WILL BE LAUNCH OR ANOTHER
        /*Intent intent = new Intent(this,AddPlantActivity.class);
        startActivity(intent);
        finish();*/
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnTriangleRight){
            index++;
            if(index>=(userPlants.size()-1)){
                index = 0;
            }
        }else{
            index--;
            if(index<0){
                index = userPlants.size()-1;
            }
        }

        plant.setImageResource(userPlants.get(index).getPlant().getResourceIdImage());
        pot.setImageResource(userPlants.get(index).getPot());
        plantName.setText(userPlants.get(index).getPlantName());
    }

    private void saveData(ArrayList<UserPlant> userPlants){
        String filename = "userPlants";
        Gson gson = new Gson();
        String jsonUserPlants = gson.toJson(userPlants);
        try (FileOutputStream fos = this.openFileOutput(filename, this.MODE_PRIVATE)) {
            fos.write(jsonUserPlants.getBytes());
        }catch(Exception e) {
        }
    }

}
