package com.nidrocus.rapidoyfurioso

import android.content.Context
import android.graphics.Color
import android.hardware.SensorEvent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nidrocus.acelerometro.ProximitySensorManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       val sensorManager =  ProximitySensorManager(this)
        sensorManager.listenUpdates { event: SensorEvent? -> {
            window.decorView.setBackgroundColor(Color.RED)
        }}

    }
}
