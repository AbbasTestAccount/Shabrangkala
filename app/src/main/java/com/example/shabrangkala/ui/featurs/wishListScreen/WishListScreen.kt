@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalLayoutApi::class
)

package com.example.shabrangkala.ui.featurs.wishListScreen

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shabrangkala.R
import com.example.shabrangkala.ui.featurs.mainScreen.MainScreenViewModel
import com.example.shabrangkala.ui.theme.NiceGreen
import com.example.shabrangkala.ui.theme.OnNiceGreen
import com.example.shabrangkala.utils.PRODUCT_SCREEN
import dev.burnoo.cokoin.navigation.getNavController


@Composable
fun WishListScreen(mainScreenViewModel: MainScreenViewModel) {

    val navController = getNavController()
    val wishList = mainScreenViewModel.wishListProducts.value


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth/2 - 30.dp



    Box(contentAlignment = Alignment.TopStart) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    OnNiceGreen
                )
                .verticalScroll(rememberScrollState())
                .padding(bottom = 50.dp)
        ) {

            if (wishList.isEmpty()) {
                Text(text = "sdkalhghjksd")

            } else {

                FlowRow(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(top = 50.dp)
                ) {
                    for (i in 0 until wishList.size) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                        ) {
                            Box(Modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
                                Card(
                                    onClick = {
                                        navController.navigate("$PRODUCT_SCREEN/${mainScreenViewModel.wishListProducts.value[i].id}")
                                    },
                                    modifier = Modifier.size(width = cardWidth, height = cardWidth+40.dp)
                                ) {

                                    AsyncImage(
                                        model = mainScreenViewModel.wishListProducts.value[i].images[0].src,
//                        model = PATH ,
                                        contentDescription = null,
                                        modifier = Modifier.size(width = cardWidth, height = cardWidth),
                                        contentScale = ContentScale.Crop,
                                        placeholder = painterResource(id = R.drawable.search),
                                        error = painterResource(id = R.drawable.heart)
                                    )


                                    Box(
                                        contentAlignment = Alignment.BottomCenter,
                                        modifier = Modifier
                                            .background(
                                                Brush.verticalGradient(
                                                    listOf(
                                                        Color(0x885FD068),
                                                        NiceGreen
                                                    )
                                                )
                                            )
                                            .width(cardWidth)
                                            .height(40.dp)
                                            .padding(end = 5.dp)
                                    ) {

                                        Text(
                                            text = mainScreenViewModel.wishListProducts.value[i].name.replace(
                                                "مجموعه",
                                                ""
                                            ),
                                            color = OnNiceGreen,
                                            style = MaterialTheme.typography.displaySmall,
                                            modifier = Modifier.padding(bottom = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            maxLines = 1
                                        )
                                    }


                                }

                                if (mainScreenViewModel.wishListProducts.value[i].on_sale) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .align(Alignment.TopStart)
                                            .padding(end = 20.dp)
                                            .offset((-10).dp, (-10).dp)
                                    ) {
                                        Icon(
                                            imageVector = ImageVector.vectorResource(id = R.drawable.discount_badge),
                                            contentDescription = null,
                                            modifier = Modifier.size(width = 50.dp, height = 50.dp),
                                            tint = Color(0x6FFF0000),
                                        )
                                        Text(
                                            text = "%",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = Color.White

                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }




        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        NiceGreen, RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                    )
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "WishList", modifier = Modifier.padding(start = 10.dp))
            }
        }

    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}