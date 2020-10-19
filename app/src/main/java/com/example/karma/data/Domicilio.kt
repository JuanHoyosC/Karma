package com.example.karma.data

data class Domicilio (
    val email: String = "",
    val uid: String = "",
    val lugar: String = "",
    val ref: String = "",
    val titulo: String = "Domicilio",
    var estado: String = "Inicial",
    var realizado: Boolean =  false
)