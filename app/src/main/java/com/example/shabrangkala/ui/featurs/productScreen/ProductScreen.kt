@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalTextApi::class,
    ExperimentalLayoutApi::class
)

package com.example.shabrangkala.ui.featurs.productScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.ui.featurs.mainScreen.MainScreenViewModel
import com.example.shabrangkala.ui.theme.HeavyGreen
import com.example.shabrangkala.ui.theme.LiteNiceGreen
import com.example.shabrangkala.ui.theme.LiteNiceGreenWithTrans
import com.example.shabrangkala.ui.theme.NiceGreen
import com.example.shabrangkala.ui.theme.OnNiceGreen
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProductScreen(id: Int) {

    //prevent screen to rotate in Landscape view
    val context = LocalContext.current
    DisposableEffect(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }


    val productViewModel = getNavViewModel<ProductScreenViewModel>()
    val mainViewModel = getNavViewModel<MainScreenViewModel>()
    val navController = getNavController()

    val pagerState = rememberPagerState()

    var likeState by remember { mutableStateOf(findLikeState(mainViewModel, id)) }

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.like_animation))
    val interactionSource = remember { MutableInteractionSource() }

    var productCount = remember { mutableIntStateOf(0) }

    productViewModel.loadProductVariations(id)

    productViewModel.loadProductData(id)



    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val cardImageHeight = if (screenHeight >= 480.dp) {
        screenHeight / 3
    } else {
        screenHeight / 2
    }

    var isOverlayPresented = true

    val productData = productViewModel.productData.value
    val productVariations = productViewModel.productVariations.value

    var selectedVariations = rememberSaveable { mutableMapOf<String, String>() }

    val coroutineScope = rememberCoroutineScope()


    BackHandler(enabled = isOverlayPresented) {
        // your action to be called if back handler is enabled

        navController.popBackStack()

        productViewModel.clearProductData()
        isOverlayPresented = false
    }

    Box() {
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
                                        indication = null, onClick = {
                                            likeState = !likeState
                                            productViewModel.removeProductFromWishList(productData)
                                        })
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
                                        indication = null, onClick = {
                                            likeState = !likeState
                                            productViewModel.addProductToWishList(productData)
                                        })

                            )
                        }



                        if (!pagerState.canScrollBackward) {

                        } else {
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                    }
                                },
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

                        if (!pagerState.canScrollForward) {

                        } else {
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                    }
                                },
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

                Spacer(modifier = Modifier.height(10.dp))


                if (productData.attributes.isNotEmpty()) {
                    for (index in 0 until productData.attributes.size) {
                        Spacer(modifier = Modifier.height(10.dp))
                        TitlePiece(
                            productData.attributes[index].name,
                            TextDirection.Rtl,
                            Arrangement.End
                        )

                        selectedVariations.put(
                            productData.attributes[index].name,
                            productData.attributes[index].options[0]
                        )

                        var selectedItem = remember {
                            mutableStateOf(productData.attributes[index].options[0])
                        }
                        Box(modifier = Modifier.align(Alignment.End)) {
                            AttributeTagsChips(productData, index, selectedVariations, selectedItem, productViewModel)
                        }
                    }
//                    productViewModel.findPriceWithVariation(selectedVariations)
                }

                TitlePiece("Product's tags")

                ProductTagsChips(productData)
            }
        }


        Row(
            modifier = Modifier
                .background(
                    NiceGreen,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
                .height(60.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
//            Row(
//                modifier = Modifier
//                    .background(
//                        color = HeavyGreen,
//                        shape = RoundedCornerShape(25.dp)
//                    )
//                    .height(40.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Spacer(modifier = Modifier.width(5.dp))
//
//                IconButton(
//                    onClick = {
//                        if (productCount.intValue > 0) {
//                            productCount.intValue--
//                        }
//                    },
//                    Modifier
//                        .background(shape = CircleShape, color = Color(0x65F3F3F3))
//                        .border(
//                            1.dp, color = Color(
//                                0xFFBBBBBB
//                            ), shape = CircleShape
//                        )
//                        .size(30.dp)
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.remove),
//                        contentDescription = null
//                    )
//
//                }
//                Spacer(modifier = Modifier.width(10.dp))
//
//                Text(text = productCount.intValue.toString())
//
//                Spacer(modifier = Modifier.width(10.dp))
//
//
//                IconButton(
//                    onClick = { productCount.intValue++ },
//                    Modifier
//                        .background(shape = CircleShape, color = Color(0x65F3F3F3))
//                        .border(
//                            1.dp, color = Color(
//                                0xFFBBBBBB
//                            ), shape = CircleShape
//                        )
//                        .size(30.dp)
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.add),
//                        contentDescription = null
//                    )
//
//                }
//                Spacer(modifier = Modifier.width(5.dp))
//
//            }


            Text(text = productViewModel.productPrice.intValue.toString())

            Button(
                onClick = {
                    productViewModel.addProductToCart(productData.id, productData.price.toInt(), productData.images[0].src, 2)
                          productViewModel.getDataFromCartDB()
                          if (productViewModel.cartList.value.isNotEmpty()){
                              Log.e("bbbaa", productViewModel.cartList.value.toString() )
                          }},
                colors = ButtonDefaults.buttonColors(containerColor = HeavyGreen)
            ) {
                Text(text = "               Add to card               ")

            }


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

    val expanded = remember { mutableStateOf(false) }
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

@Composable
fun ProductTagsChips(productData: Product) {
    FlowRow(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.padding(vertical = 20.dp)
    ) {
        if (productData.isProductEmpty()) {
            //todo
        } else {

            for (it in 0 until productData.tags.size) {

                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = productData.tags[it].name.replace(
                                "_",
                                " "
                            )
                        )
                    },
                    border = AssistChipDefaults.assistChipBorder(borderColor = LiteNiceGreen),
                    modifier = Modifier
                        .wrapContentSize(),
                    colors = AssistChipDefaults.assistChipColors(containerColor = OnNiceGreen)
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}

@Composable
fun TitlePiece(
    title: String,
    textDirection: TextDirection = TextDirection.Ltr,
    arrangement: Arrangement.Horizontal = Arrangement.Start
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        horizontalArrangement = arrangement
    ) {
        Text(
            text = "$title :",
            style = MaterialTheme.typography.titleSmall.copy(textDirection = textDirection),
        )
    }
}


@Composable
fun AttributeTagsChips(
    productData: Product,
    index: Int,
    selectedVariations: MutableMap<String, String>,
    selectedItem: MutableState<String>,
    productViewModel: ProductScreenViewModel
) {
    FlowRow(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
    ) {
        if (productData.isProductEmpty()) {
            //todo
        } else {
            for (it in 0 until productData.attributes[index].options.size) {
                AssistChip(
                    onClick = {

                        selectedItem.value = productData.attributes[index].options[it]
                        selectedVariations.put(
                            productData.attributes[index].name,
                            productData.attributes[index].options[it]
                        )
                        productViewModel.findPriceWithVariation(selectedVariations)


                        Log.e("sallkjshkjbb", selectedVariations.toString())
                    },
                    label = {
                        Text(
                            text = productData.attributes[index].options[it]
                        )
                    },
                    border = AssistChipDefaults.assistChipBorder(borderColor = LiteNiceGreen),
                    modifier = Modifier
                        .wrapContentSize(),
                    colors = AssistChipDefaults.assistChipColors(containerColor = OnNiceGreen),
                    leadingIcon = {
                        if (selectedItem.value == productData.attributes[index].options[it]) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = null
                            )
                        }
                    },

                    )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}

fun findLikeState(mainViewModel: MainScreenViewModel, id: Int): Boolean {
    if (mainViewModel.wishListProductsId.value.isEmpty()) {
        return false
    }
    for (i in 0 until mainViewModel.wishListProductsId.value.size) {
        if (id == mainViewModel.wishListProductsId.value[i]) {
            return true
        }
    }
    return false
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}