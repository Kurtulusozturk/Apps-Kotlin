package com.ozturkurtulus.kotlinoopproject

//constructor class içinde değil parametre olarak yazıldı

open class Musician(name: String, instrument: String, age: Int) {

    //Encapsulation
    var name: String? = name
        private set
        get

    //Encapsulation
    private var instrument: String? = instrument

    var age: Int? = age
        private set
        get

    private val bandName: String = "Metallica"

    fun returnBandName(password:String):String{
        if (password == "Kurtulus"){
            return bandName
        }
        else{
            return "wrong password"
        }
    }

}