package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.activity.viewModels
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.ViewModelLocker
import it.polito.progettolocker.graphic.FooterHome
import it.polito.progettolocker.graphic.HeaderDouble

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Spedizioni(mainActivity: MainActivity, navController: NavController) {
    
    val buttonLeft = remember { mutableStateOf(true) }

    Scaffold(
        //containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            HeaderDouble(
                text1 = "IN CORSO",
                weight1 = if(buttonLeft.value) FontWeight.Bold else FontWeight.Normal,
                text2 = "STORICO CONSEGNE",
                weight2 = if(buttonLeft.value) FontWeight.Normal else FontWeight.Bold,
                onClickHandler1 = { if(!buttonLeft.value) { buttonLeft.value = true } },
                onClickHandler2 = { if(buttonLeft.value) buttonLeft.value = false },
                navController = navController)
        },
        bottomBar = {
            FooterHome(navController = navController)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            if(buttonLeft.value)
                SpedizioniInCorso(mainActivity = mainActivity, navController = navController)
            else
                StoricoConsegne(mainActivity = mainActivity, navController = navController)
        }

    }
    //var buttonLeft = true
    /*Column{
        Row{
            HeaderDouble(
                text1 = "IN CORSO",
                weight1 = if(buttonLeft) FontWeight.Bold else FontWeight.Normal,
                text2 = "STORICO CONSEGNE",
                weight2 = if(buttonLeft) FontWeight.Normal else FontWeight.Bold,
                onClickHandler1 = { if(!buttonLeft) { buttonLeft = true; } },
                onClickHandler2 = { if(buttonLeft) buttonLeft = false },
                navController = navController)
        }
        Row{
            Text(text = buttonLeft.toString())
        }
        Row(modifier = Modifier.height(IntrinsicSize.Min)){
            *//* TODO: Scrollable *//*
            if(buttonLeft)
                SpedizioniInCorso(mainActivity = mainActivity, navController = navController)
            else
                StoricoConsegne(mainActivity = mainActivity, navController = navController)
        }
        Row(
            //verticalAlignment = Alignment.Bottom
        ){
            FooterHome(navController = navController)
        }
    }*/








    BackHandler (enabled = true){
        navController.navigate("Customer")
    }

}