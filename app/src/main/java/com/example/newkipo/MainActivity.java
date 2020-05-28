package com.example.newkipo;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnTriangleR, btnTriangleL, btnAddPlant;
    private int index = 0;
    private ImageView plant, pot, imgBtnDelete, imgBackgroundCloud, imgTask1, imgTask2,
            imgTask3, imgTask4, imgState1, imgState2, imgState3, imgState4, imgBackground;
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
        imgBtnDelete = findViewById(R.id.imgBtnDelete);
        imgBackgroundCloud = findViewById(R.id.backgroundCloud);
        imgTask1 = findViewById(R.id.task1);
        imgTask2 = findViewById(R.id.task2);
        imgTask3 = findViewById(R.id.task3);
        imgTask4 = findViewById(R.id.task4);
        imgState1 = findViewById(R.id.state1);
        imgState2 = findViewById(R.id.state2);
        imgState3 = findViewById(R.id.state3);
        imgState4 = findViewById(R.id.state4);
        imgBackground = findViewById(R.id.imageView6);

        //BUTTONS
        btnAddPlant = findViewById(R.id.btnAddPlantMain);
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

                //SET THE DAYS OF LIFE OF THE PLANT
                updateDaysOfLife();

                //SET THE CORRECT STATE IMAGES ON THE TOP BAR
                updateDaysOfTasks();
            }

        }

        //CREATE THE ANIMATIONS THAT CLOUD DOES IN VERTICAL
        ObjectAnimator animationUP = ObjectAnimator.ofFloat(imgBackgroundCloud, "translationY", -10f);
        //ANIMATION NOTE: I PUT THE SECOND ANIMATION TO 0F BECAUSE IT IS THE ORIGINAL Y COORDINATE
        ObjectAnimator animationStart = ObjectAnimator.ofFloat(imgBackgroundCloud, "translationY", 0f);

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




        //GO TO CREATE PLANT ACTIVITY
        btnAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(MainActivity.this, CreatePlantActivity.class);
                startActivity(intent);
            }
        });

        //CREATE THE DIALOG OF INFO
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.normal_dialog_alert, null));
        final AlertDialog dialog = builder.create();
        //SET TRANSPARENT THE BACKGROUND AND SHOW IT
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        imgBackgroundCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                //THE WIDGETS INSIDE THE DIALOG CAN BE MODIFY ONLY AFTER ITS SHOW
                txtDialogAlert = dialog.findViewById(R.id.txtInfo);
                txtDialogAlert.setText(userPlants.get(index).getRandomInfo());
                Button btnDialog = dialog.findViewById(R.id.btnDialog);
                btnDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        //DELETE A PLANT
        //CREATE THE DIALOG DELETE
        AlertDialog.Builder builderDelete = new AlertDialog.Builder(this);
        LayoutInflater inflaterDelete = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_delete_alert, null));
        final AlertDialog dialogDelete = builder.create();
        //SET TRANSPARENT THE BACKGROUND AND SHOW IT
        dialogDelete.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDelete.show();
                TextView txtPlantName = dialogDelete.findViewById(R.id.plantNameDelete);
                txtPlantName.setText(userPlants.get(index).getPlantName());
                Button btnNo = dialogDelete.findViewById(R.id.btnNo);
                Button btnYes = dialogDelete.findViewById(R.id.btnYes);
                //DONT DELETE THE PLANT
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogDelete.dismiss();
                    }
                });
                //DELETE THE PLANT
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userPlants.remove(index);
                        saveData(userPlants);
                         //If after the delete of one plant there is no more plants we launch again the activity
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                         finish();
                        Toast.makeText(MainActivity.this,"PLANT DELETED",Toast.LENGTH_LONG).show();
                        dialogDelete.dismiss();
                    }
                });
            }
        });

        //CREATE THE DIALOG OF THE TASKS
        AlertDialog.Builder builderTask = new AlertDialog.Builder(this);
        LayoutInflater inflaterTask = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_tasks, null));
        final AlertDialog dialogTask = builder.create();
        //set transparent the background of the dialog box
        dialogTask.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        //BOTTOM TASKS BAR
        imgTask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,""+userPlants.get(index).getDaysUntilTask1()+"\n" + userPlants.get(index).getMaxDaysUntilTask1(),Toast.LENGTH_SHORT).show();
                dialogTask.show();
                TextView txt = dialogTask.findViewById(R.id.txtplantTask);
                txt.setText("Actividad 1");
                Button btnNo = dialogTask.findViewById(R.id.btnNoTask);
                Button btnYes = dialogTask.findViewById(R.id.btnYesTask);
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogTask.dismiss();
                    }
                });
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //First we update the days until task 1 to the max
                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                        calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DATE));
                        long dateMill = calendar.getTimeInMillis();
                        userPlants.get(index).setDateLastTask1(dateMill);
                        //We close the dialog
                        dialogTask.dismiss();
                        //Then we update the image of state of the plant
                        updateDaysOfTasks();
                        //And for the last we save the data
                        saveData(userPlants);
                    }
                });
            }
        });
        imgTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,""+userPlants.get(index).getDaysUntilTask2()+"\n" + userPlants.get(index).getMaxDaysUntilTask2(),Toast.LENGTH_SHORT).show();
                dialogTask.show();
                TextView txt = dialogTask.findViewById(R.id.txtplantTask);
                txt.setText("Actividad 2");
                Button btnNo = dialogTask.findViewById(R.id.btnNoTask);
                Button btnYes = dialogTask.findViewById(R.id.btnYesTask);
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogTask.dismiss();
                    }
                });
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //First we update the days until task 1 to the max
                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                        calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DATE));
                        long dateMill = calendar.getTimeInMillis();
                        userPlants.get(index).setDateLastTask2(dateMill);
                        //We close the dialog
                        dialogTask.dismiss();
                        //Then we update the image of state of the plant
                        updateDaysOfTasks();
                        //And for the last we save the data
                        saveData(userPlants);
                    }
                });
            }
        });
        imgTask3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,""+userPlants.get(index).getDaysUntilTask3()+"\n" + userPlants.get(index).getMaxDaysUntilTask3(),Toast.LENGTH_SHORT).show();
                dialogTask.show();
                TextView txt = dialogTask.findViewById(R.id.txtplantTask);
                txt.setText("Actividad 3");
                Button btnNo = dialogTask.findViewById(R.id.btnNoTask);
                Button btnYes = dialogTask.findViewById(R.id.btnYesTask);
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogTask.dismiss();
                    }
                });
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //First we update the days until task 1 to the max
                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                        calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DATE));
                        long dateMill = calendar.getTimeInMillis();
                        userPlants.get(index).setDateLastTask3(dateMill);
                        //We close the dialog
                        dialogTask.dismiss();
                        //Then we update the image of state of the plant
                        updateDaysOfTasks();
                        //And for the last we save the data
                        saveData(userPlants);
                    }
                });
            }
        });
        imgTask4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,""+userPlants.get(index).getDaysUntilTask4()+"\n" + userPlants.get(index).getMaxDaysUntilTask4(),Toast.LENGTH_SHORT).show();
                dialogTask.show();
                TextView txt = dialogTask.findViewById(R.id.txtplantTask);
                txt.setText("Actividad 4");
                Button btnNo = dialogTask.findViewById(R.id.btnNoTask);
                Button btnYes = dialogTask.findViewById(R.id.btnYesTask);
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogTask.dismiss();
                    }
                });
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //First we update the days until task 1 to the max
                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                        calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DATE));
                        long dateMill = calendar.getTimeInMillis();
                        userPlants.get(index).setDateLastTask4(dateMill);
                        //We close the dialog
                        dialogTask.dismiss();
                        //Then we update the image of state of the plant
                        updateDaysOfTasks();
                        //And for the last we save the data
                        saveData(userPlants);
                    }
                });
            }
        });

    }

    //NEXT AND PREVIOUS BUTTONS
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
        //SET THE DAYS OF LIFE OF THE PLANT
        updateDaysOfLife();
        //Update de top bar
        updateDaysOfTasks();
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

    private void updateDaysOfLife(){
        // Creates one calendars instances
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //Set it to the current date
        calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DATE));

        // Get the represented date in milliseconds
        long millis1 = calendar.getTimeInMillis();

        // Calculate difference in milliseconds: today - the day the plant was born
        long diff = millis1 - userPlants.get(index).getDayBorn() ;

        // Calculate difference in days between the born day of the plant and the current day
        long diffDays = diff / (24 * 60 * 60 * 1000);

        TextView txtDaysOfLife = findViewById(R.id.txtDaysOfLife);
        txtDaysOfLife.setText("" + diffDays);
    }

    private void updateDaysOfTasks(){
        // Creates one calendars instances
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //Set it to the current date
        calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DATE));

        // Get the represented date in milliseconds
        long millis1 = calendar.getTimeInMillis();

        // Calculate difference in milliseconds: today - the day the user made the task
        long diff = millis1 - userPlants.get(index).getDateLastTask1();
        long diff2 = millis1 - userPlants.get(index).getDateLastTask2();
        long diff3 = millis1 - userPlants.get(index).getDateLastTask3();
        long diff4 = millis1 - userPlants.get(index).getDateLastTask4();

        // Calculate difference in days between the last time the user make the task and today
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long diffDays2 = diff2 / (24 * 60 * 60 * 1000);
        long diffDays3 = diff3 / (24 * 60 * 60 * 1000);
        long diffDays4 = diff4 / (24 * 60 * 60 * 1000);

        //Update the daysUntilTask variables
        UserPlant currentPlant = userPlants.get(index);
        currentPlant.setDaysUntilTask1((int)(currentPlant.getMaxDaysUntilTask1() - diffDays));
        currentPlant.setDaysUntilTask2((int)(currentPlant.getMaxDaysUntilTask2() - diffDays2));
        currentPlant.setDaysUntilTask3((int)(currentPlant.getMaxDaysUntilTask3() - diffDays3));
        currentPlant.setDaysUntilTask4((int)(currentPlant.getMaxDaysUntilTask4() - diffDays4));

        if(currentPlant.getDaysUntilTask1() < 1 || currentPlant.getDaysUntilTask2() < 1 || currentPlant.getDaysUntilTask3() < 1 || currentPlant.getDaysUntilTask4() < 1){
            imgBackground.setImageResource(R.drawable.background_dry);
        }else{
            imgBackground.setImageResource(R.drawable.background);
        }
        //Once the dates are update we have to update the top bar
        updateTopBar();
    }

    private void updateTopBar(){
        int task1Current = userPlants.get(index).getDaysUntilTask1();
        int task1Max = userPlants.get(index).getMaxDaysUntilTask1();
        setCorrectImageState(imgState1,task1Current,task1Max);

        int task1Current2 = userPlants.get(index).getDaysUntilTask2();
        int task1Max2 = userPlants.get(index).getMaxDaysUntilTask2();
        setCorrectImageState(imgState2,task1Current2,task1Max2);

        int task1Current3 = userPlants.get(index).getDaysUntilTask3();
        int task1Max3 = userPlants.get(index).getMaxDaysUntilTask3();
        setCorrectImageState(imgState3,task1Current3,task1Max3);

        int task1Current4 = userPlants.get(index).getDaysUntilTask4();
        int task1Max4 = userPlants.get(index).getMaxDaysUntilTask4();
        setCorrectImageState(imgState4,task1Current4,task1Max4);
    }
    private void setCorrectImageState(ImageView imageView, int currentDays, int maxDays){
        if(currentDays == maxDays){
            imageView.setImageResource(R.drawable.state1);
        }else if(currentDays>maxDays/2){
            imageView.setImageResource(R.drawable.state2);
        }else if(currentDays<=maxDays/2 && currentDays>0){
            imageView.setImageResource(R.drawable.state3);
        }else{
            imageView.setImageResource(R.drawable.state4);
        }
    }

}
