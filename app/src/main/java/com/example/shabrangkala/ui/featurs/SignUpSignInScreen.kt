package com.example.shabrangkala.ui.featurs

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shabrangkala.R
import com.example.shabrangkala.utils.LOG_IN
import com.example.shabrangkala.utils.ON_BOARDING
import com.example.shabrangkala.utils.SIGN_UP
import dev.burnoo.cokoin.navigation.getNavController

@Composable
fun SignUpSignInScreen(firstRun : Boolean) {

    val navigation = getNavController()
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.queue),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
        )
        MainElements {
            Log.e("abbas", firstRun.toString())
            if (it == SIGN_UP && firstRun) {
                navigation.navigate(ON_BOARDING)
            }else if (it == SIGN_UP){
                navigation.navigate(SIGN_UP)
            }else if (it == LOG_IN){
                navigation.navigate(it)
            }
        }
    }

}

@Composable
fun MainElements(onButtonClicked: (String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign UP | Sign IN",
            fontFamily = FontFamily(Font(R.font.brandier_trial_bold)),
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Welcome to Shabrangkala",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "You can find all decorative things that you want!",
            modifier = Modifier.padding(top = 5.dp),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(50.dp))

        TextButton(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = Color(0xFF43A047)
            ),
            onClick = { onButtonClicked(SIGN_UP) }
        ) {
            Text(
                text = "Sign Up",
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(30.dp))


        TextButton(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color(0xFF43A047)
            ),
            onClick = { onButtonClicked(LOG_IN) }
        ) {
            Text(
                text = "Log In",
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFF2F8336)
            )
        }
    }


}
