package com.devmasterteam.tasks.service.listener

interface APIListener<T> { //Interface escutadora precisará receber uma classe para implementação

    fun onSuccess(result : T)
    fun onFailure(message : String)
}