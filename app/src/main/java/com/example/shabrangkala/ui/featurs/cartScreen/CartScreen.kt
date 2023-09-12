@file:OptIn(
    ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.example.shabrangkala.ui.featurs.cartScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shabrangkala.R
import com.example.shabrangkala.ui.theme.NiceGreen
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun CartScreen() {
    val cartViewModel = getNavViewModel<CartScreenViewModel>()
    val navController = getNavController()
    var snackBarVisible by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    Scaffold(topBar = {CartTopBar()},snackbarHost = { SnackbarHost(snackbarHostState) }) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = rememberLazyListState(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.align(Alignment.TopCenter),
                contentPadding = PaddingValues(top = 60.dp)
            ) {

                itemsIndexed(
                    items = cartViewModel.list,
                    key = { index, item -> item.hashCode() })
                { index, item ->
                    //todo: sort list


                    val dismissState =
                        rememberDismissState(
                            confirmValueChange = {

                                if (it == DismissValue.DismissedToEnd) {
                                    cartViewModel.list.remove(item)


                                    scope.launch {
                                        val snackbarResult = snackbarHostState.showSnackbar(
                                            "Product is removed from your cartList!",
                                            duration = SnackbarDuration.Short,
                                            actionLabel = "Undo"
                                        )

                                        when (snackbarResult) {
                                            SnackbarResult.Dismissed -> {
                                                //todo: remove item from db completely
                                            }

                                            SnackbarResult.ActionPerformed -> {
                                                //todo: update screen to display all products in cart
                                                Toast.makeText(
                                                    context,
                                                    "hi",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }
                                }

                                true
                            })
                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                            val color =
                                if (DismissDirection.StartToEnd == dismissState.dismissDirection) Color.Red else Color.Transparent
                            Box(
                                modifier = Modifier
                                    .background(color)
                                    .height(80.dp)
                                    .fillMaxWidth()
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.delete),
                                    contentDescription = "delete",
                                    modifier = Modifier
                                        .align(Alignment.CenterStart)
                                        .padding(start = 20.dp)
                                )
                            }
                        },
                        directions = setOf(DismissDirection.StartToEnd),
                        dismissContent = {
                            SampleItem(item)


                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))


                }
            }
        }
    }

}

@Composable
fun CartTopBar() {
    Box(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth()
        .background(NiceGreen), contentAlignment = Alignment.CenterStart
    ){
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(painter = painterResource(id = R.drawable.back), contentDescription = "back", Modifier.padding(start = 10.dp))
            Text(text = "Cart Page", fontWeight = FontWeight.Bold )
            Spacer(modifier = Modifier.width(10.dp))

        }

    }
}

@Composable
fun SampleItem(item: String) {
    Card(modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),
        onClick = {
//            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT)
//                .show()
        }) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.pic),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(text = "Title, $item")

        }
    }
}
