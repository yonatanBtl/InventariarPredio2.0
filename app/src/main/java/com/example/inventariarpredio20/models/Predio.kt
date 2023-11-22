package com.example.inventariarpredio20.models

data class Predio(
    val idPredio: Int,
    val idTipoPredio: Int,
    val descripcion: String,
    val ruc: String,
    val telefono: String,
    val correo: String,
    val direccion: String,
    val idUbigeo: String? = null
)