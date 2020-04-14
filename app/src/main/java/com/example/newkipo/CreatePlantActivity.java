package com.example.newkipo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CreatePlantActivity extends AppCompatActivity implements View.OnClickListener {
    private String plantName = "";
    private Button btnDialog ;
    private EditText editTextDialog;
    private TextView txtTitleName;
    private ImageView plants[] = new ImageView[3];
    private ImageView pots[] = new ImageView[3];
    private ImageView plant, pot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_create_plant);

        txtTitleName = findViewById(R.id.editText2);
        plant = findViewById(R.id.imageView5);
        pot = findViewById(R.id.imageView4);
        plants[0] = findViewById(R.id.imgPlant1);
        plants[1] = findViewById(R.id.imgPlant2);
        plants[2] = findViewById(R.id.imgPlant3);
        pots[0] = findViewById(R.id.imgPot1);
        pots[1] = findViewById(R.id.imgPot2);
        pots[2] = findViewById(R.id.imgPot3);

        plants[0].setOnClickListener(this);
        plants[1].setOnClickListener(this);
        plants[2].setOnClickListener(this);

        pots[0].setOnClickListener(this);
        pots[1].setOnClickListener(this);
        pots[2].setOnClickListener(this);

        //CREATE THE DIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_plant_name, null));

        final AlertDialog dialog = builder.create();
        //SET TRANSPARENT THE BACKGROUND AND SHOW IT
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();


        editTextDialog = dialog.findViewById(R.id.editText);
        //GET THE BUTTON FROM THE DIALOG LAYOUT
        btnDialog = dialog.findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plantName = editTextDialog.getText().toString();
                if(plantName.equals("")){
                    Toast.makeText(CreatePlantActivity.this, "Name your plant", Toast.LENGTH_SHORT).show();
                }else{
                    txtTitleName.setText(plantName);
                    dialog.dismiss();
                }
            }
        });

        txtTitleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog.show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        ImageView image = (ImageView) view;
        if(image.getId() == R.id.imgPlant1||image.getId() == R.id.imgPlant2||image.getId() == R.id.imgPlant3){
            plant.setImageDrawable(image.getDrawable());
        }else {
           pot.setImageDrawable(image.getDrawable());
        }
    }

}
