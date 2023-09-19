@file:OptIn(ExperimentalMotionApi::class)

package com.example.shabrangkala.ui.featurs.profileScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavHostController
import com.example.shabrangkala.R
import dev.burnoo.cokoin.navigation.getNavController

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileScreen() {

    val scrollState = rememberScrollState()
    val navController = getNavController()

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
                .padding(top = 72.dp)
        ) {

            Spacer(Modifier.height(138.dp))
            for (i in 0..40) {
                Text(text = i.toString(), Modifier.size(40.dp))
            }
        }


        ProfileHeader(motionProgress.floatValue, navController)
    }
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(boxBackground.value.color("background"))
                .layoutId("box")
        )

        IconButton(onClick = {navController.popBackStack()}, modifier = Modifier.layoutId("back_icon")) {
            Icon(painter = painterResource(id = R.drawable.back), contentDescription = "back", modifier = Modifier.size(24.dp))
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