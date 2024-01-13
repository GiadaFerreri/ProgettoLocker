package it.polito.progettolocker.views.customer

import android.graphics.Paint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardsJustText
import it.polito.progettolocker.graphic.HeaderX
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//La lunghezza del codice è considerata 6
fun LockerCode(mainActivity: MainActivity, navController: NavController){
    var codice by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var alert by remember { mutableStateOf("") }
    var isTextFieldSelected by remember { mutableStateOf(false) }
    var contatore by remember { mutableIntStateOf(0) }

    fun setTextFieldValue(newValue: String) {
        codice = newValue
    } 
    
    fun setAlert(newValue: String) {
            alert=newValue

    }





    Column {
        Row(){
            HeaderX(text ="LOCKER" , navController = navController, onClickDestination = "Spedizioni")
        }
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
            .padding(top = 50.dp, bottom = 25.dp)
            .fillMaxWidth())
        {

            Text(
                text = "INSERISCI IL CODICE CHE VEDI SULLO SCHERMO DEL LOCKER",
                fontSize = 14.sp,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )
    }
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {


            TextField(
                value = codice,
                onValueChange = {
                    if (it.length <= 6) {
                        codice = it
                    }
                },
                label = { Text("CODICE LOCKER") },
                maxLines = 1,
                textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = if (isError) Color.Red else if (isTextFieldSelected) Color.Black else Color.Gray
                    )
                    .onFocusChanged {
                        isTextFieldSelected = it.isFocused
                    }
            )
        }
        Row ( horizontalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp)){
            Text(text = alert,
                color= Color.Red)
        }
        Row( horizontalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp))
        {
            Button(
                onClick = {
                    if(codice=="123456"){
                        navController.navigate("LockerCorrectedCode")
                        isError=false
                    }

                    else{
                        isError=true
                        ++contatore
                        if(contatore==1){
                            setAlert("PRIMO CODICE INSERITO ERRATO. RIPROVA")
                        }
                        if(contatore==2){
                            setAlert("SECONDO CODICE INSERITO ERRATO. RIPROVA")
                        }
                        if(contatore==3){
                            setAlert("TERZO CODICE INSERITO ERRATO. RIPROVA")
                        }
                        if(contatore==4){
                            setAlert("TENTATIVI TERMINATI. RIPROVA PIÙ TARDI")
                        }

                        setTextFieldValue("")

                    }
                    },
                shape= RectangleShape,
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .border(width = 0.5.dp, color = Color.Black)
                    .width(140.dp)
                    .height(50.dp)
                    .background(color = Color(0xFFF8F6F6)),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color.Transparent),
                enabled = codice.length == 6
            ) {
                Text(
                    text = "CONFERMA",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 12.sp
                )
            }
        }


    }



    BackHandler (enabled = true){
        navController.navigate("LockerConfirmCustomer")
    }
}