package com.example.inventariarpredio20.models

import com.example.inventariarpredio20.data.tables.PredioAreaComunTable
import com.example.inventariarpredio20.data.tables.PredioAreaComunTable.autoIncrement

data class PredioAreaComun(
    val idPredio: Int,
    val idAreaComun: Int,
    val codigo: String?,
    val area: Double
)


