package com.nidrocus.rapidoyfurioso

import android.hardware.SensorEvent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nidrocus.acelerometro.GyroscopeSensor
import com.nidrocus.customviews.Velocimetro
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var gyroscope: GyroscopeSensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_velocimetro)


        //val sensorManager =  ProximitySensorManager(this)
        //sensorManager.listenUpdates( {event ->  textview.setText("Objeto cercano detectado!") } )


        gyroscope = GyroscopeSensor(this)
        gyroscope.listenUpdates(this::onGyroscopeUpdate)

        findViewById<Velocimetro>(R.id.velocimetro).updatePosition(180f)
    }


    private fun onGyroscopeUpdate(event: SensorEvent?) {
//        tv_x.text = "x:${event!!.values[0]}"
//        tv_y.text = "y:${event!!.values[1]}"
//        tv_z.text  = "z:${event!!.values[2]}"
    }


    override fun onDestroy() {
        super.onDestroy()
        gyroscope.stopUpdates()
    }
}

