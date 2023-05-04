package com.ozturkurtulus.kotlinoopproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var myUser = User("Kurtuluş",24)

        println(myUser.info())

        var james = Musician("James","Guitar",55)

        //private erişim sağlanılabilir ama değiştirilemez Encapsulation örneğidir
        println(james.age)
        //james.age = 15 değiştirilemez Encapsulation


        // println(james.instrument) private oluşturduğumuz için erişim sağlayamadık okunamaz değiştirilemez Encapsulation örneğidir

        println(james.returnBandName("Kurtulus"))

        println(james.returnBandName("asda"))

        //inheritance

        var lars = SuperMusician("lars","drums",55)
        println(lars.name)
        println(lars.returnBandName("Kurtulus"))
        lars.sing()


        //polymorphism

        //static polymorphism
        var math = Math()
        println( math.sum())
        println( math.sum(3,4))
        println( math.sum(3,4,5))

        //dnamic polymorphism

        val animal = Animal()
        animal.sing()

        val barley = Dog()
        barley.test()
        barley.sing()

        //abstract & interface

        var myPiano = Piano()
        myPiano.brand = "Yamaha"
        myPiano.digital = false
        println(myPiano.roomName)
        myPiano.info()

        //Lambda expressions
        fun printString(myString: String){
            println(myString)
        }

        printString("My test string")

        val testString = {myString: String -> println(myString) }

        testString("My Lambda String")

        val multiplyLambda = {a:Int, b:Int-> a*b}
        println(multiplyLambda(5,6))

        val multiplyLambda2 : (Int,Int) -> Int = {a,b -> a*b}
        println(multiplyLambda2(5,9))

        //asynchrnous

        //callback fun, listener fun, completion fun

        fun downloadMusicians(url:String, completion:(Musician) -> Unit ){
            //url -> download
            //data
            val kirkHamet = Musician("kirk","Guitar",60)
            completion(kirkHamet)

        }

        downloadMusicians("metellica.com", {musician ->
            println(musician.name)
        })

    }
}