@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalTextApi::class,
    ExperimentalLayoutApi::class
)

package com.example.shabrangkala.ui.featurs.blogScreen

import android.annotation.SuppressLint
import android.text.Html
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shabrangkala.model.data.blog.Blog
import com.example.shabrangkala.ui.theme.LiteNiceGreen
import com.example.shabrangkala.ui.theme.OnNiceGreen
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun BlogScreen(id: Int) {


    val blogViewModel = getNavViewModel<BlogScreenViewModel>()
    val navController = getNavController()



    blogViewModel.loadBlogData(id)


    val blogData = blogViewModel.blogData.value

    val text = Html.fromHtml(blogData.content.rendered, Html.FROM_HTML_MODE_LEGACY)

    var isOverlayPresented = true
    BackHandler(enabled = isOverlayPresented) {
        // your action to be called if back handler is enabled

        navController.popBackStack()

        blogViewModel.clearProductData()
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
            .padding(bottom = 50.dp)
    ) {

        if (blogData.isBlogEmpty()) {

        } else {
            Card(

                colors = CardDefaults.cardColors(containerColor = LiteNiceGreen),
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = blogData.yoast_head_json?.og_image?.get(0)?.url,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = blogData.title.rendered,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 15.dp),
                textAlign = TextAlign.Right
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = text.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(textDirection = TextDirection.Rtl),
                textAlign = TextAlign.Right,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
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