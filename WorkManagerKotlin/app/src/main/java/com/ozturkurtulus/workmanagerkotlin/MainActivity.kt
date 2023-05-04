package com.ozturkurtulus.workmanagerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = Data.Builder().putInt("intKey",1).build()

        //Telefonun ne durumda olması lazım work manager çalışması için
        val constraints = Constraints.Builder()
            //.setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(false)
            .build()

        /**
         * İşimizin hangi sıklıkla çalışması gerektiğini ayarlıyoruz ve hangi durumda çalışması gerektiğini
         val myWorkRequest : WorkRequest = OneTimeWorkRequestBuilder<RefreshDataBase>()
        .setConstraints(constraints)
        .setInputData(data)
        //.setInitialDelay(5,TimeUnit.HOURS) delaylı başlat
        //.addTag("myTag")
        .build()

        //work manager başlatma
        WorkManager.getInstance(this).enqueue(myWorkRequest)
         */

        val myWorkRequest : PeriodicWorkRequest = PeriodicWorkRequestBuilder<RefreshDataBase>(15,TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)

        //Arkaplandaki servisi gözlemliyoruz
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(myWorkRequest.id).observe(this,
            Observer{
                if (it.state == WorkInfo.State.RUNNING){
                    println("running")
                }else if(it.state == WorkInfo.State.FAILED){
                    println("Failed")
                }else if (it.state == WorkInfo.State.SUCCEEDED){
                    println("Succeeded")
                }

        })

        /**
         *Tüm workleri iptal etme
         * WorkManager.getInstance(this).cancelAllWork()
        */

        /**
         * Chaining -> sırasıyla çalıştırma, Zincirleme çalıştırma
         *  val oneTimeRequest : OneTimeWorkRequest = OneTimeWorkRequestBuilder<RefreshDataBase>()
                .setConstraints(constraints)
                .setInputData(data)
                .build()

         *  WorkManager.getInstance(this).beginWith(oneTimeRequest)
                .then(oneTimeRequest)
                .then(oneTimeRequest)
                .enqueue()
         */




    }

}