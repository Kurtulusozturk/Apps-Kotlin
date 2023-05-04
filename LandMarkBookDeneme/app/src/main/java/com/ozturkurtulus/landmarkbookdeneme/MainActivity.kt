package com.ozturkurtulus.landmarkbookdeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozturkurtulus.landmarkbookdeneme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var landMarklist: ArrayList <LandMark>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        landMarklist =ArrayList<LandMark>()

        //data
        val pisa = LandMark("Pisa","Italy", R.drawable.pisa)
        val colosseum = LandMark("Colosseum","Italy",R.drawable.collesium)
        val eiffel = LandMark("Eiffel","France", R.drawable.eiffel)
        val londonBridge = LandMark("London Bridge","England",R.drawable.londonbridge)

        landMarklist.add(pisa)
        landMarklist.add(colosseum)
        landMarklist.add(eiffel)
        landMarklist.add(londonBridge)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        val landmarkAdapter = LandmarkAdapter(landMarklist)

        binding.recyclerview.adapter = landmarkAdapter



        /*
        //Mapping adapter son kelimeler

        //Adapter:

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,landMarklist.map { landMark -> landMark.name })

        binding.listView.adapter = adapter

        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val intent = Intent(MainActivity@this,DetailsActivity::class.java)
            intent.putExtra("LandMark",landMarklist.get(position))
            startActivity(intent)
        }
           BURAYI KULLANMADIK ÇÜNKÜ HERBİRİNE XML DOSYASI AÇIYOR
        */

    }
}