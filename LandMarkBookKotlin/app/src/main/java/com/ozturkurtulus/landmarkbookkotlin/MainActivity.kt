package com.ozturkurtulus.landmarkbookkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.ozturkurtulus.landmarkbookkotlin.databinding.ActivityDetailsBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var landMarklist: ArrayList <LandMark>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //data
        val pisa = LandMark("Pisa","Italy", R.drawable.pisa)
        val colosseum = LandMark("Colosseum","Italy",R.drawable.collesium)
        val eiffel = LandMark("Eiffel","France", R.drawable.eiffel)
        val londonBridge = LandMark("London Bridge","England",R.drawable.londonbridge )

        landMarklist.add(pisa)
        landMarklist.add(colosseum)
        landMarklist.add(eiffel)
        landMarklist.add(londonBridge)

        //Adapter:

        val adapter =ArrayAdapter(this,android.R.layout.simple_list_item_1,landMarklist)

        //binding.listview.adapter = adapter
    }
}