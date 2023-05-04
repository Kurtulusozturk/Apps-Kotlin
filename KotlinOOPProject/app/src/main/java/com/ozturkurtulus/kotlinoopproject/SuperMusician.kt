package com.ozturkurtulus.kotlinoopproject

//kalıtım aldığımız sınıfın constructor ları aldık

class SuperMusician(name: String, instrument: String, age: Int) : Musician(name, instrument, age) {

    fun sing(){
        println("nothing else matters")
    }

}