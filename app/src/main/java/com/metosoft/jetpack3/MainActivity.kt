package com.metosoft.jetpack3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.metosoft.jetpack3.ui.theme.Jetpack3Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Jetpack3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SayfaGecisleri()
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "ilk_sayfa") {

        composable(route = "ilk_sayfa") {
            BirinciSayfa(navController)
        }
        composable(route = "ikinci_sayfa") {
            IkinciSayfa(navController)
        }
        composable(route = "ucuncu_sayfa") {
            UcuncuSayfa(navController)
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun Greeting() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(){
    Jetpack3Theme{
        SayfaGecisleri()
        Greeting()
    }
}







