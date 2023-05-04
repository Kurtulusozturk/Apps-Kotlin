package com.ozturkurtulus.kotlincoroutines

import kotlinx.coroutines.*

fun main(){
    /**
     * iç içe geçmiş coroutines
     * ilk 3sn sonra CoroutineScope sonra 5sn sonra runBlocking çalıştı
     */
    runBlocking {
        launch {
            delay(5000)
            println("runBlocking")
        }
        coroutineScope{
            launch {
                delay(3000)
                println("CoroutineScope")
            }
        }
    }
}