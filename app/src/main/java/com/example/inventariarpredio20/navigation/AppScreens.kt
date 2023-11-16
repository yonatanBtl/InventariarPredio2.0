package com.example.inventariarpredio20.navigation

sealed class AppScreens(val route: String){
    object PantallaInicio: AppScreens("pantalla_inicio")
    object RegistrarAreaComun: AppScreens("registrar_area_comun")
    object RegistrarDepa: AppScreens("registrar_depa")
    object RegistrarUVM: AppScreens("registrar_uvm")
    object CasasPredio: AppScreens("casas_predio")
    object EditarCasa: AppScreens("editar_casa")
    object EditarPredio: AppScreens("editar_predio")
    object InventarioPredios: AppScreens("inventario_predios")
}
