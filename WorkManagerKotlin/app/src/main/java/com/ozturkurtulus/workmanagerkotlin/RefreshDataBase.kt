package com.ozturkurtulus.workmanagerkotlin

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class RefreshDataBase(val context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {
    override fun doWork(): Result {

        //Work manager içinde ne yapmak istiyorsak bunun altında yapmamız lazım
        val getData = inputData
        val myNumber = getData.getInt("intKey",0)
        refreshDatabase(myNumber)
        return Result.success()
    }
    private fun refreshDatabase(myNumber : Int){
        val sharedPreferences = context.getSharedPreferences(
            "com.ozturkurtulus.workmanagerkotlin",Context.MODE_PRIVATE)
        var savedNumber = sharedPreferences.getInt("myNumber",0)
        savedNumber = savedNumber + myNumber
        println(savedNumber)
        sharedPreferences.edit().putInt("myNumber",savedNumber).apply()

    }
}