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

typealias OrientationUpdateListener = ((orientation: FloatArray) -> Unit)?
class GyroscopeSensor constructor(context: Context) {

    val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as (SensorManager)
    val TAG = "GyroscopeSensor"
    var gyroscopeSensor: Sensor?
    var accelerometerSensor: Sensor?
    var magneticFieldSensor: Sensor?
    var sensorEventListener: SensorEventListener? = null

    var gravityData: FloatArray? = null
    var magneticData: FloatArray? = null

    var RMatrix = FloatArray(16, { i -> 0f })
    var IMatrix = FloatArray(16, { i -> 0f })

    var orientation = FloatArray(4, { i -> 0f })


    init {
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)


    }


    fun listenUpdates(updateListener: UpdateListener, orientationListener: OrientationUpdateListener = null) {
        sensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

            }

            override fun onSensorChanged(event: SensorEvent?) {
                if (event != null) {
                    when (event.sensor.type) {
                        Sensor.TYPE_MAGNETIC_FIELD -> {
                            magneticData = floatArrayOf(event.values[0], event.values[1], event.values[2])
                        }
                        Sensor.TYPE_ACCELEROMETER -> {
                            gravityData = floatArrayOf(event.values[0], event.values[1], event.values[2])
                        }
                        Sensor.TYPE_GYROSCOPE -> {
                            updateListener(event.values[0], event.values[1], event.values[2])
                        }
                    }

                    if (matrixDataAvailable()) {
                        SensorManager.getRotationMatrix(RMatrix, IMatrix, gravityData, magneticData)
                        SensorManager.getOrientation(RMatrix, orientation)
                        if (orientationListener != null) orientationListener(orientation)
                    }
                }
            }
        }
        registerListeners()
    }


    private fun matrixDataAvailable(): Boolean {
        return gravityData?.size == 3 && magneticData?.size == 3
    }

    private fun registerListeners() {
        sensorManager.registerListener(sensorEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(sensorEventListener, magneticFieldSensor, SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_UI)
    }


    fun stopUpdates() {
        if (sensorEventListener != null) {
            sensorManager.unregisterListener(sensorEventListener)
            sensorEventListener = null
        }
    }
}