package com.devmasterteam.tasks.service.repository

import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.service.repository.remote.PersonService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository {

    private val remote = RetrofitClient.getService(PersonService::class.java)

    fun login(email : String, password : String, listener : APIListener<PersonModel>) {
        val call = remote.login(email, password)
        call.enqueue(object : Callback<PersonModel> {
            override fun onResponse(call: Call<PersonModel>, response: Response<PersonModel>) {
                if(response.code() == 200) { //Se a resposta for um código 200 significa que deu certo
                    response.body()?.let { listener.onSuccess(it) }   //Passa o corpo da requisição para o método listener.onSucess
                    val s = ""
                } else {
                    listener.onFailure(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<PersonModel>, t: Throwable) {
                listener.onFailure(R.string.ERROR_UNEXPECTED.toString())
            }

        })
    }
}