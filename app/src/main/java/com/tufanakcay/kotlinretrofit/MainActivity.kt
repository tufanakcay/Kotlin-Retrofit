package com.tufanakcay.kotlinretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tufanakcay.kotlinretrofit.configs.ApiClient
import com.tufanakcay.kotlinretrofit.models.DummyProducts
import com.tufanakcay.kotlinretrofit.models.Product
import com.tufanakcay.kotlinretrofit.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var dummyService: DummyService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dummyService = ApiClient.getClient().create(DummyService::class.java)
        dummyService.products().enqueue( object: Callback<DummyProducts> {
            override fun onResponse(call: Call<DummyProducts>, response: Response<DummyProducts>) {
                val datas = response.body();
                for( item in datas!!.products ) {
                    Log.d("title", item.title)
                }
            }
            override fun onFailure(call: Call<DummyProducts>, t: Throwable) {
                Log.e("dummyService", t.toString())
            }
        })

        dummyService.singleProduct(2).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                val product = response.body()
                if (product != null) {
                    Log.d("description", product.description)
                }
            }
            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.e("singleProduct", t.toString())
            }

        })


    }
}