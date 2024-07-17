package com.metosoft.jetpack3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun BirinciSayfa(navController: NavController) {


    val CizimListesi = remember {
        mutableStateListOf("herhangibirdosya")

    }
    LazyColumn(      modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        items(
            count = CizimListesi.count(),
            itemContent = {
               val Cizim = CizimListesi[it]



            }
        )
    }


     Card(modifier = Modifier
             .fillMaxWidth()
             .height(150.dp)
             .padding(16.dp)) {
         Row(modifier = Modifier.fillMaxWidth()) {
             Button(
                 onClick = {
                     (" ")

                 }, modifier = Modifier.padding(35.dp), colors = ButtonDefaults.buttonColors(
                     contentColor = Color.White,
                     containerColor = Color.Black
                 )
             ) {
                 Text(text = "Seç")
             }

         }
         Text(text = "Çizim: ${CizimListesi}", fontSize = 24.sp)



    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = { navController.navigate("ikinci_sayfa") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .size(75.dp)
        ) {
            Icon(
                Icons.Filled.ArrowCircleRight,
                contentDescription = "Sağ alt köşe ikonu"
            )

        }



    }
    Image(painter = painterResource(id = R.drawable.micon), contentDescription = " ",
        modifier = Modifier
            .fillMaxWidth()
            .padding(75.dp),
        alignment = Alignment.BottomCenter)

    Box (modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {navController.navigate("ilk_sayfa")


            },
            modifier = Modifier
                .padding(150.dp)
                .align(Alignment.BottomStart),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            )
        ) {
            Text(text = "Yenile")
        }

    }
}


