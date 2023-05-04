package com.ozturkurtulus.kotlinalertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this@MainActivity,"welcome",Toast.LENGTH_LONG).show() //tost mesajı

    }

    fun save (view: View){

        val alert =  AlertDialog.Builder(this)

        alert.setTitle("save")
        alert.setMessage("are u sure?")
        alert.setPositiveButton("yes"){dialog, which->

            //onclick listener
            Toast.makeText(applicationContext, "saved", Toast.LENGTH_LONG).show()
        }
        alert.setNegativeButton("no"){dialog, which->

            //onclick listener
            Toast.makeText(applicationContext, "not saved", Toast.LENGTH_LONG).show()
        }

        alert.show()

    }

}