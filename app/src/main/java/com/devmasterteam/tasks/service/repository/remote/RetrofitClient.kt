package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.repository.local.TaskDatabase
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient private constructor() {

    companion object { //Companion object salva as informações estáticas em memória

        private lateinit var INSTANCE: Retrofit //Conceito de singleton
        private const val BASE_URL = "http://devmasterteam.com/CursoAndroidAPI/"
        private var token: String = ""
        private var personKey: String = ""

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                        .newBuilder()
                        .addHeader(TaskConstants.HEADER.TOKEN_KEY, token)
                        .addHeader(TaskConstants.HEADER.PERSON_KEY, personKey)
                        .build()
                    return chain.proceed(request)
                }

            })

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

        fun <T> getService(serviceClass: Class<T>) : T {//Como vários serviços precisarão utilizar a retrofit client será utilizado generics para receber qualquer classe de serviço
            return getRetrofitInstance().create(serviceClass)
        }

        fun addHeaders(tokenValue: String, personKeyValue: String) {
            token = tokenValue
            personKey = personKeyValue
        }

    }
}