package com.ozturkurtulus.retrofitkotlin.adapter

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozturkurtulus.retrofitkotlin.R
import com.ozturkurtulus.retrofitkotlin.databinding.ActivityMainBinding
import com.ozturkurtulus.retrofitkotlin.databinding.RowLayoutBinding
import com.ozturkurtulus.retrofitkotlin.model.CryptoModel

private lateinit var binding:RowLayoutBinding

class RecyclerViewAdapter(private val cryptoList : ArrayList<CryptoModel>, private var listener:Listener,) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {



    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

    private val colors: Array<String> = arrayOf("#800000","#808000","#008000","#800080","#008080","#000080")


    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(cryptoModel: CryptoModel,colors: Array<String> , position: Int, listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            binding.textName.text = cryptoModel.currency
            binding.textPrice.text = cryptoModel.price
            itemView.setBackgroundColor(Color.parseColor(colors[position %6]))

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        //val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return  cryptoList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],colors,position,listener)
    }


}