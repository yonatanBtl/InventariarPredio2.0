package com.example.inventariarpredio20.models

import java.math.BigDecimal

data class PredioCasa(
    val idPredio: Int,
    val idEstado: Int,
    val numero: Short,
    val piso: Short,
    val area: BigDecimal,
    val participacion: BigDecimal,
)