package com.ozturkurtulus.kotlinsqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //hata kontrol sistemi
        try{

            val myDatabase = this.openOrCreateDatabase("Musicians", Context.MODE_PRIVATE,null)

            //veritabanı üzerinde sql kodu çalıştırmak istediğimizde yazdığımız kod içide sql kodu
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR, age INT)")

            //ilk kayıt
            //myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('Ahmet',50)")
            //myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('Mehmet',50)")

            //sorgu çekmek selection args filtreli veri çekmek için
            val cursor = myDatabase.rawQuery("SELECT * FROM musicians",null)

            //satır indexlerini veren kodlar
            val nameIx = cursor.getColumnIndex("name")
            val ageIX = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")

            //while tek tek tüm hücrelere gider
            while(cursor.moveToNext()){
                println("Name: " + cursor.getString(nameIx))
                println("Age: " + cursor.getInt(ageIX))
                println("id: " + cursor.getInt(idIx))
            }
            println("----------------Filtreleme - Update - Delete - işlemleri----------------------")

            //myDatabase.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Mehmet'") UPDATE
            //myDatabase.execSQL("UPDATE musicians SET name = 'Mehmet Diyar' WHERE id = 1") UPDATE

            //myDatabase.execSQL("DELETE FROM musicians WHERE name = 'Ahmet'") DELETE

            val cursor2 = myDatabase.rawQuery("SELECT * FROM musicians WHERE id = '1'",null)

            //satır indexlerini veren kodlar
            val nameIx2 = cursor2.getColumnIndex("name")
            val ageIX2 = cursor2.getColumnIndex("age")
            val idIx2 = cursor2.getColumnIndex("id")

            //while tek tek tüm hücrelere gider
            while(cursor2.moveToNext()){
                println("Name: " + cursor2.getString(nameIx2))
                println("Age: " + cursor2.getInt(ageIX2))
                println("id: " + cursor2.getInt(idIx2))
            }

            cursor2.close()

        }catch (e:Exception){
            //hatanın detaylarını logcate yazar
            e.printStackTrace()
        }
    }
}