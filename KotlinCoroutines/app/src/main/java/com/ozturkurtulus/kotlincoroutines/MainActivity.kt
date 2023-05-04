package com.ozturkurtulus.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
        burda arka planda uygulumamız logcat a 100 000 defa android yazdı ama uygulama çökmedi
         */
        //Light Weightness
        //kapsam
        /*
        GlobalScope.launch {
            //kaç defa tekrar edecek
            repeat(100000){
                launch {
                    println("android")
                }
            }
        }

         */


        //Scope - Kapsam coroutine nerede çalışıcak ve yaşam döngüsünü belirleyen yapı
        /**
        //Globle Scope - Tüm uygulama içinde çalışabilecek bir kapsamda açar çok fazla karşımıza çıkmaz

        //ilk GlobalScope start geldi sonra GlobalScope end geldi sonra GlobalScope geldi çünkü 5 sn gecikmesi vardı
        */

        /*
        println("GlobalScope start")
        GlobalScope.launch {
        delay(5000)
        println("GlobalScope")
        }
        println("GlobalScope end")
        */

        /**
        //runBlocking - Bloklayarak bir scope oluşturur. Önceki kodaların çalıştırılmasını durdurur ve kendini çalıştırır çok kullanılmaz
        //ilk runBlocking start geldi sonra blokladı araya girdi runBlocking geldi sonra runBlocking end geldi
         */
        /*
        println("runBlocking start")
        runBlocking {
            //coroutine başlatmak için launch kodu kullanırız
            launch {
                delay(5000)
                println("runBlocking")
            }
        }
        println("runBlocking end")

         */

        /**
         * Coroutine Scope - İçerisindeki tüm işlemleri bitirene kadar devam eder. Karşımızda genellikle bu çıkar
         *
         *
         * CoroutineScope(context) istenen context -> coroutine context
         * Dispatchers -> coroutine hangi tread içinde çalışsın
         * İlk CoroutineScope start sonra CoroutineScope end 5sn sonra da CoroutineScope çalıştı
         */
        /*
        println("CoroutineScope start")
        CoroutineScope(Dispatchers.Default).launch {
            delay(5000)
            println("CoroutineScope")
        }
        println("CoroutineScope end")
         */

        runBlocking {

            launch(Dispatchers.Main) {
                println("Main Thread ${Thread.currentThread().name}")
            }

            launch(Dispatchers.IO) {
                println("IO Thread ${Thread.currentThread().name}")
            }

            launch(Dispatchers.Default) {
                println("Default Thread ${Thread.currentThread().name}")
            }

            launch(Dispatchers.Unconfined) {
                println("Unconfined Thread ${Thread.currentThread().name}")
            }
        }
    }
}