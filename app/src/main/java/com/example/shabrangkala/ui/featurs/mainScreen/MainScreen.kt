@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class, ExperimentalLayoutApi::class
)

package com.example.shabrangkala.ui.featurs.mainScreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.shabrangkala.R
import com.example.shabrangkala.model.data.Search
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.ui.featurs.wishListScreen.WishListScreen
import com.example.shabrangkala.ui.featurs.wishListScreen.shimmerEffect
import com.example.shabrangkala.ui.theme.HeavyGreen
import com.example.shabrangkala.ui.theme.LiteNiceGreen
import com.example.shabrangkala.ui.theme.LiteNiceGreenWithTrans
import com.example.shabrangkala.ui.theme.NiceGreen
import com.example.shabrangkala.ui.theme.OnNiceGreen
import com.example.shabrangkala.utils.BLOG_SCREEN
import com.example.shabrangkala.utils.CART_SCREEN
import com.example.shabrangkala.utils.CATEGORY_LIST_SCREEN
import com.example.shabrangkala.utils.PRODUCT_SCREEN
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val mainScreenViewModel = getNavViewModel<MainScreenViewModel>()
    val navController = getNavController()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth / 2 - 30.dp

    val showFab = remember { mutableStateOf(true) }
    val showTopBar = remember { mutableStateOf(true) }
    val showBottomBar = remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val selectedItem = rememberSaveable() { mutableIntStateOf(0) }
    val pagerState = rememberPagerState()

    val showEmptyWishListProduct = remember { mutableStateOf(true) }


    val bottomList = listOf(
        Pair("Home", R.drawable.home),
        Pair("Wishlist", R.drawable.heart),
        Pair("Search", R.drawable.search)
    )



    Scaffold(
        topBar = { AppTopAppBar(showTopBar, scrollState, navController) },
        floatingActionButton = { FAB(scrollState, showFab) { onFabClicked(context) } },
        bottomBar = { AppBottomBar(scrollState, selectedItem, bottomList, showBottomBar) }) {

        when (selectedItem.intValue) {
            0 -> {
                showFab.value = true
                showTopBar.value = true
                showBottomBar.value = true
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(it)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(20.dp))

                    TitlePiece(title = "دسته بندی محصولات")

                    CategoryRow(mainScreenViewModel) { id ->
                        Toast.makeText(context, id.toString(), Toast.LENGTH_SHORT).show()
                        navController.navigate("$CATEGORY_LIST_SCREEN/$id")

                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    TitlePiece(title = "پست های بلاگ")

                    BlogRow(
                        mainScreenViewModel,
                        pagerState
                    ) { id ->
                        navController.navigate("$BLOG_SCREEN/$id")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    TitlePiece(title = "آخرین محصولات")


                    ProductRow(cardWidth, mainScreenViewModel) { id ->
                        navController.navigate("$PRODUCT_SCREEN/$id")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    TitlePiece(title = "تخفیف ها")


                    ProductRow(cardWidth, mainScreenViewModel, hasDiscount = true) { id ->
                        navController.navigate("$PRODUCT_SCREEN/$id")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    TitlePiece(title = "محبوب ترین تگ ها")

                    TagsChips(mainScreenViewModel)
                    Spacer(modifier = Modifier.height(30.dp))


                    FollowOnSocialMedia(context)


                    Spacer(modifier = Modifier.height(100.dp))


//                    for (i in 0..30) {
//                        Row {
//                            Spacer(modifier = Modifier.width(20.dp))
//
//                            Column {
//                                Text(text = "ali$i")
//                                Spacer(modifier = Modifier.height(20.dp))
//                            }
//                        }
//                    }
                }
            }

            1 -> {
                showTopBar.value = false
                showFab.value = false
                mainScreenViewModel.getAllWishListProductsId()

                if (mainScreenViewModel.wishListProductsId.value.isNotEmpty()) {
                    mainScreenViewModel.loadWishListProductData(mainScreenViewModel.wishListProductsId.value)
                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(it)
                    ) {
                        Text(text = "Empty list")
                    }
                }

                if (showEmptyWishListProduct.value) {
                    EmptyProductFlowRow(cardWidth)
                }

                if (mainScreenViewModel.wishListProducts.value.isNotEmpty()) {
                    showEmptyWishListProduct.value = false

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(it)
                    ) {
                        WishListScreen(mainScreenViewModel)
                    }
                }


            }

            2 -> {
                showTopBar.value = false
                showFab.value = false
                ShopSearchBar(mainScreenViewModel, showBottomBar) { productClicked, search ->
                    navController.navigate("$PRODUCT_SCREEN/${productClicked.id}")
                    mainScreenViewModel.addNewSearchItem(search)
                }
            }
        }


    }
}

