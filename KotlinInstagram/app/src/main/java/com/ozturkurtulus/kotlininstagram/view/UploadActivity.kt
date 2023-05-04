package com.ozturkurtulus.kotlininstagram.view

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.ozturkurtulus.kotlininstagram.databinding.ActivityUploadBinding
import java.util.UUID

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding

    //bunları galeriye erişim için kullanacağız
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>//start activity for result için kullanacağız o yüzden intent
    private lateinit var permLauncher: ActivityResultLauncher<String>//Read External Storage string olduğu için
    var selectedPicture : Uri? = null

    private lateinit var auth : FirebaseAuth
    private lateinit var firestore : FirebaseFirestore
    private lateinit var storage : FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUploadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registerLauncher()

        auth = Firebase.auth
        firestore = Firebase.firestore
        storage = Firebase.storage
    }

    fun uploadClicked (view: View){

        //bu her yapıldığında random string verir ve database de dosyalarımızı saklamamıza yardımcı olur
        val uuid = UUID.randomUUID()
        val imagename = "$uuid.jpg"

        val reference = storage.reference //reference firebase storage kısmı ana kısım
        val imagereference = reference.child("images").child(imagename)

        if (selectedPicture != null){
            imagereference.putFile(selectedPicture!!).addOnSuccessListener {
                //download url-> firestore
                //resmin urlsini aldık bir sonraki adımda veritabanına kayıt işlemi gerçekleştireceğiz
                val uploadPictureReference = storage.reference.child("images").child(imagename)
                uploadPictureReference.downloadUrl.addOnSuccessListener {
                    val downloadUrl = it.toString()
                    // veri tabanına kayda başlıyoruz
                    val postMap = hashMapOf<String,Any>()
                    postMap.put("downloadUrl",downloadUrl)
                    postMap.put("user_email",auth.currentUser!!.email!!)
                    postMap.put("comment",binding.uploadCommentText.text.toString())
                    postMap.put("date",Timestamp.now()) //güncel zamanını verir

                    firestore.collection("Posts").add(postMap).addOnSuccessListener {

                        finish()

                    }.addOnFailureListener{

                        Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
                    }
                }

            }.addOnFailureListener{

                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }



        //binding.uploadCommentText.text

    }
    fun imageViewClicked (view: View){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU) {
            //İZİN İŞLEMLERİ arayüzü izin istemeler falan registerLauncher empoze edildi
            if (ContextCompat.checkSelfPermission
                    (
                    this,
                    android.Manifest.permission.READ_MEDIA_IMAGES
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //iznimiz yoksa
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        android.Manifest.permission.READ_MEDIA_IMAGES
                    )
                ) {
                    //izin istenmiş ama verilmemiş sonra
                    Snackbar.make(view, "Permission need", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Give permission", View.OnClickListener {
                            //tekrar izin iste
                            permLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)

                        }).show()
                } else {
                    //ilk defa izin istenmesi
                    permLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)

                }

            } else {
                //izin alındıysa
                val intentToGallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }else{
            //İZİN İŞLEMLERİ arayüzü izin istemeler falan registerLauncher empoze edildi
            if (ContextCompat.checkSelfPermission
                    (
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //iznimiz yoksa
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    //izin istenmiş ama verilmemiş sonra
                    Snackbar.make(view, "Permission need", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Give permission", View.OnClickListener {
                            //tekrar izin iste
                            permLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                        }).show()
                } else {
                    //ilk defa izin istenmesi
                    permLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                }

            } else {
                //izin alındıysa
                val intentToGallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }
    }

    //İZİN İŞLEMLERİ Fonksiyonu
    private fun registerLauncher(){
        //seçilen resmin konumunu ve seçildikten sonra ne olacağını ayarladığımız kod
        activityResultLauncher =registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == RESULT_OK){

                val intentFromResult = result.data
                if (intentFromResult != null){
                    selectedPicture = intentFromResult.data //URI aldık yani seçilen resim
                    selectedPicture?.let {
                        binding.uploadImageView.setImageURI(it)
                    }
                 }
            }
        }


        permLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){result->
            if (result){

                //izin verilir ise
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }else{

                //izin verilmez ise
                Toast.makeText(this@UploadActivity,"Permission need",Toast.LENGTH_LONG).show()
            }
        }
    }



}