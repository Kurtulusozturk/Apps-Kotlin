package com.ozturkurtulus.kotlincoroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    /**
     *Async = Launch gibi çalışır fakat launchta değer atarken bekleme yapmaz bu yüzden indirilen değerlerin atanabilmesi için biz
     * bunları beklemek zorundayız. Bu bekleme işlemini async ile yaparız.
     */
    var username = ""
    var userage = 0

    runBlocking {
        var downloadedName = async {
            downladName()
        }

        var downloadedAge = async {
            downladAge()
        }
        /**
         * bitmesini bekler sonra değişkene atar
         */
        username = downloadedName.await()
        userage = downloadedAge.await()

        println("${username} ${userage}")
    }

}

suspend fun downladName() : String{
    delay(2000)
    val username = "Default Username:"
    println("username Download")
    return username
}

suspend fun downladAge() : Int{
    delay(4000)
    val userage = 60
    println("userage Download")
    return userage
}