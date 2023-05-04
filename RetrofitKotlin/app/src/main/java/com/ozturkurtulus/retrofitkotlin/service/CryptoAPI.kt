package com.ozturkurtulus.retrofitkotlin.service

import io.reactivex.Observable
import com.ozturkurtulus.retrofitkotlin.model.CryptoModel
import retrofit2.http.GET

interface CryptoAPI {

    //Get, Post, Update, Delete

    //https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    //kullanıcının arayüzünü bloklamadan internetten yapılan işlem= Call sonucunda bana List şeklinde bana CryptoModel getir
    //fun getData(): Call <List<CryptoModel>>
    fun getData(): Observable<List<CryptoModel>>
}