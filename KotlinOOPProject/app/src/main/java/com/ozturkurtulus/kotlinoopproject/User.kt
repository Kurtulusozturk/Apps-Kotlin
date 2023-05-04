package com.ozturkurtulus.kotlinoopproject

class User: People {

    var name:String? = null
    var age:Int? = null

    constructor(nameInput:String,ageInput:Int){
        this.name = nameInput
        this.age = ageInput

    }

    //çağırılan ilk fonksiyonlardan biri
    init {

    }

}