package com.ozturkurtulus.kotlincoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    /**
     * Dispatchers.
     * Type:
     * Default    -> Cpu Intensive Yoğun işlemlerde kullanılır. (Görüntü işleme, yüzbintane kelimeyi alfabetik dizme gibi)
     * IO         -> Input/Output işlemleri Network işlemlerinde kullanılır.
     * Main       -> Main Thread UI user interface de kullanılır.
     * Unconfined -> Inherited Dispatcher Miras alarak içerisinde çalıştığı yere göre kendi ayarlar.
     */
/*
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
 */

}