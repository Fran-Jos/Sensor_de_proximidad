package com.example.practica

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var proximitySensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val proximidad = event.values[0]
        var img1 = findViewById<ImageView>(R.id.imageView)
        if (proximidad < proximitySensor.maximumRange) {
            // El objeto está cerca

            img1.background = getDrawable(R.drawable.monito)
        } else {
            // El objeto está lejos
            img1.background = getDrawable(R.drawable.oso)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }
}

