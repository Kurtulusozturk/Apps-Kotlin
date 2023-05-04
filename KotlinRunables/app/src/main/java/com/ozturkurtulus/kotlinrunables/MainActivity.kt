package com.ozturkurtulus.kotlinrunables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var number = 0
    var runable : Runnable = Runnable{}
    var handle : Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun start(view: View){

        number = 0

        runable = object :Runnable{
            override fun run() {

                number=number+1
                textView.text = "Time: $number"

                handle.postDelayed(this,1000)

            }
        }
        handle.post(runable)
       /*bu şekilde saydırılığında uygulama çöktü çünkü önplanda uygulamada çalıştı ve çöktü runnable arka planda çalışır çökertmez
        while (number<100){
            number = number+1
            textView.text = "Time: $number"
            Thread.sleep(1000)
        }
        */
    }
    fun stop(view: View){
        handle.removeCallbacks(runable)
        textView.text = "Time: 0"
    }
}