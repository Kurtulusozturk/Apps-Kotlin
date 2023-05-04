package com.ozturkurtulus.kotlinlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables

        //Integer

        var x = 5
        var y = 4
        println(x * y)

        var age = 35
        println(age / 7)

        // Defining
        val myInteger : Int

        // Initialize
        myInteger = 10

        val b : Int = 5

        //Constant- Sabit değeri değişmeyen

        val name = "Kurtuluş"

        val a = 5

        //Double(64bit)-Float(32bit)

        val pi = 3.14

        val myfloat = 3.14f

        var myAge: Double
        myAge = 23.0

        //string

        var myString: String
        myString = "ali"

        val names = "kıraç"

        val fullName = myString + " " + names

        //boolean

        var myBoolean: Boolean = true
        myBoolean = false

        //Converting

        var convert = 5
        convert.toFloat() //5.0 olur float olur

        var input = "20"
        input.toInt()

        //Array dizilerin boyutu başta belirlenir sonradan ekleme yapılamaz

        val myArray = arrayOf("Kurtulus","Rusen","Öztürk")

        val mixedArray = arrayOf("Ahmet",0) //böyle de kullanılabilir

        //List-ArrayList sonradan ekleme yapılabilir

        val myMusician = arrayListOf<String>("s","b")
        myMusician.add("lars") //ekleme işlemi
        myMusician.add(0,"rob") //değiştirme işlemi

        val myArrayList = ArrayList <Int>()
        myArrayList.add(200)
        myArrayList.add(1)

        val mixedArrayList = ArrayList <Any>()
        mixedArrayList.add("a")
        mixedArrayList.add(12.5)
        mixedArrayList.add(11)
        mixedArrayList.add(true)

        //Set bir elemanın bir dizi içinde birden fazla olamayacağı array tipi

        val myExampleArray = arrayOf(1,1,2,3,4)

        println("first element: ${myExampleArray[0]}")

        val mySet = setOf<Int>(1,1,2,3)

        val myStringSet = HashSet <String>()

        myStringSet.add("Kurtulus")
        myStringSet.add("Rusen")
        myStringSet.add("Rusen")

        println(mySet.size)//3

        mySet.forEach{println(it)}// 1 2 3

        println(myStringSet.size) //2

        //Map - HashMap - sözlük

        var fruitCalorieArray = hashMapOf<String,Int>()

        fruitCalorieArray.put("apple",100)
        fruitCalorieArray.put("banana",200)
        println(fruitCalorieArray["apple"])

        val myHashMap = hashMapOf<String,String>()
        val myNewMap = hashMapOf<String,Int>("a" to 1,"b" to 2, "c" to 3)
        println(myNewMap["c"])

        //Operator
        var m =5
        println(m)
        m++
        println(m)
        m--
        println(m)
        var n = 7
        println(n>m)
        println(n>m && 1>2)

        //if
        val myNumberAge=32
        if(myNumberAge<30){
            println("Küçük 30")
        }
        else if(myNumberAge>=40 && myNumberAge <50){
            println("40-50")
        }
        else{
            println("Büyük 30")
        }

        //Switch-When
        val day = 3
        var dayString = ""

        when(day){
            1->dayString = "Monday"
            2->dayString = "Tuesday"
            3->dayString = "Wednesday"
            else->dayString = ""
        }
        println(dayString)

        //Loops

        //For loop
        val myArrayOfNumbers = arrayOf(12,15,18,21,24,27,30,33)
        val q = myArrayOfNumbers[0]/3*5

        for(number in myArrayOfNumbers){
            val z = number/3*5
            println("sonuç:"+z)
        }

        for(b in 0..9){
            println(b)
        }

        val myStringArrayList = ArrayList<String>()
        myStringArrayList.add("q")
        myStringArrayList.add("a")
        myStringArrayList.add("c")

        for (str in myStringArrayList){
            println(str)
        }

        //While
        var j = 0

        while (j<10){

            println(j)
            j = j + 1
        }
    }

}