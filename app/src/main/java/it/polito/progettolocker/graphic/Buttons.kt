package it.polito.progettolocker.graphic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Buttons(text: String,  onClickHandler: () -> Unit) {
    Button(
        onClick = { onClickHandler()  },
        shape= RectangleShape,
        modifier = Modifier
            .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
            .border(width = 0.5.dp, color = Color.Black)
            .width(140.dp)
            .height(50.dp)
            .background(color = Color(0xFFF8F6F6)),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color.Transparent)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
    }
}