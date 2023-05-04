package com.ozturkurtulus.artbookkotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Size
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ozturkurtulus.artbookkotlin.databinding.ActivityArtBinding
import java.io.ByteArrayOutputStream

class ArtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtBinding
    private lateinit var activityResultLauncher :ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    var selectedBitmap : Bitmap? = null
    private lateinit var database :SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArtBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        database = this.openOrCreateDatabase("Arts", MODE_PRIVATE,null)

        registerLauncher()

        //eğer yeni birşey eklenince xml nasıl olsun eskisi gelince nasıl olsun
        val intent = intent
        val info = intent.getStringExtra("info")
        if (info.equals("new")){

            binding.artNameText.setText("")
            binding.artistNameText.setText("")
            binding.yearText.setText("")
            binding.save.visibility = View.VISIBLE
            binding.imageView.setImageResource(R.drawable.selectimage)

        }else{

            binding.save.visibility = View.INVISIBLE
            val selectedId = intent.getIntExtra("id",0)

            val cursor =database.rawQuery("SELECT * FROM arts WHERE id=?", arrayOf(selectedId.toString()))

            val artNameIx = cursor.getColumnIndex("artname")
            val artistNameIx = cursor.getColumnIndex("artistname")
            val yearIx = cursor.getColumnIndex("year")
            val imageIx = cursor.getColumnIndex("image")

            while (cursor.moveToNext()){
                binding.artNameText.setText(cursor.getString(artNameIx))
                binding.artistNameText.setText(cursor.getString(artistNameIx))
                binding.yearText.setText(cursor.getString(yearIx))

                val byteArray = cursor.getBlob(imageIx)
                val bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
                binding.imageView.setImageBitmap(bitmap)
            }
            cursor.close()

        }

    }

    fun  save(view:View){

        val artname = binding.artNameText.text.toString()
        val artistname = binding.artistNameText.text.toString()
        val year = binding.yearText.text.toString()

        println(artname)

        if (selectedBitmap != null){
            val smallBitmap = makeSmallerBitmap(selectedBitmap!!,300)

            val outputStream = ByteArrayOutputStream()
            smallBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            val byteArray = outputStream.toByteArray()

            try {

                //val database = this.openOrCreateDatabase("Arts", MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS arts(id INTEGER PRIMARY KEY, artname VARCHAR, artistname VARCHAR, year VARCHAR, image BLOB)")

                //database e değişken ile kaydetme yöntemi
                val sqlString = "INSERT INTO arts(artname, artistname, year, image) VALUES (?,?,?,?)"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1,artname)
                statement.bindString(2,artistname)
                statement.bindString(3,year)
                statement.bindBlob(4,byteArray)
                statement.execute()


            }catch (e:java.lang.Exception){
                e.printStackTrace()
            }
            val intent = Intent(this@ArtActivity,MainActivity::class.java)
            //arkda çalışan tüm activityleri kapatma kodu
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }

    }


    // resimleri küçültmek için kullanacağımız fonksiyon
    private  fun makeSmallerBitmap(image:Bitmap, maximumSize:Int):Bitmap{

        var width =image.width
        var height = image.height

        //genişliği uznuluğa böler 1 den büyükse yatay  1 den küçükse görselim dikey 1 e eşit çıkarsa görselim kare olur
        var bitmapRatio : Double = width.toDouble() /height.toDouble()

        if (bitmapRatio>1){
            //landscape
            width = maximumSize
            val scaledheight = width/ bitmapRatio
            height = scaledheight.toInt()

        }else{
            //portrait
            height = maximumSize
            val scaledwidth = height/ bitmapRatio
            width = scaledwidth.toInt()

        }

        return Bitmap.createScaledBitmap(image,width,height,true)

    }

    fun  selectImage(view:View){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            //Android 33+ sonrası ise --> READ_MEDIA IMAGES
            //daha önceden izin verilmiş mi manifestten gelen izin isteği sonucunda level: Dangerous

            if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){

                //izin yoksa

                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_MEDIA_IMAGES)){
                    //rational
                    // kullanıcı ilkte izin vermez ise snackbar ile izin verene kadar istekte bulunuyoruz
                    Snackbar.make(view,"Permission required",Snackbar.LENGTH_INDEFINITE).setAction("Accept",View.OnClickListener {
                        //request permission
                        permissionLauncher.launch((Manifest.permission.READ_MEDIA_IMAGES))
                    }).show()
                }else{
                    //request permission
                    permissionLauncher.launch((Manifest.permission.READ_MEDIA_IMAGES))
                }

            }
            else{
                //gallery e git
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }else{
            //Android 32 --> READ_EXTERNAL_STORGE
            //println("çalıştı")
            //daha önceden izin verilmiş mi manifestten gelen izin isteği sonucunda level: Dangerous

            if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                //izin yoksa

                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                    //rational
                    // kullanıcı ilkte izin vermez ise snackbar ile izin verene kadar istekte bulunuyoruz
                    Snackbar.make(view,"Permission required",Snackbar.LENGTH_INDEFINITE).setAction("Accept",View.OnClickListener {
                        //request permission
                        permissionLauncher.launch((Manifest.permission.READ_EXTERNAL_STORAGE))
                    }).show()
                }else{
                    //request permission
                    permissionLauncher.launch((Manifest.permission.READ_EXTERNAL_STORAGE))
                }

            }
            else{
                //gallery e git
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }


    }

    private fun registerLauncher(){

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode == RESULT_OK){
                val intentFromResult = result.data
                if (intentFromResult != null){
                    val imageData = intentFromResult.data
                    //binding.imageView.setImageURI(imageData)
                    if (imageData != null) {
                        try {
                            //ImageDecoder android 28 üzerinde çalıştığı için kontrol yapıyoruz
                            if(Build.VERSION.SDK_INT >= 28) {
                                val source = ImageDecoder.createSource(this@ArtActivity.contentResolver, imageData)
                                selectedBitmap = ImageDecoder.decodeBitmap(source)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            }else{
                                selectedBitmap = MediaStore.Images.Media.getBitmap(contentResolver,imageData)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            }
                        } catch (e: java.lang.Exception) {
                                e.printStackTrace()
                        }
                    }
                }
            }
        }



        permissionLauncher =registerForActivityResult(ActivityResultContracts.RequestPermission()){result ->
            println(result)
            if (result){
            //izin verildiyse
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)

            }else{
                Toast.makeText(this@ArtActivity,"Permission needed!",Toast.LENGTH_LONG).show()
            }

        }

    }

}