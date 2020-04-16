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
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnTriangleR, btnTriangleL, btnInfo, btnAddPlant;
    private int index = 0;
    private ImageView plant,pot;
    private TextView plantName, txtDialogAlert;
    private ArrayList<UserPlant> userPlants = new ArrayList<>();
   private final String FILENAME = "userPlants";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);

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

        //HERE I NEED TO VERIFY IF THE USER HAD ANY PLANTS, DEPENDING OF THAT ONE ACTIVITY WILL BE LAUNCH OR ANOTHER
        File file = this.getFileStreamPath(FILENAME);
        if(file == null || !file.exists()){
            //THE FILE DOSENT EXIST SO WE CREATE THE FILE AND LAUNCH TE ACTIVITY TO ADD A NEW PLANT
            Toast.makeText(this,"Do not exist",Toast.LENGTH_SHORT).show();
            saveData(userPlants);
            Intent intent = new Intent(this, AddPlantActivity.class);
            startActivity(intent);
            finish();
        }else{
            //THE FILE EXIST BUT WE HAVE TO CHECK IF THE LIST OF PLANTS IS EMPTY
            Toast.makeText(this,"Exist",Toast.LENGTH_SHORT).show();

            try {
                fetchData();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(userPlants.isEmpty()){//ARRAY ITS EMPTY
                Toast.makeText(this,"EMPTY",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AddPlantActivity.class);
                startActivity(intent);
                finish();
            }else{
                //SET THE FIRST PLANT WHEN THIS ACTIVITY START
                plant.setImageResource(userPlants.get(0).getPlantImage());
                pot.setImageResource(userPlants.get(0).getPot());
                plantName.setText(userPlants.get(0).getPlantName());
            }

        }

        //GO TO CREATE PLANT ACTIVITY
        btnAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnTriangleRight){
            index++;
            if(index>=(userPlants.size())){
                index = 0;
            }
        }else{
            index--;
            if(index<0){
                index = userPlants.size()-1;
            }
        }
        plant.setImageResource(userPlants.get(index).getPlantImage());
        pot.setImageResource(userPlants.get(index).getPot());
        plantName.setText(userPlants.get(index).getPlantName());
    }

    private void saveData(ArrayList<UserPlant> userPlants){
        Gson gson = new Gson();
        String jsonUserPlants = gson.toJson(userPlants);
        try (FileOutputStream fos = this.openFileOutput(FILENAME, this.MODE_PRIVATE)) {
            fos.write(jsonUserPlants.getBytes());
        }catch(Exception e) {
        }
    }

    private void fetchData() throws FileNotFoundException {
        FileInputStream fis = this.openFileInput(FILENAME);
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (Exception e) {
            // Error occurred when opening raw file for reading.
        } finally {
            String contents = stringBuilder.toString();
            Log.w("JSON","josn: " + contents);
            Gson gson = new Gson();
            this.userPlants = gson.fromJson(contents, new TypeToken<List<UserPlant>>(){}.getType());
        }
    }

}
