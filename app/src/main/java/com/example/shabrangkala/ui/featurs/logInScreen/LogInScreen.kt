@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.shabrangkala.ui.featurs.logInScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.shabrangkala.R
import com.example.shabrangkala.ui.featurs.signUpScreen.SignTextField
import com.example.shabrangkala.ui.theme.LiteGray
import com.example.shabrangkala.ui.theme.LiteNiceGreen
import com.example.shabrangkala.ui.theme.MediumGray
import com.example.shabrangkala.ui.theme.NiceGreen
import com.example.shabrangkala.utils.SIGN_UP_SIGN_IN
import com.example.shabrangkala.utils.rememberImeState
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun LogInScreen(onLoginClicked: (Pair<String, String>) -> Unit) {
    val context = LocalContext.current
    val viewModel = getNavViewModel<LogInScreenViewModel>()
    val navController = getNavController()

    val userName = viewModel.userName.observeAsState("")
    val password = viewModel.password.observeAsState("")

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value){
            scrollState.animateScrollTo(scrollState.maxValue, tween(500))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .background(NiceGreen)
                .align(Alignment.TopCenter)
        )
    }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.9f)
            .padding(top = 20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {

        IconButton(
            { navController.navigate(SIGN_UP_SIGN_IN) },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back button",
                modifier = Modifier
                    .background(LiteNiceGreen, CircleShape)
                    .padding(5.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Log in",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )

        Text(
            text = "Welcome back !",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            SignTextField(
                first = true,
                userName.value,
                "Username",
                R.drawable.person,
                keyboardType = KeyboardType.Text,
                needScroll = mutableStateOf(true)
            ) {
                viewModel.userName.value = it
            }

            SignTextField(
                input = password.value,
                text = "Password",
                icon = R.drawable.password,
                keyboardType = KeyboardType.Password,
                needScroll = mutableStateOf(true)
            ) {
                viewModel.password.value = it
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextButton(
                onClick = {}, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "forgot ?",
                    color = MaterialTheme.colorScheme.tertiary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displaySmall
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            modifier = Modifier.fillMaxWidth(0.9f),
            shape = RoundedCornerShape(10.dp),
            onClick = { onLoginClicked(Pair(userName.value, password.value)) }
        ) {
            Text(text = "Log in", modifier = Modifier.padding(10.dp))
        }

        Text(
            text = "or continue with",
            modifier = Modifier.padding(top = 10.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MediumGray
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Card(
                onClick = {
                    Toast.makeText(
                        context,
                        "this feature isn't active right now",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = CircleShape
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "google login",
                    modifier = Modifier
                        .background(Color.White, CircleShape)
                        .size(40.dp)
                        .border(BorderStroke(0.5.dp, LiteGray), shape = CircleShape)
                        .padding(2.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

            Card(
                onClick = {
                    Toast.makeText(
                        context,
                        "this feature isn't active right now",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = CircleShape

            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "facebook login",
                    modifier = Modifier
                        .background(Color.White, CircleShape)
                        .size(40.dp)
                        .border(BorderStroke(0.5.dp, LiteGray), shape = CircleShape)
                        .padding(5.dp),
                )
            }
        }
    }
}