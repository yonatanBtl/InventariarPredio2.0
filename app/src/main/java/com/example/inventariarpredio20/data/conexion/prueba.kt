package com.example.inventariarpredio20.data.conexion

import com.example.inventariarpredio20.data.repository.PredioRepository
import kotlinx.coroutines.runBlocking

//prueba para traer datos
fun main(){
    db.init()
    val nombresDePredios = runBlocking {
        PredioRepository.obtenerNombresDePredios()
    }
    nombresDePredios.forEach {
        //Log.d("Predio", it)
        println("Predio: $it")
    }
}