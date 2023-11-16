package com.example.inventariarpredio20.models

import com.example.inventariarpredio20.data.tables.PredioAreaComunTable
import com.example.inventariarpredio20.data.tables.PredioAreaComunTable.autoIncrement

data class PredioMDU(
    //val idPredioMDU: Int,
    val idPredio: Int,
    val idMDU: Int,
    val descripcion: String,
    val direccion: String,
    val numero: String,
)