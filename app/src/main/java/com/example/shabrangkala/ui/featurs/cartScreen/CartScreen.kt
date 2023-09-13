@file:OptIn(
    ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.shabrangkala.ui.featurs.cartScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDismissState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shabrangkala.R
import com.example.shabrangkala.ui.theme.HeavyGreen
import com.example.shabrangkala.ui.theme.LiteNiceGreen
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

    var openBottomSheet = rememberSaveable { mutableStateOf(false) }
    var textFieldState = rememberSaveable { mutableStateOf("") }




    Scaffold(topBar = { CartTopBar() }, snackbarHost = { SnackbarHost(snackbarHostState) }) {
        Box(modifier = Modifier.fillMaxSize()) {


            LazyColumn(
                state = rememberLazyListState(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.align(Alignment.TopCenter),
                contentPadding = PaddingValues(top = 120.dp)
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
                            val color by
                            animateColorAsState(targetValue = if (DismissDirection.StartToEnd == dismissState.dismissDirection) Color.Red else Color.Transparent)

                            Box(
                                modifier = Modifier
                                    .background(color)
                                    .height(100.dp)
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

            DiscountBottomSheet(openBottomSheet, textFieldState)

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(LiteNiceGreen)
            ) {
                BottomContent(openBottomSheet)
            }
        }

    }

}

@Composable
fun BottomContent(openBottomSheet: MutableState<Boolean>) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            border = BorderStroke(1.dp, HeavyGreen),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
                .height(50.dp),
            colors = CardDefaults.cardColors(containerColor = LiteNiceGreen),
            onClick = { openBottomSheet.value = !openBottomSheet.value }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "کد تخفیف دارید؟",
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    painter = painterResource(id = R.drawable.discount),
                    contentDescription = "discount",
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun CartTopBar() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(NiceGreen), contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "back",
                    Modifier.padding(start = 10.dp)
                )
                Text(text = "Cart Page", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(10.dp))

            }

        }

        Card(
            shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = LiteNiceGreen),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ریال 1000",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 20.dp)
                    )

                    Text(
                        text = "مجموع با احتساب تخفیف",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ریال 10 ",
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 20.dp)
                    )

                    Text(
                        text = "تخفیف",
                        color = Color.Gray,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))

            }

        }
    }
}

@Composable
fun SampleItem(item: String) {
    Card(modifier = Modifier
        .padding(horizontal = 10.dp)
        .fillMaxWidth(),
        onClick = {
//            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT)
//                .show()
        }) {
        Row(modifier = Modifier.align(Alignment.End)) {
            Text(text = "Title, $item", Modifier.padding(end = 10.dp, top = 10.dp))

            Image(
                painter = painterResource(id = R.drawable.pic),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )

        }
    }
}


@Composable
fun DiscountTextField(textFieldState: MutableState<String>) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {

        OutlinedTextField(
            value = textFieldState.value,
            onValueChange = { textFieldState.value = it },
            modifier = Modifier.width(200.dp),
            label = {
                Text(
                    text = "کد تخفیف خود را وارد کنید",
                    textAlign = TextAlign.Right,


                    )
            },
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(autoCorrect = false)
        )

        Spacer(modifier = Modifier.width(5.dp))
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Spacer(modifier = Modifier.height(5.dp))
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .background(shape = RoundedCornerShape(5.dp), color = HeavyGreen)
                    .width(100.dp)
                    .height(35.dp)
            ) {
                Text(
                    text = "  بررسی کد",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

            }

        }
    }
}

//@Preview
//@Composable
//fun show() {
//    Surface(modifier = Modifier.background(Color.White)) {
//        DiscountTextField(textFieldState)
//
//    }
//}


@Composable
fun DiscountBottomSheet(
    openBottomSheet: MutableState<Boolean>,
    textFieldState: MutableState<String>
) {
//todo: should be fix

// Sheet content
    if (openBottomSheet.value) {

        ModalBottomSheet(
            onDismissRequest = { openBottomSheet.value = !openBottomSheet.value },
            containerColor = LiteNiceGreen,
        ) {

            Column() {
                DiscountTextField(textFieldState)

                Spacer(modifier = Modifier.height(30.dp))
            }

        }
    }
}