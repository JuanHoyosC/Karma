package com.example.karma.data

data class Fotocopia (
    val email: String = "",
    val uid: String = "",
    val lugar: String = "",
    val codigo: String = "",
    val titulo: String = "Fotocopia",
    var estado: String = "Inicial",
    var realizado: Boolean =  false
)