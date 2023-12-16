package it.polito.progettolocker.Graphic

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderDouble(
    text1: String,
    weight1: FontWeight,
    text2: String,
    weight2: FontWeight,
    onClickListener1: String = "",
    onClickListener2: String = "",
    navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceEvenly){
                        Button(colors = ButtonDefaults.buttonColors(Color.Transparent), onClick = { if(onClickListener1 != "") navController.navigate(onClickListener1) }) {
                                Text(
                                    text= text1,
                                    color = Color.Black,
                                    fontWeight = weight1
                                )
                        }
                        Button(colors = ButtonDefaults.buttonColors(Color.Transparent), onClick = { if(onClickListener2 != "") navController.navigate(onClickListener2) }) {
                                Text(
                                    text= text2,
                                    color = Color.Black,
                                    fontWeight = weight2
                                )
                            }
                        }
                },

            )
        },
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }
}