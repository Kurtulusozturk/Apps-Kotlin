package com.ozturkurtulus.retrofitkotlin.model

import com.google.gson.annotations.SerializedName

//veri çekilen ve bu verilerin işlendiği sınıflar

data class CryptoModel(
    //json içindeki datanın ismi görevi değişken ismi farklı olursa bu kullanılır
    //@SerializedName("currency")
    //değişken ismi
    val currency: String,

    //@SerializedName("price")
    val price: String ) {

}