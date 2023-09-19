@file:OptIn(ExperimentalMotionApi::class)

package com.example.shabrangkala.ui.featurs.profileScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavHostController
import com.example.shabrangkala.R
import com.example.shabrangkala.ui.theme.LiteGray
import dev.burnoo.cokoin.navigation.getNavController

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileScreen() {

    val scrollState = rememberScrollState()
    val navController = getNavController()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val socialMediaWidth = screenWidth/3 - 60.dp

    val motionProgress = mutableFloatStateOf(
        if (scrollState.value < 600) {
            (scrollState.value.toFloat() / 600)
        } else {
            1f
        }
    )
    Box() {

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(top = 72.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(112.dp))

            ProfileRow("مشاهده اطلاعات کاربری", Color.Black)
            ProfileRow("ویرایش اطلاعات کاربری", Color.Black)
            Divider(thickness = 10.dp, modifier = Modifier.fillMaxWidth(), color = LiteGray)

            ProfileRow("پیگیری سفارش", Color.Black)
            ProfileRow("سفارش دلخواه", Color.Black)
            Divider(thickness = 10.dp, modifier = Modifier.fillMaxWidth(), color = LiteGray)

            ProfileRow("راهنمای سفارش کالا", Color.Black)
            ProfileRow("قوانین و مقررات", Color.Black)
            ProfileRow("پرسش و پاسخ", Color.Black)
            Divider(thickness = 10.dp, modifier = Modifier.fillMaxWidth(), color = LiteGray)

            ProfileRow("همکاری با ما", Color.Black)
            ProfileRow("درباره ما", Color.Black)
            Divider(thickness = 10.dp, modifier = Modifier.fillMaxWidth(), color = LiteGray)

            SocialMediaRow(socialMediaWidth)
            Divider(thickness = 10.dp, modifier = Modifier.fillMaxWidth(), color = LiteGray)

            ProfileRow("خروج از حساب کاربری", Color.Red)
            ProfileRow("پاک کردن حساب کاربری", Color.Red)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LiteGray), contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Shabrangkala version 1.0.0")
                    Text(text = "Developed by AbbasShabrang", fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }


        ProfileHeader(motionProgress.floatValue, navController)
    }
}

@Composable
fun SocialMediaRow(socialMediaWidth: Dp) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "شبکه های اجتماعی", textAlign = TextAlign.Right, modifier = Modifier.align(Alignment.End).padding(end = 20.dp, top = 10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly ) {
            SocialMediaIcon(R.drawable.instagram_icon, socialMediaWidth)
            SocialMediaIcon(R.drawable.telegram_icon, socialMediaWidth)
            SocialMediaIcon(R.drawable.whatapp_icon, socialMediaWidth)
        }
        Spacer(modifier = Modifier.height(10.dp))

    }
}

@Composable
fun SocialMediaIcon(socialMediaIcon: Int, socialMediaWidth: Dp) {
    Image(
        painter = painterResource(id = socialMediaIcon),
        contentDescription = "social media icon",
        modifier = Modifier
            .border(
                width = 0.5.dp,
                shape = CircleShape,
                color = Color(0x22E0E0E0)
            )
            .clip(CircleShape)
            .clickable { }
            .size(socialMediaWidth)
    )
}

@Composable
fun ProfileRow(name: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clickable { }
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 15.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.right_arrow),
            contentDescription = "right arrow",
            tint = color,
            modifier = Modifier.rotate(180f)
        )
        Text(text = name, color = color)

    }

    Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth(), color = LiteGray)
}


@Composable
fun ProfileHeader(progress: Float, navController: NavHostController) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
    ) {
        val properties = motionProperties(id = "profile_pic")
        val color = if (progress == 1f) Color(0xFFFFFFFF) else properties.value.color("background")
        val boxBackground = motionProperties(id = "box")
        val usernameProperties = motionProperties(id = "username")
        val editIconProperties = motionProperties(id = "edit_icon")
        val backIconProperties = motionProperties(id = "back_icon")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(boxBackground.value.color("background"))
                .layoutId("box")
        )

        IconButton(onClick = {}, modifier = Modifier.layoutId("edit_icon")) {
            Icon(
                painter = painterResource(id = R.drawable.edit),
                contentDescription = "edit_profile",
                modifier = Modifier.size(24.dp),
                tint = editIconProperties.value.color("background")
            )
        }

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.layoutId("back_icon")
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back",
                modifier = Modifier.size(24.dp),
                tint = editIconProperties.value.color("background")
            )
        }

        Image(
            painter = painterResource(id = R.drawable.person),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = color,
                    shape = CircleShape
                )
                .layoutId("profile_pic")
        )
        Text(
            text = "عباس شبرنگ",
            fontSize = usernameProperties.value.fontSize("textSize"),
            modifier = Modifier.layoutId("username"),
            color = Color.White
        )
    }
}