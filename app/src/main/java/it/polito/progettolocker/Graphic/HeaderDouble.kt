package it.polito.progettolocker.Graphic

import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
import android.os.Build
import android.view.View.OnClickListener
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.polito.progettolocker.R

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HeaderDouble( text1: String, weight1: FontWeight,text2: String, weight2: FontWeight, onClick: OnClickListener){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                },

                actions = {
                    Button(colors = ButtonDefaults.buttonColors(Color.Transparent), onClick = {onClick }) {
                        Text(
                            text= text1,
                            color = Color.Black,
                            fontWeight = weight1
                        )

                    }
                    Button(colors = ButtonDefaults.buttonColors(Color.Transparent), onClick = {onClick}) {
                        Text(
                            text= text2,
                            color = Color.Black,
                            fontWeight = weight2
                        )

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