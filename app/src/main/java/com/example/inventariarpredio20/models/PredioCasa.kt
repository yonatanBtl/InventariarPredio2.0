package com.example.inventariarpredio20.models

import java.math.BigDecimal

data class PredioCasa(
    val idCasa: Int,
    val idPredio: Int,
    val idEstado: Int,
    val idPredioMdu: Int,
    val numero: Short,
    val piso: Short? = null,
    val area: BigDecimal,
    val participacion: BigDecimal? = null,
)