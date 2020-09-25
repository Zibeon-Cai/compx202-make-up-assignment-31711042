package com.example.compx202_make_upassignment_31711042;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {


    SensorManager sensorMgr;
    Sensor accelerometer;

    private float xAcceleration;
    private float yAcceleration;
    private RoundBallView mRoundBallView;
    private AimsView aimsView;
    private int[] position;
    private int aimsViewR;

    int chance = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mRoundBallView = findViewById(R.id.roundball);
        aimsView = findViewById(R.id.aimsView);
        position = new int[2];

        sensorMgr = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Start the game now")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startGame();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).create().show();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        aimsView.getLocationOnScreen(position);
        aimsViewR =  aimsView.getWidth();
        Log.e("x",position[0]+"");
        Log.e("x",position[1]+"");
        Log.e("x",aimsViewR+"");
    }

    protected void startGame() {
        sensorMgr.registerListener(listener, accelerometer,
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void overGame() {
        sensorMgr.unregisterListener(listener, accelerometer);
    }
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            xAcceleration = event.values[0]; //x
            yAcceleration = event.values[1]; //y
            mRoundBallView.refreshUI(xAcceleration, yAcceleration, new RoundBallView.onBallStatusListener() {
                @Override
                public void onLocation(float x, float y) {
                    if (x<0||y<0||x>Utils.getScreenWidth(MainActivity.this)||y>Utils.getScreenHeight(MainActivity.this)){
                        overGame();
                        mRoundBallView.init();
                        chance();
                    }else{
                        if (x>position[0]&&x<position[0]+aimsViewR&&y>position[1]&&y<position[1]+aimsViewR){
                            overGame();
                            mRoundBallView.init();
                            win();
                        }
                    }


                }
            });
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {}
    };

    private void win() {

        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("success",true);
        startActivity(intent);
        finish();
    }

    private void lost() {
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("success",false);
        startActivity(intent);
        finish();
    }
    private void chance() {
        if (chance>1){
            chance--;
            alert();
        }else{
            lost();
        }
    }

    private void alert() {
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("There are "+chance+" more chances, do you continue")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startGame();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).create().show();
    }
}