package com.ozturkurtulus.kotlincoroutines

import kotlinx.coroutines.*

fun main(){

    /**
     * Suspend fonksiyon içinde coroutine çalıştırılabilen fonksiyonlardır.
     * Bu fonksiyonları istediğimiz zaman durdurup devam ettrilebiliyor.
     * Bu fonksiyonlar noraml fonksiyonlar içinde sadece coroutines içinde çalışır.
     */
    runBlocking {
        delay(2000)
        println("run blocking")
        myFunction()
    }


}
suspend fun myFunction(){
    coroutineScope {
        delay(4000)
        println("suspend function")
    }
}