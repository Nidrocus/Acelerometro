package com.nidrocus.rapidoyfurioso

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nidrocus.acelerometro.GyroscopeSensor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       //val sensorManager =  ProximitySensorManager(this)
        //sensorManager.listenUpdates( {event ->  textview.setText("Objeto cercano detectado!") } )


        val gyroscope = GyroscopeSensor(this)
        gyroscope.listenUpdates( {event -> {
            tv_x.setText("x:${event!!.values[0]}")
            tv_y.setText("y:${event!!.values[1]}")
            tv_z.setText("z:${event!!.values[2]}")
        }})

    }
}
