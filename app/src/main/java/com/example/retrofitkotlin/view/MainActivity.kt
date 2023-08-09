package com.example.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitkotlin.adapter.CryptoAdapter
import com.example.retrofitkotlin.databinding.ActivityMainBinding
import com.example.retrofitkotlin.model.CryptoModel
import com.example.retrofitkotlin.service.CryptoService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoList: ArrayList<CryptoModel>? = null
    private var cryptoAdapter: CryptoAdapter? = null
    private var job: Job? = null//coroutines için
    val exception= CoroutineExceptionHandler { coroutineContext, throwable ->
        println("hata$throwable")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.recyler.layoutManager = LinearLayoutManager(this)


        loadData()


    }

    private fun loadData() {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CryptoService::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {
            val response =
                retrofit.getData()
            withContext(Dispatchers.Main+exception) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoList = ArrayList(it)
                        cryptoAdapter =
                            CryptoAdapter(cryptoList!!, object : CryptoAdapter.Listener {
                                override fun onItemClik(cyrptoModel: CryptoModel) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Tıklandı${cyrptoModel.currency}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            })
                        binding.recyler.adapter = cryptoAdapter
                    }
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        //en son coroutines cıkıs yaparken iptal edelim
        job?.cancel();
    }


}
//https://jsonplaceholder.typicode.com/posts
//https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json