@Composable
fun EmptyProductFlowRow(cardWidth: Dp) {
    Box(contentAlignment = Alignment.TopStart) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    OnNiceGreen
                )
                .padding(bottom = 50.dp)
        ) {


            FlowRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(top = 50.dp)
            ) {
                for (i in 0 until 6) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    ) {
                        Box(Modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
                            Card(
                                modifier = Modifier.size(
                                    width = cardWidth,
                                    height = cardWidth + 40.dp
                                )
                            ) {

                                Box(
                                    modifier = Modifier
                                        .size(width = cardWidth, height = cardWidth)
                                        .shimmerEffect()
                                ) {

                                }


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
                                        text = "loading...",
                                        color = OnNiceGreen,
                                        style = MaterialTheme.typography.displaySmall,
                                        modifier = Modifier.padding(bottom = 10.dp),
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ProductRow(
    cardWidth: Dp,
    mainScreenViewModel: MainScreenViewModel,
    hasDiscount: Boolean = false,
    onProductClicked: (Int) -> Unit
) {
    LazyRow(contentPadding = PaddingValues(end = 20.dp), reverseLayout = true) {
        val count = if (hasDiscount) 4 else 10
        items(count) {

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(Modifier.padding(top = 25.dp, start = 20.dp)) {
                    val list =
                        if (hasDiscount) mainScreenViewModel.listOnSaleProduct else mainScreenViewModel.listProduct
                    Card(
                        onClick = { onProductClicked(list.value[it].id) },
                        modifier = Modifier.size(width = cardWidth, height = cardWidth + 40.dp)
                    ) {
                        if (mainScreenViewModel.listProduct.value.isNotEmpty() && mainScreenViewModel.listOnSaleProduct.value.isNotEmpty()) {
                            when (hasDiscount) {
                                true -> {
                                    AsyncImage(
                                        model = mainScreenViewModel.listOnSaleProduct.value[it].images[0].src,
//                        model = PATH ,
                                        contentDescription = null,
                                        modifier = Modifier.size(
                                            width = cardWidth,
                                            height = cardWidth
                                        ),
                                        contentScale = ContentScale.Crop,
                                        placeholder = painterResource(id = R.drawable.search),
                                        error = painterResource(id = R.drawable.heart)
                                    )
                                }

                                false -> {
                                    AsyncImage(
                                        model = mainScreenViewModel.listProduct.value[it].images[0].src,
//                        model = PATH ,
                                        contentDescription = null,
                                        modifier = Modifier.size(
                                            width = cardWidth,
                                            height = cardWidth
                                        ),
                                        contentScale = ContentScale.Crop,
                                        placeholder = painterResource(id = R.drawable.search),
                                        error = painterResource(id = R.drawable.heart)
                                    )
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(width = cardWidth, height = cardWidth)
                                    .shimmerEffect()
                            ) {

                            }
                        }


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

                            if (mainScreenViewModel.listProduct.value.isNotEmpty() && mainScreenViewModel.listOnSaleProduct.value.isNotEmpty()) {
                                when (hasDiscount) {
                                    true -> {
                                        Text(
                                            text = changeText(mainScreenViewModel.listOnSaleProduct.value[it].name),
                                            color = OnNiceGreen,
                                            style = MaterialTheme.typography.displaySmall,
                                            modifier = Modifier.padding(bottom = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                        )
                                    }

                                    false -> {
                                        Text(
                                            text = changeText(mainScreenViewModel.listProduct.value[it].name),
                                            color = OnNiceGreen,
                                            style = MaterialTheme.typography.displaySmall,
                                            modifier = Modifier.padding(bottom = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                        )
                                    }
                                }

                            } else {
                                Text(
                                    text = "loading...",
                                    color = OnNiceGreen,
                                    style = MaterialTheme.typography.displaySmall,
                                    modifier = Modifier.padding(bottom = 10.dp),
                                    fontWeight = FontWeight.Bold,
                                )
                            }

                        }


                    }

                    if (mainScreenViewModel.listOnSaleProduct.value.isNotEmpty() && hasDiscount) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(end = 20.dp)
                                .offset((-20).dp, (-20).dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.discount_badge),
                                contentDescription = null,
                                modifier = Modifier.size(width = 50.dp, height = 50.dp),
                                tint = Color.Red,
                            )
                            Text(
                                text = "%OFF",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color.White,
                                modifier = Modifier.rotate(-45f)

                            )
                        }
                    }
                }
            }
        }
    }
}

fun changeText(title: String): String {
    val title2 = if (title.contains("مجموعه")) {
        title.substring(7)
    } else {
        title
    }
    return if (title2.length > 23) {
        " ..." + title2.substring(0, 15)
    } else {
        title2
    }

}

@Composable
fun FollowOnSocialMedia(context: Context) {
    TextButton(
        onClick = {
            val uri = Uri.parse("https://www.instagram.com/shabrangkala")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(context, intent, null)
        }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xFFE74C80), RoundedCornerShape(20.dp))
                .border(
                    2.dp, color = Color(
                        0xFFA21142
                    ),
                    RoundedCornerShape(20.dp)
                )
                .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.instagram),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.White
            )
            Text(
                text = "Open Shabrangkala Instagram",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(5.dp))
        }


    }
}

