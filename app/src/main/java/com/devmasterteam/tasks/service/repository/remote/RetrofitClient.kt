package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.repository.local.TaskDatabase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient private constructor() {

    companion object {

        private lateinit var INSTANCE: Retrofit //Conceito de singleton
        private const val BASE_URL = "http://devmasterteam.com/CursoAndroidAPI/"

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()

            if (!::INSTANCE.isInitialized) { //Se a instância do retrofit ainda não tiver sido instânciada, realizar a instância
                synchronized(RetrofitClient::class) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>) : T {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}