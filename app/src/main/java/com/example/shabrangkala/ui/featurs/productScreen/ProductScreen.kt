@file:OptIn(ExperimentalFoundationApi::class, ExperimentalTextApi::class)

package com.example.shabrangkala.ui.featurs.productScreen

import android.annotation.SuppressLint
import android.text.Html
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.shabrangkala.R
import com.example.shabrangkala.ui.theme.LiteNiceGreen
import com.example.shabrangkala.ui.theme.LiteNiceGreenWithTrans
import com.example.shabrangkala.ui.theme.OnNiceGreen
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProductScreen(id: Int) {


    val productViewModel = getNavViewModel<ProductScreenViewModel>()
    val navController = getNavController()

    val pagerState = rememberPagerState()

    var likeState by remember { mutableStateOf(false) }

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.like_animation))
    val interactionSource = remember { MutableInteractionSource() }


    productViewModel.loadProductData(id)

    var isOverlayPresented = true

    val productData = productViewModel.productData.value

    BackHandler(enabled = isOverlayPresented) {
        // your action to be called if back handler is enabled

        navController.popBackStack()

        productViewModel.clearProductData()
        isOverlayPresented = false


    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                OnNiceGreen
            )
            .verticalScroll(rememberScrollState())
    ) {

        if (productData.isProductEmpty()) {

        } else {
            Card(

                colors = CardDefaults.cardColors(containerColor = LiteNiceGreen),
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Box {

                    HorizontalPager(
                        pageCount = productData.images.size,
                        state = pagerState
                    ) {
                        if (productData.isProductEmpty()) {

                        } else {
                            AsyncImage(
                                model = productData.images[it].src,
                                modifier = Modifier.fillMaxWidth(),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                placeholder = painterResource(id = R.drawable.search),
                                error = painterResource(id = R.drawable.heart)
                            )
                        }
                    }

                    if (likeState) {
                        LottieAnimation(
                            composition = composition,
                            speed = 1f,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = (-20).dp, y = 20.dp)
                                .background(
                                    Color(0x37DFDFDF), shape = CircleShape
                                )
                                .size(50.dp)
                                .clickable(interactionSource = interactionSource,
                                    indication = null, onClick = { likeState = !likeState })
                        )
                    } else {
                        LottieAnimation(
                            composition = composition,
                            speed = -1f,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = (-20).dp, y = 20.dp)
                                .background(
                                    Color(0x37DFDFDF), shape = CircleShape
                                )
                                .size(50.dp)
                                .clickable(interactionSource = interactionSource,
                                    indication = null, onClick = { likeState = !likeState })

                        )
                    }



                    if (pagerState.currentPage == 0) {

                    } else {
                        IconButton(
                            onClick = {},
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.back),
                                contentDescription = "back button",
                                modifier = Modifier
                                    .background(LiteNiceGreenWithTrans, CircleShape)
                                    .padding(5.dp)
                                    .alpha(0.6f)
                            )
                        }
                    }

                    if (pagerState.currentPage == productData.images.size - 1) {

                    } else {
                        IconButton(
                            onClick = {},
                            modifier = Modifier.align(Alignment.CenterEnd)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.back),
                                contentDescription = "back button",
                                modifier = Modifier
                                    .background(LiteNiceGreenWithTrans, CircleShape)
                                    .padding(5.dp)
                                    .alpha(0.6f)
                                    .rotate(180f)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = productData.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 15.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))


            ExpandingText(productData.description)
        }
    }
}

@Composable
fun ExpandingText(
    description: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    expandable: Boolean = true,
    collapsedMaxLines: Int = 3,
    expandedMaxLines: Int = Int.MAX_VALUE,
    padding: Int = 20,
) {
    val text = Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY)
    Log.e("abbas", text.toString())

    var expanded = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Text(
        text = text.toString(),
        style = textStyle.copy(textDirection = TextDirection.Rtl),
        textAlign = TextAlign.Right,

        overflow = TextOverflow.Ellipsis,
        maxLines = if (expanded.value) expandedMaxLines else collapsedMaxLines,
        modifier = Modifier
            .clickable(
                enabled = expandable,
                onClick = { expanded.value = !expanded.value },
                indication = rememberRipple(bounded = true),
                interactionSource = interactionSource,
            )
            .padding(horizontal = padding.dp)
            .animateContentSize(animationSpec = spring())
            .then(modifier)


    )
}