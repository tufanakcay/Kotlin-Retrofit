package com.tufanakcay.kotlinretrofit.services

import com.tufanakcay.kotlinretrofit.models.DummyProducts
import com.tufanakcay.kotlinretrofit.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DummyService {

    @GET("products")
    fun products() : Call<DummyProducts>

    @GET("products/{id}")
    fun singleProduct( @Path("id") id: Int ) : Call<Product>

}