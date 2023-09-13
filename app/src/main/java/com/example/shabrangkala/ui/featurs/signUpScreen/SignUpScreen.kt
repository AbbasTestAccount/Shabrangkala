@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.shabrangkala.ui.featurs.signUpScreen

import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.shabrangkala.R
import com.example.shabrangkala.model.data.User
import com.example.shabrangkala.ui.theme.LiteGray
import com.example.shabrangkala.ui.theme.LiteNiceGreen
import com.example.shabrangkala.ui.theme.MediumGray
import com.example.shabrangkala.ui.theme.NiceGreen
import com.example.shabrangkala.utils.SIGN_UP_SIGN_IN
import com.example.shabrangkala.utils.TEXT_FIELD_ICON_SIZE
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel

@Composable
fun SignUpScreen(onSignUpClicked: (User) -> Unit) {
    val context = LocalContext.current
    val viewModel = getNavViewModel<SignUpScreenViewModel>()
    val navController = getNavController()

    val userName = viewModel.userName.observeAsState("")
    val email = viewModel.email.observeAsState("")
    val phoneNumber = viewModel.phoneNumber.observeAsState("")
    val password = viewModel.password.observeAsState("")
    val confirmPassword = viewModel.confirmPassword.observeAsState("")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .background(NiceGreen)
                .align(Alignment.TopCenter)
        )
    }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.9f)
            .padding(top = 20.dp)
            .verticalScroll(rememberScrollState()),
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
            text = "Create an account",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )

        Text(
            text = "Just one step to get started",
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
                keyboardType = KeyboardType.Text
            ) {
                viewModel.userName.value = it
            }

            SignTextField(
                input = email.value,
                text = "Email",
                icon = R.drawable.at_sign,
                keyboardType = KeyboardType.Email
            ) {
                viewModel.email.value = it
            }

            SignTextField(
                input = phoneNumber.value,
                text = "Phone number",
                icon = R.drawable.phone,
                keyboardType = KeyboardType.Phone
            ) {
                viewModel.phoneNumber.value = it
            }

            SignTextField(
                input = password.value,
                text = "Password",
                icon = R.drawable.password,
                keyboardType = KeyboardType.Password
            ) {
                viewModel.password.value = it
            }

            SignTextField(
                input = confirmPassword.value,
                text = "Confirm password",
                icon = R.drawable.lock,
                keyboardType = KeyboardType.Password
            ) {
                viewModel.confirmPassword.value = it
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            modifier = Modifier.fillMaxWidth(0.9f),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                onSignUpClicked(
                    User(
                        userName = userName.value,
                        email = email.value,
                        phoneNumber = phoneNumber.value,
                        password = password.value,
                        listOfOrders = arrayListOf()
                        )
                )
            }) {
            Text(text = "Create an account", modifier = Modifier.padding(10.dp))
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


@Composable
fun SignTextField(
    first: Boolean = false,
    input: String,
    text: String,
    icon: Int,
    keyboardType: KeyboardType,
    onValueChanged: (String) -> Unit
) {
    val seePassword = if (text == "Confirm password" || text == "Password") {
        remember { mutableStateOf(false) }
    } else {
        remember { mutableStateOf(true) }

    }

    if (!first) {
        Spacer(modifier = Modifier.height(20.dp))
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            value = input,
            onValueChange = { onValueChanged(it) },
            label = { Text(text) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(TEXT_FIELD_ICON_SIZE.dp),
                    tint = Color.Black
                )
            },
            trailingIcon = if (text == "Confirm password" || text == "Password") {
                {
                    IconButton(onClick = { seePassword.value = !seePassword.value }) {
                        if (!seePassword.value) {
                            Icon(
                                painter = painterResource(id = R.drawable.visible),
                                contentDescription = "visible",
                                modifier = Modifier.size(TEXT_FIELD_ICON_SIZE.dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.invisible),
                                contentDescription = "invisible",
                                modifier = Modifier.size(TEXT_FIELD_ICON_SIZE.dp)
                            )
                        }
                    }
                }
            } else {
                null
            },

            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = LiteGray,
                disabledBorderColor = MediumGray,
                focusedLabelColor = MediumGray,
            ),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (!seePassword.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
        )
    }

}