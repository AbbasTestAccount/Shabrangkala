@file:OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)

package com.example.shabrangkala.ui.featurs.cartScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shabrangkala.R
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen() {
    val cartViewModel = getNavViewModel<CartScreenViewModel>()
    val navController = getNavController()
    var snackBarVisible by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current




    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                items(4) {
                    val dismissState =
                        rememberDismissState(
                            initialValue = DismissValue.Default,
                            confirmValueChange = {
                                when (it) {
                                    DismissValue.DismissedToEnd -> {
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
                                                    Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show()
                                                }
                                            }
                                        }
                                    }

                                    DismissValue.DismissedToStart -> {
                                        snackBarVisible = !snackBarVisible
                                        Toast.makeText(context, "aaaaaaaa", Toast.LENGTH_SHORT)
                                            .show()
                                    }

                                    DismissValue.Default -> {
                                        Toast.makeText(context, "aaaaaaaa", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                                true
                            })
                    SwipeToDismiss(state = dismissState, background = {}, dismissContent = {
                        Card(modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
                            }) {
                            Row() {
                                Image(
                                    painter = painterResource(id = R.drawable.pic),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(80.dp)
                                )
                                Text(text = "Title")

                            }
                        }
                    }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }
    }

}