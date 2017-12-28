package com.nidrocus.acelerometro

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

/**
 * Created by leandro on 28/12/17.
 */


typealias UpdateListener = (x: Float, y: Float, z: Float) -> Unit
class SensorWrapper constructor(context: Context, type: Int, private val listener: UpdateListener) {

    val TAG =  "SensorWrapper"

    private val manager : SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as (SensorManager)
    private val sensor : Sensor
    private val sensorListener : SensorEventListener


    init {
        this.sensor = manager.getDefaultSensor(type)
        this.sensorListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // No me importa
            }

            override fun onSensorChanged(event: SensorEvent?) {
                if (event != null) {
                    listener(event.values[0],event.values[1], event.values[2])
                }
            }
        }

        manager.registerListener(sensorListener,sensor,2*1000 *1000)
    }

    fun die () {
        manager.unregisterListener(sensorListener,sensor)
    }
}

