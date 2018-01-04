package com.nidrocus.rapidoyfurioso

import android.hardware.Sensor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.nidrocus.acelerometro.GyroscopeSensor
import com.nidrocus.acelerometro.SensorWrapper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    var sensorWrapper: SensorWrapper? = null
    var sensorFede: GyroscopeSensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_velocimetro)

        sensorWrapper = SensorWrapper(this, Sensor.TYPE_GYROSCOPE ,this::onRotationUpdate)
//        sensorFede = GyroscopeSensor(this)
//        sensorFede?.listenUpdates(this::onRotationUpdate,this::onOrientationUpdate)


    }


    private fun onOrientationUpdate(orientation: FloatArray){
        val tvOrientation = findViewById<TextView>(R.id.tv_orientation)
        tvOrientation.text = "x: ${orientation[0]}, y:${orientation[1]}, z:${orientation[2]} NN: ${orientation[3]}"
    }

    private fun onRotationUpdate(x: Float, y: Float, z: Float) {
        tv_x.text = "x: %.2f".format(x)
        tv_y.text = "y: %.2f".format(y)
        tv_z.text = "z: %.2f".format(z)

        val tvAngular = findViewById<TextView>(R.id.tv_angular)
        tvAngular.text = "angular: %.2f".format( Math.sqrt((x*x+y*y+z*z).toDouble()))
    }



    override fun onDestroy() {
        super.onDestroy()

        sensorWrapper?.die()
        sensorFede?.stopUpdates()
    }
}

