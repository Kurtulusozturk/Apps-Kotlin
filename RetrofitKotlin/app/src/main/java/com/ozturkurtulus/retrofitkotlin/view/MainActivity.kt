package com.ozturkurtulus.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ozturkurtulus.retrofitkotlin.adapter.RecyclerViewAdapter
import com.ozturkurtulus.retrofitkotlin.databinding.ActivityMainBinding
import com.ozturkurtulus.retrofitkotlin.model.CryptoModel
import com.ozturkurtulus.retrofitkotlin.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener{

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModel: ArrayList<CryptoModel>? = null
    private lateinit var binding: ActivityMainBinding
    private var recyclerViewAdapter:RecyclerViewAdapter? = null

    //Disposable -- activity biterken rowlardan kurtulmak için bellek optimizasyonu
    private var compositeDisposable: CompositeDisposable? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable = CompositeDisposable()


        //RecyclerView

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        loadData()


    }


    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        //gelen veriyi işliyor arka planda dinliyor ve handleresponse kısmına aktarıyor
        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))


        /*
        RXjava sız yaptığımız örnek sağlıksız


        val service = retrofit.create(CryptoAPI::class.java)

        val call = service.getData()

        call.enqueue(object: Callback<List<CryptoModel>>{

            //İnternetten gelen veride sıkıntı yoksa ve cevap geldiyse
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {

                        cryptoModel = ArrayList(it)

                        cryptoModel?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(cryptoModel!!,this@MainActivity)
                            binding.recyclerView.adapter = recyclerViewAdapter
                        }

                        /*
                        for (cryptoModels: CryptoModel in cryptoModel!!){
                            println(cryptoModels.currency)
                            println(cryptoModels.price)
                        }

                         */
                    }
                }
            }

            //İnternetten gelen veride hata varsa
            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                // internetten çekilen verilerde hata varsa loglara yazdırır
                t.printStackTrace()
            }


        })

         */

    }

    private fun handleResponse(cryptoList : List<CryptoModel>){

        cryptoModel = ArrayList(cryptoList)

        cryptoModel?.let {
            recyclerViewAdapter = RecyclerViewAdapter(cryptoModel!!,this@MainActivity)
            binding.recyclerView.adapter = recyclerViewAdapter
        }

    }


    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"clicked: ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        //belleği temizler
        compositeDisposable?.clear()
    }
}