package com.example.inventariarpredio20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.inventariarpredio20.data.conexion.db
import com.example.inventariarpredio20.navigation.AppNavigation
import com.example.inventariarpredio20.ui.theme.InventariarPredio20Theme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db.init()
        setContent {
            InventariarPredio20Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InventariarPredio20Theme {
        AppNavigation()
    }
}