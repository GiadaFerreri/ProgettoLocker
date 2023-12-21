package it.polito.progettolocker.graphic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CardsJustText(text1: String) {
        val backgroundColor= Color(0xFF_EFEFEF)
        OutlinedCard (
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor),
            border = BorderStroke(0.5.dp, Color.Black),
            shape= RectangleShape,
            modifier = Modifier
                .size(width = 400.dp, height = 200.dp)
                .padding(16.dp)
        )

        {
            Column( modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
            .wrapContentSize(align = Alignment.Center)){
                Text(
                    text = text1,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                )
            }

        }
    }

