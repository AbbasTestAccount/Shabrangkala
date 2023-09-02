@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalTextApi::class,
    ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class
)

package com.example.shabrangkala.ui.featurs.categoryListScreen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shabrangkala.R
import com.example.shabrangkala.ui.theme.NiceGreen
import com.example.shabrangkala.ui.theme.OnNiceGreen
import com.example.shabrangkala.utils.PRODUCT_SCREEN
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun CategoryScreen(id: Int) {


    val categoryViewModel = getNavViewModel<CategoryScreenViewModel>()
    val navController = getNavController()
    val context = LocalContext.current



    categoryViewModel.loadProductDataOfCategory(id)
    categoryViewModel.getCategoryNameById(id)
    categoryViewModel.getProductCount(id)


    val productsList = categoryViewModel.productsData
    val categoryName = categoryViewModel.categoryName
    val categoryCount = categoryViewModel.categoryCount
    val pageNumber = categoryViewModel.pageNumber

    var isOverlayPresented = true
    BackHandler(enabled = isOverlayPresented) {
        // your action to be called if back handler is enabled

        navController.popBackStack()

        categoryViewModel.clearProductsData()
        isOverlayPresented = false
    }

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

            if (productsList.value.isEmpty()) {
                Text(text = "sdkalhghjksd")

            } else {


                FlowRow(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(top = 50.dp)
                ) {
                    for (i in 0 until productsList.value.size) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                        ) {
                            Box(Modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
                                Card(
                                    onClick = {
                                        navController.navigate("$PRODUCT_SCREEN/${productsList.value[i].id}")
                                    },
                                    modifier = Modifier.size(width = 180.dp, height = 220.dp)
                                ) {

                                    AsyncImage(
                                        model = productsList.value[i].images[0].src,
//                        model = PATH ,
                                        contentDescription = null,
                                        modifier = Modifier.size(width = 180.dp, height = 180.dp),
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
                                            .width(180.dp)
                                            .height(40.dp)
                                            .padding(end = 5.dp)
                                    ) {

                                        Text(
                                            text = productsList.value[i].name.replace("مجموعه", ""),
                                            color = OnNiceGreen,
                                            style = MaterialTheme.typography.displaySmall,
                                            modifier = Modifier.padding(bottom = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            maxLines = 1
                                        )
                                    }


                                }

                                if (productsList.value[i].on_sale) {
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

                PageSelect(categoryCount.value, pageNumber) {
                    categoryViewModel.loadProductDataOfCategory(id)
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
                IconButton(onClick = {
                    navController.popBackStack()
                    categoryViewModel.clearProductsData()

                }) {
                    Icon(painter = painterResource(id = R.drawable.back), contentDescription = null)
                }

                Text(
                    text = categoryName.value,
                    Modifier.padding(end = 20.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}

@Composable
fun PageSelect(categoryProductCount: Int, intValue: MutableIntState, onPageSelect: () -> Unit) {

    LazyRow() {
        items(categoryProductCount / 20 + lastPage(categoryProductCount)) {
            IconButton(
                onClick = {
                    onPageSelect()
                    intValue.intValue = it+1
                },
                modifier = Modifier.background(
                    color = pageSelectColor(it+1, intValue.intValue),
                    shape = CircleShape
                )
            ) {
                Text(text = (it+1).toString(), textAlign = TextAlign.Center)
            }

        }

    }


}

fun lastPage(categoryProductCount: Int): Int {
    return if (categoryProductCount%20 == 0){
        0
    }else{
        1
    }

}

fun pageSelectColor(i: Int, intValue: Int): Color {
    return if (i == intValue) {
        NiceGreen
    } else {
        Color.Transparent
    }
}
