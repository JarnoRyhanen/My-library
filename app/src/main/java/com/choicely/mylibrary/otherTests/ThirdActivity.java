package com.choicely.mylibrary.otherTests;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

public class ThirdActivity extends AppCompatActivity {

    private ImageView image;
    private final static String TAG = "ThirdActivity";
    private SensorManager sensorManager;
    private Sensor gyroScopeSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        image = findViewById(R.id.third_activity_image);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroScopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener gyroscopeSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {


                Log.d(TAG, "Rotation X:" + sensorEvent.values[0] + ", Y: " + sensorEvent.values[1]);
                image.setRotationX(sensorEvent.values[0] * 10);
                image.setRotationY(sensorEvent.values[1] * 10);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        sensorManager.registerListener(gyroscopeSensorListener, gyroScopeSensor, sensorManager.SENSOR_DELAY_NORMAL);

    }
}