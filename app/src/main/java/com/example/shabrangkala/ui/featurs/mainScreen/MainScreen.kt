@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class, ExperimentalLayoutApi::class
)

package com.example.shabrangkala.ui.featurs.mainScreen

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shabrangkala.R
import com.example.shabrangkala.ui.theme.LiteNiceGreen
import com.example.shabrangkala.ui.theme.LiteNiceGreenWithTrans
import com.example.shabrangkala.ui.theme.NiceGreen
import com.example.shabrangkala.ui.theme.OnNiceGreen
import com.example.shabrangkala.utils.BLOG_SCREEN
import com.example.shabrangkala.utils.PRODUCT_SCREEN
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import kotlinx.coroutines.launch

const val PATH = "https://www.shabrangkala.ir/wp-content/uploads/2023/05/انتقام-جویان.jpg"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val mainScreenViewModel = getNavViewModel<MainScreenViewModel>()
    val navController = getNavController()


    val showTopBar = remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val selectedItem = remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState()

    val bottomList = listOf(
        Pair("Home", R.drawable.home),
        Pair("Wishlist", R.drawable.heart),
        Pair("Search", R.drawable.search)
    )



    Scaffold(
        topBar = { AppTopAppBar(showTopBar, scrollState) },
        floatingActionButton = { FAB(scrollState) { onFabClicked(context) } },
        bottomBar = { AppBottomBar(scrollState, selectedItem, bottomList) }) {

        when (selectedItem.intValue) {
            0 -> {
                showTopBar.value = true
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(it)
                        .fillMaxWidth()
                ) {

                    Spacer(modifier = Modifier.height(20.dp))

                    TitlePiece(title = "Categories")

                    CategoryRow(mainScreenViewModel)

                    Spacer(modifier = Modifier.height(30.dp))

                    TitlePiece(title = "Blog posts")

                    BlogRow(
                        mainScreenViewModel,
                        pagerState
                    ) { id ->
                        navController.navigate("$BLOG_SCREEN/$id")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    TitlePiece(title = "Latest Products")


                    ProductRow(mainScreenViewModel) { id ->
                        navController.navigate("$PRODUCT_SCREEN/$id")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    TitlePiece(title = "Discounts")


                    ProductRow(mainScreenViewModel, hasDiscount = true) { id ->
                        navController.navigate("$PRODUCT_SCREEN/$id")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    TitlePiece(title = "Popular product's tag")

                    TagsChips(mainScreenViewModel)

                    //FollowOnSocialMedia()


                    for (i in 0..30) {
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))

                            Column {
                                Text(text = "ali$i")
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }
                    }
                }
            }

            1 -> {

            }

            2 -> {
                showTopBar.value = false
                SearchBarSample()
            }
        }


    }
}

@Composable
fun ProductRow(
    mainScreenViewModel: MainScreenViewModel,
    hasDiscount: Boolean = false,
    onProductClicked: (Int) -> Unit
) {
    LazyRow(contentPadding = PaddingValues(end = 10.dp)) {
        items(10) {

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(Modifier.padding(top = 25.dp, start = 20.dp)) {
                    Card(
                        onClick = { onProductClicked(mainScreenViewModel.listProductImage.value[it].id) },
                        modifier = Modifier.size(width = 180.dp, height = 220.dp)
                    ) {
                        if (mainScreenViewModel.listProductImage.value.isEmpty()) {
                            Image(
                                painter = painterResource(id = R.drawable.person),
                                contentDescription = null,
                                modifier = Modifier.size(width = 180.dp, height = 180.dp)
                            )
                        } else {
                            AsyncImage(
                                model = mainScreenViewModel.listProductImage.value[it].images[0].src,
//                        model = PATH ,
                                contentDescription = null,
                                modifier = Modifier.size(width = 180.dp, height = 180.dp),
                                contentScale = ContentScale.Crop,
                                placeholder = painterResource(id = R.drawable.search),
                                error = painterResource(id = R.drawable.heart)
                            )
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
                                .width(180.dp)
                                .height(40.dp)
                                .padding(end = 5.dp)
                        ) {
                            if (mainScreenViewModel.listProductImage.value.isNotEmpty()) {

                                Text(
                                    text = mainScreenViewModel.listProductImage.value[it].name,
                                    color = OnNiceGreen,
                                    style = MaterialTheme.typography.displaySmall,
                                    modifier = Modifier.padding(bottom = 10.dp),
                                    fontWeight = FontWeight.Bold,
                                )
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

                    if (hasDiscount) {
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
                                text = "20%",
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

@Composable
fun FollowOnSocialMedia() {
    TODO("Not yet implemented")
}

//TODO
@Composable
fun BlogRow(
    mainScreenViewModel: MainScreenViewModel,
    pagerState: PagerState,
    onClicked: (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()


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
                    if (mainScreenViewModel.listProductImage.value.isEmpty()) {

                    } else {
                        AsyncImage(
                            model = mainScreenViewModel.listLastBlogPosts.value[it].yoast_head_json?.og_image?.get(0)?.url,
                            modifier = Modifier.fillMaxWidth(),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            placeholder = painterResource(id = R.drawable.search),
                            error = painterResource(id = R.drawable.heart)
                        )
                    }

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

@Composable
fun CategoryRow(mainScreenViewModel: MainScreenViewModel) {
    LazyRow(contentPadding = PaddingValues(start = 20.dp)) {
        items(mainScreenViewModel.listCategory.value.size) {

            Column {
                Card(onClick = {}) {
                    Box {
                        if (mainScreenViewModel.listProductImage.value.isEmpty()) {
                            Image(
                                painter = painterResource(id = R.drawable.person),
                                contentDescription = null,
                                modifier = Modifier.size(width = 125.dp, height = 180.dp)
                            )
                        } else {
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
                    }

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
            for(it in 0..19) {

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
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )

            TextButton(onClick = {}, Modifier.padding(end = 30.dp)) {
                Text(
                    text = "more...",
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }


    }
}

fun onFabClicked(context: Context) {
    Toast.makeText(context, "saDASDASDA", Toast.LENGTH_SHORT).show()
}


@Composable
fun AppTopAppBar(showTopBar: MutableState<Boolean>, scrollState: ScrollState) {

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

                        IconButton(onClick = { /*TODO*/ }) {
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
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(30.dp),
                                    tint = Color.Black
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
fun FAB(scrollState: ScrollState, onFabClicked: () -> Unit) {

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
            text = { Text(text = "Extended FAB") },
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    }

}

@Composable
fun AppBottomBar(
    scrollState: ScrollState,
    selectedItem: MutableState<Int>,
    bottomList: List<Pair<String, Int>>
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
fun SearchBarSample() {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .semantics {}) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics {},
            query = text,
            onQueryChange = { text = it },
            onSearch = { active = false },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = { Text("Hinted search text") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
        ) {
            repeat(4) { idx ->
                val resultText = "Suggestion $idx"
                ListItem(
                    headlineContent = { Text(resultText) },
                    supportingContent = { Text("Additional info") },
                    leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                    modifier = Modifier
                        .clickable {
                            text = resultText
                            active = false
                        }
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, top = 72.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val list = List(100) { "Text $it" }
            items(count = list.size) {
                Text(
                    list[it],
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}