//TODO
@Composable
fun BlogRow(
    mainScreenViewModel: MainScreenViewModel,
    pagerState: PagerState,
    onClicked: (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    if (mainScreenViewModel.listLastBlogPosts.value.isEmpty()) {
        EmptyBlogRow()
    } else {
        Card(
            border = BorderStroke(0.5.dp, LiteNiceGreen),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = OnNiceGreen),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(220.dp),
            onClick = { onClicked.invoke(mainScreenViewModel.listLastBlogPosts.value[pagerState.currentPage].id) }
        ) {


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                colors = CardDefaults.cardColors(containerColor = LiteNiceGreen),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Box {
                    HorizontalPager(
                        pageCount = 2,
                        state = pagerState
                    ) {
                        AsyncImage(
                            model = mainScreenViewModel.listLastBlogPosts.value[it].yoast_head_json?.og_image?.get(
                                0
                            )?.url,
                            modifier = Modifier.fillMaxWidth(),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            placeholder = painterResource(id = R.drawable.search),
                            error = painterResource(id = R.drawable.heart)
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
            if (mainScreenViewModel.listLastBlogPosts.value.isEmpty()) {

            } else {
                Text(
                    text = mainScreenViewModel.listLastBlogPosts.value[pagerState.currentPage].title.rendered,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold

                )
            }

        }
    }


}

@Composable
fun EmptyBlogRow() {
    Card(
        border = BorderStroke(0.5.dp, LiteNiceGreen),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .height(220.dp)

    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .shimmerEffect()
        ) {

        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "loading...",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold

        )


    }
}

@Composable
fun CategoryRow(mainScreenViewModel: MainScreenViewModel, onClicked: (Int) -> Unit) {
    if (mainScreenViewModel.listCategory.value.isEmpty()) {
        EmptyCategoryRow()
    } else {
        LazyRow(contentPadding = PaddingValues(end = 20.dp, start = 10.dp), reverseLayout = true) {
            items(mainScreenViewModel.listCategory.value.size) {

                Column {
                    Card(onClick = { onClicked.invoke(mainScreenViewModel.listCategory.value[it].id) }) {

                        AsyncImage(
                            model = mainScreenViewModel.listCategory.value[it].image.src,
//                        model = PATH ,
                            contentDescription = null,
                            modifier = Modifier.size(width = 125.dp, height = 180.dp),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = R.drawable.search),
                            error = painterResource(id = R.drawable.heart)
                        )

                    }



                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = mainScreenViewModel.listCategory.value[it].name,
                        color = Color.Black,
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier
                            .width(125.dp)
                            .padding(bottom = 10.dp, end = 5.dp),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 14.sp
                    )


                }

                Spacer(modifier = Modifier.width(5.dp))

            }

        }
    }

}

