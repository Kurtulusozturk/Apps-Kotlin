package com.ozturkurtulus.landmarkbookdeneme

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozturkurtulus.landmarkbookdeneme.databinding.RecyclerRowBinding

//landmarklist parametresi istendi dizi çekildi --------------- Adapter olduğunu tanımladık
class LandmarkAdapter(val landmarkList : ArrayList<LandMark>) : RecyclerView.Adapter<LandmarkAdapter.landmarkHolder>() {

    class landmarkHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }
    //ilk oluşunca ne olcak
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): landmarkHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return landmarkHolder(binding)

    }
    //kaç tane olacak
    override fun getItemCount(): Int {

    return landmarkList.size

    }
    //bağlandıktan sonra ne olacak
    override fun onBindViewHolder(holder: landmarkHolder, position: Int) {

        //listede görünecek isimleri belirledik
        holder.binding.recyclertextview.text= landmarkList.get(position).name

        //liste itemlerine tıklandığında ne yapılcağını yazdık
        holder.itemView.setOnClickListener {
            //intent dataların activity arası transferlerini gerçekleştirir
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            //listedeki itemin indexsini bulup detailactivity ile haberleştik
            intent.putExtra("LandMark",landmarkList.get(position))
            //başlama yapısı
            holder.itemView.context.startActivity(intent)
        }

    }

}