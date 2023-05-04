package com.ozturkurtulus.coroutineexception

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Handler kullanmak sağlıklı hata aldığında uygulama çökmez
         * ama şöyle bir durum var bir launch ta çökme olursa diğerleride o coroutine iptal olur
         * bununsa önüne geçmek için supervisorScope kullanmalıyız
         */
        val handler = CoroutineExceptionHandler {coroutineContext, throwable ->
            println("exception:"+throwable.localizedMessage)
        }
        lifecycleScope.launch(handler){
            supervisorScope {
                launch {
                    throw Exception("error")
                }
                launch {
                    delay(5000)
                    println("this is executed")
                }
            }
        }
        CoroutineScope(Dispatchers.Main + handler).launch {
            launch {
                throw Exception("error in coroutineScope")
            }
        }
    }
}