@Composable
fun EmptyCategoryRow() {
    LazyRow(contentPadding = PaddingValues(end = 20.dp, start = 10.dp), reverseLayout = true) {
        items(3) {

            Column {
                Card {
                    Box {
                        Box(
                            modifier = Modifier
                                .size(width = 125.dp, height = 180.dp)
                                .shimmerEffect()
                        ) {

                        }
                    }

                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "loading...",
                    color = Color.Black,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .width(125.dp)
                        .padding(bottom = 10.dp, end = 5.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 14.sp
                )


            }

            Spacer(modifier = Modifier.width(5.dp))

        }

    }
}

@Composable
fun TagsChips(mainScreenViewModel: MainScreenViewModel) {
    FlowRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        if (mainScreenViewModel.listPopularTags.value.isEmpty()) {
            //todo
        } else {
            for (it in 0..19) {

                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = mainScreenViewModel.listPopularTags.value[it].name.replace(
                                "_",
                                " "
                            )
                        )
                    },
                    border = AssistChipDefaults.assistChipBorder(borderColor = LiteNiceGreen),
                    modifier = Modifier
                        .wrapContentSize(),
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Done,
//                            contentDescription = null
//                        )
//                    },
                    colors = AssistChipDefaults.assistChipColors(containerColor = OnNiceGreen)
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }


    }
}

@Composable
fun TitlePiece(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = {}) {
                Text(
                    text = "...بیشتر",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(end = 30.dp)
            )
        }


    }
}

fun onFabClicked(context: Context) {
    Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
}


@Composable
fun AppTopAppBar(
    showTopBar: MutableState<Boolean>,
    scrollState: ScrollState,
    navController: NavHostController
) {

    if (showTopBar.value) {
        var oldValue = 0
        val closeOpenBottomBar by remember {
            derivedStateOf {
                val newValue = scrollState.value
                val result = newValue <= oldValue
                oldValue = newValue
                return@derivedStateOf result
            }
        }

        AnimatedVisibility(
            visible = closeOpenBottomBar,
            exit = shrinkVertically() + fadeOut(),
            enter = expandVertically() + fadeIn()
        ) {
            TopAppBar(
                modifier = Modifier.height(50.dp),

                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {

                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.question),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(25.dp)
                                    .background(Color.Black, CircleShape),
                                tint = NiceGreen
                            )
                        }


                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier
                                .height(30.dp)
                                .align(Alignment.CenterVertically)
                        )

                        Row {
                            IconButton(onClick = { navController.navigate(CART_SCREEN) }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.shopping),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(25.dp)
                                        .background(Color.Black, CircleShape)
                                        .padding(3.dp),
                                    tint = NiceGreen
                                )
                            }

                            Spacer(modifier = Modifier.width(20.dp))

                        }


                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NiceGreen
                )
            )
        }
    }

}

@Composable
fun FAB(scrollState: ScrollState, showFab: MutableState<Boolean>, onFabClicked: () -> Unit) {

    val expandedFab by remember {
        derivedStateOf {
            return@derivedStateOf scrollState.value - 400 < 0
        }
    }

    val fabVisibility by remember {
        derivedStateOf {
            return@derivedStateOf scrollState.value + 500 < scrollState.maxValue
        }
    }

    if (showFab.value) {
        AnimatedVisibility(
            visible = fabVisibility
        ) {
            ExtendedFloatingActionButton(
                onClick = { onFabClicked.invoke() },
                expanded = expandedFab,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.shopping_cart),
                        "Localized Description",
                        modifier = Modifier.size(24.dp)
                    )
                },
                text = { Text(text = "مشاهده سبد خرید", fontWeight = FontWeight.Bold) },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        }

    }

}

