package com.example.karma.data

data class Usuario(
   val email: String,
   val karma: Int,
   val pidioFavor: Boolean,
   val movimientos: ArrayList<String>,
   val cantFavores: Number = 0
)