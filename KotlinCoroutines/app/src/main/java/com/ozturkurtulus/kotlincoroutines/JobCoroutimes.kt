package com.ozturkurtulus.kotlincoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    /**
     *Job = Launch ları kontrol etmek için kullanırız. Kapatılabilir bitişinde ne yapılacağı gibi şeyler ayarlanabilir.
     */

    runBlocking {
        val myJob = launch {
            delay(2000)
            println("job")
            val secondJob = launch {
                delay(4000)
                println("Second job")
            }
        }

        myJob.invokeOnCompletion {
            println("My Job done!!")
        }

        myJob.cancel()
    }

}