@Composable
fun AppBottomBar(
    scrollState: ScrollState,
    selectedItem: MutableState<Int>,
    bottomList: List<Pair<String, Int>>,
    showBottomBar: MutableState<Boolean>
) {

    var oldValue = 0
    val closeOpenBottomBar by remember {
        derivedStateOf {
            if (scrollState.value < scrollState.maxValue - 500) {
                val newValue = scrollState.value
                val result = newValue <= oldValue
                oldValue = newValue
                return@derivedStateOf result
            } else {
                return@derivedStateOf false
            }
        }
    }

    if (showBottomBar.value) {
        AnimatedVisibility(
            visible = closeOpenBottomBar,
            exit = shrinkVertically() + fadeOut(),
            enter = expandVertically() + fadeIn()
        ) {
            NavigationBar(
                containerColor = NiceGreen, modifier = Modifier.height(75.dp),
            ) {
                bottomList.forEachIndexed { index, item ->
                    NavigationBarItem(

                        icon = {
                            Icon(
                                painterResource(item.second),
                                contentDescription = item.first,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = { Text(item.first) },
                        selected = selectedItem.value == index,
                        onClick = { selectedItem.value = index },
                        colors = NavigationBarItemDefaults.colors(

                            selectedIconColor = bottomNavColor(index),
                            selectedTextColor = bottomNavColor(index),
                            indicatorColor = OnNiceGreen,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    }

}

fun bottomNavColor(index: Int): Color {
    return when (index) {
        0 -> {
            Color.Black
        }

        1 -> {
            Color.Red
        }

        else -> {
            Color(0xFFBF360C)
        }
    }
}

@Composable
fun ShopSearchBar(
    mainScreenViewModel: MainScreenViewModel,
    showBottomBar: MutableState<Boolean>,
    searchItemClicked: (Product, Search) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize(), contentAlignment = Alignment.CenterEnd
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            SearchBar(

                colors = SearchBarDefaults.colors(containerColor = NiceGreen),
                modifier = Modifier
                    .align(Alignment.TopCenter),
                query = text,
                onQueryChange = { text = it },
                onSearch = { active = false },
                active = active,
                onActiveChange = {
                    active = it
                    showBottomBar.value = !active
                },
                placeholder = {
                    Text(
                        "کلمه مورد نظر خود را وارد کنید",

                        )
                },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
//            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            ) {
                SearchResult(text, mainScreenViewModel, searchItemClicked)

            }

        }


        mainScreenViewModel.loadLastSearches()


        Column( modifier = Modifier.fillMaxWidth().padding(top = 72.dp)) {
            Divider(thickness = 1.dp, color = HeavyGreen)

            FlowRow(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight().padding(top = 10.dp),
                maxItemsInEachRow = 2,


            ) {
                if (mainScreenViewModel.lastSearchList.value.isEmpty()) {
                    //todo
                } else {
                    for (it in mainScreenViewModel.lastSearchList.value.size-1 downTo  0 ) {
                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                            AssistChip(
                                onClick = {
                                    active = true
                                    text = mainScreenViewModel.lastSearchList.value[it].name
                                },
                                label = {
                                    Text(
                                        text = mainScreenViewModel.lastSearchList.value[it].name,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                },
                                border = AssistChipDefaults.assistChipBorder(borderColor = HeavyGreen),
                                modifier = Modifier
                                    .fillMaxWidth(0.4f)
                                    .height(40.dp)
                                    .padding(bottom = 10.dp),
                                colors = AssistChipDefaults.assistChipColors(containerColor = OnNiceGreen)
                            )

                        }
                    }
                }
            }
        }

    }
}

@Composable
fun SearchResult(
    text: String,
    mainScreenViewModel: MainScreenViewModel,
    searchItemClicked: (Product, Search) -> Unit
) {
    mainScreenViewModel.getProductListByNameSearch(text)

    if (text.isEmpty()) {
        Text(text = "Nothing.......")
    } else if (mainScreenViewModel.searchListByName.value.isEmpty()) {
        Text(text = "loading...")
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = 10.dp)
        ) {
            items(mainScreenViewModel.searchListByName.value.size) { index ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(80.dp),
                        colors = CardDefaults.cardColors(containerColor = LiteNiceGreen),
                        border = BorderStroke(1.dp, HeavyGreen),
                        onClick = {
                            searchItemClicked(
                                mainScreenViewModel.searchListByName.value[index],
                                Search(text, mainScreenViewModel.searchListByName.value.size)
                            )
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {


                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(start = 15.dp)
                            ) {
                                Text(
                                    text = (index + 1).toString(),
                                    modifier = Modifier
                                        .size(25.dp)
                                        .background(HeavyGreen, CircleShape)
                                        .align(Alignment.CenterVertically),
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.width(15.dp))

                                Text(
                                    text = mainScreenViewModel.searchListByName.value[index].name,
                                    color = Color.Black,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }

                            Card(shape = RoundedCornerShape(10.dp)) {
                                AsyncImage(
                                    model = mainScreenViewModel.searchListByName.value[index].images[0].src,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(80.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }


            }
        }

    }

}
