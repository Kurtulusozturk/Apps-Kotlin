package com.ozturkurtulus.kotlinoopproject

class Dog: Animal() {

    fun test(){
        super.sing()
    }
    //override fonksiyonu ezmek gibi kalıtım aldığı sınıftaki fonksiyonu farklı kullanıyor
    override fun sing(){
        println("dog class")
    }

}