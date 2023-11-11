package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.model.PersonModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PersonService {

    @POST("Authentication/Login")
    @FormUrlEncoded //Informa que as informações que serão enviadas através do método POST estão no body
    fun login(@Field("email") email: String, @Field("password") password: String) : Call<PersonModel> //@Field diz o nome do campo da API que receberá a informação enviada

    @POST("Authentication/Create")
    @FormUrlEncoded //Informa que as informações que serão enviadas através do método POST estão no body
    fun create(@Field("name") name : String, @Field("email") email: String, @Field("password") password: String) : Call<PersonModel> //@Field diz o nome do campo da API que receberá a informação enviada
}