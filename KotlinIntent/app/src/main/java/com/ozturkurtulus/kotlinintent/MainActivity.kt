package com.ozturkurtulus.kotlinintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("on create called")
    }

    fun next (view: View){

        //Intent activity ler arası geçiş için kullanılır
        val intent = Intent(applicationContext, NextActivity::class.java)

        intent.putExtra("username",userNameText.text.toString())
        intent.putExtra("name",nameText.text.toString())

        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        println("on resume called")
    }

    override fun onPause() {
        super.onPause()
        println("on pause called")
    }

    override fun onStop() {
        super.onStop()
        println("on stop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("on destroy called")
    }
}