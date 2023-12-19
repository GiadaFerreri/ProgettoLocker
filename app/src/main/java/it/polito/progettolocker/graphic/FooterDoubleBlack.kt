package it.polito.progettolocker.graphic
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable

fun FooterDoubleBlack(price: Int){
    Column(verticalArrangement = Arrangement.Bottom) {
        Row{
            Divider(color = Color.Black, thickness = 1.dp)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {

    Button( shape= RectangleShape, modifier = Modifier
        .padding(0.dp)
        .height(60.dp),colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
        containerColor = Color.Black), onClick = { }, contentPadding = PaddingValues(0.dp)) {
        Text(text = "CONTINUA", modifier=Modifier.
        padding(start=5.dp),
            style = TextStyle(
                fontSize = 15.sp),
            color = Color.White
        )
}




                       Text(
                               text ="TOTALE    $price EUR", modifier=Modifier.
                               padding(end=5.dp),style = TextStyle(
                                   fontSize = 15.sp,
                               ),  color = Color.Black
                           )


        }
    }
}