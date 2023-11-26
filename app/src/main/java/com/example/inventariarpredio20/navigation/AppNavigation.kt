package com.example.inventariarpredio20.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.inventariarpredio20.data.repository.PredioIdSingleton
import com.example.inventariarpredio20.screens.CasasPredio
import com.example.inventariarpredio20.screens.EditarCasa
import com.example.inventariarpredio20.screens.EditarPredio
import com.example.inventariarpredio20.screens.InventarioPredios
import com.example.inventariarpredio20.screens.PantallaInicio
import com.example.inventariarpredio20.screens.RegistrarAreaComun
import com.example.inventariarpredio20.screens.RegistrarDepa
import com.example.inventariarpredio20.screens.RegistrarUVM


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.PantallaInicio.route){
        composable(route= AppScreens.PantallaInicio.route){
            PantallaInicio(navController)
        }
        composable(route= AppScreens.RegistrarAreaComun.route){
            RegistrarAreaComun(navController)
        }
        composable(route= AppScreens.RegistrarDepa.route){
            RegistrarDepa(navController)
        }
        composable(route= AppScreens.RegistrarUVM.route){
            RegistrarUVM(navController)
        }

        composable(route= AppScreens.CasasPredio.route){
            val idPredio = PredioIdSingleton.idPredio
            CasasPredio(navController, idPredio)
        }
        composable(route= AppScreens.EditarCasa.route){
            EditarCasa(navController)
        }
        composable(route= AppScreens.EditarPredio.route){
            EditarPredio(navController)
        }
        composable(route= AppScreens.InventarioPredios.route){
            InventarioPredios(navController)
        }
    }
}

