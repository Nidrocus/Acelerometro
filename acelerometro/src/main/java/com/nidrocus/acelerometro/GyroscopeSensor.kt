package com.nidrocus.acelerometro

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

/**
 * Created by Federico Torres on 27/12/2017.
 */
class GyroscopeSensor constructor(context : Context) {

    val sensorManager : SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as (SensorManager)
    val TAG =  "GyroscopeSensor"
    var sensor : Sensor?
    var proximityListener : SensorEventListener? = null


    init {
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        Log.e(TAG,"Sensor no disponible")
    }


    fun listenUpdates(onUpdate: (event: SensorEvent?) -> Any ) {
        proximityListener = object : SensorEventListener {
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

            }

            override fun onSensorChanged(event: SensorEvent?) {
                if(event != null){
                    onUpdate(event)
                }
            }
        }

        sensorManager.registerListener(proximityListener,sensor,2*1000 *1000)
    }


    fun stopUpdates(){
        if(proximityListener != null){
            sensorManager.unregisterListener(proximityListener)
            proximityListener = null
        }
    }
}