package com.ozturkurtulus.kotlincoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main (){

    /***
     * Aynı Launch içinde farklı Dispatchers, thread lar ile çalışmak
     */

    runBlocking {
        launch(Dispatchers.Default) {
            println("Context: ${coroutineContext}")
            withContext(Dispatchers.IO){
                println("Context: ${coroutineContext}")
            }
        }
    }

}