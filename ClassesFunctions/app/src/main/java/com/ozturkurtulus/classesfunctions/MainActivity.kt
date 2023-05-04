package com.ozturkurtulus.classesfunctions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var name = ""

    var job = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fonksiyonu çağırdık
        test()

        //void-unit değişkene eşitlemeden kullanmalısın
        //mySum(10,20)

        //return olduğu için değişkene eşitleyebiliyorsun
        val result = myMultiply(10,20)

        //textView.text="Result= ${result}"

        /* button onclick farklı kullanımı
        button.setOnClickListener {
            println("clicked")
        }
         */

        //class
        var homer = Simpson(50,"Homer Simpson","Nuclear")
        println(homer.age)

        //object Instance
        //homer.age = 50
        //homer.job = "Nuclear"
        //homer.name = "Homer Simpson"

        //Nullability (ilk başta değer atmak istemezsek)
        //Nullable (?)

        var myString :String? = null //nullable string
        var myAge :Int? = null //nullable Int

        println(myString)

        //NullSafety 1
        if(myAge != null){
            println(myAge * 10)
        }
        else{
            println("myAge null")
        }

        //NullSafety 2
        println(myAge?.compareTo(2))

        //NullSafety 3 elvis
        val myResult = myAge?.compareTo(2)?:-100
        println(myResult)

        //myAge!! kesin gelicek null olmayacak demek
        //println(myAge!! * 10 ) // null * 10 olduğu için app çöker
    }

    //test adında fonksiyon açtık
    fun test(){

        println("test function")

    }


    //Input & Return
    fun mySum(a:Int,b:Int){
        //resultText.text ="Sonuç: ${a + b}"
    }

    fun myMultiply(x:Int,y:Int):Int {
        return x * y
    }

    /*
    fun helloKotlin(view:View){

        textView.text="hello kotlin"
    }
    */

    fun makeSimpson(view: View){

        name = nameText.text.toString()
        var age = ageText.text.toString().toIntOrNull()
        if (age == null ){
            age = 0
        }
        job = jobText.text.toString()

        val simpson = Simpson(age,name,job)

        resultText.text="Name: ${simpson.name} Age: ${simpson.age} job: ${simpson.job}"




    }

    fun scopeExample(view:View){
        //Scope
        println(name)
    }
}