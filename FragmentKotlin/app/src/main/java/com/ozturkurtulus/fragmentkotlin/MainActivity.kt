package com.ozturkurtulus.fragmentkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun fragment1(view : View){

        val fragmeManager =supportFragmentManager
        val fragmentTransaction = fragmeManager.beginTransaction()

        val fragment1 = Fragment1()
        fragmentTransaction.replace(R.id.frameLayout,fragment1).commit()

    }
    fun fragment2(view : View){

        val fragmeManager =supportFragmentManager
        val fragmentTransaction = fragmeManager.beginTransaction()

        val fragment2 = Fragment2()
        fragmentTransaction.replace(R.id.frameLayout,fragment2).commit()


    }

}