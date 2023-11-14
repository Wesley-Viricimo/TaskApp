package com.devmasterteam.tasks.service.model

class ValidationModel(message: String = "") { //Construtor espera uma string que é vazia caso não seja passada como parâmetro

    private var status: Boolean = true
    private var validationMessage: String = ""

    init {
        if(message != "") {
            validationMessage = message
            status = false
        }
    }

    fun status() = status
    fun message() = validationMessage
}