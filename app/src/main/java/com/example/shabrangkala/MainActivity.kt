@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.example.shabrangkala

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shabrangkala.di.myModules
import com.example.shabrangkala.ui.featurs.LogInScreen
import com.example.shabrangkala.ui.featurs.mainScreen.MainScreen
import com.example.shabrangkala.ui.featurs.OnBoardingScreen
import com.example.shabrangkala.ui.featurs.productScreen.ProductScreen
import com.example.shabrangkala.ui.featurs.SignUpScreen
import com.example.shabrangkala.ui.featurs.SignUpSignInScreen
import com.example.shabrangkala.ui.featurs.blogScreen.BlogScreen
import com.example.shabrangkala.ui.featurs.categoryListScreen.CategoryScreen
import com.example.shabrangkala.ui.theme.ShabrangkalaTheme
import com.example.shabrangkala.utils.BLOG_SCREEN
import com.example.shabrangkala.utils.CATEGORY_LIST_SCREEN
import com.example.shabrangkala.utils.LOG_IN
import com.example.shabrangkala.utils.MAIN_SCREEN
import com.example.shabrangkala.utils.ON_BOARDING
import com.example.shabrangkala.utils.PRODUCT_SCREEN
import com.example.shabrangkala.utils.SIGN_UP
import com.example.shabrangkala.utils.SIGN_UP_SIGN_IN
import com.example.shabrangkala.utils.START
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val mainActivityViewModel =
            MainActivityViewModel(sharedPreferences)
        setContent {

            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myModules)
            }) {
                ShabrangkalaTheme {

                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {

                        ShabrangkalaUI(mainActivityViewModel)

                    }
                }
            }

        }
    }
}

@Composable
fun ShabrangkalaUI(mainActivityViewModel: MainActivityViewModel) {

    val navController = rememberNavController()

    var firstRun = mainActivityViewModel.checkFirstRunStatus()
    var logInStatus = mainActivityViewModel.checkLogInStatus()


    KoinNavHost(navController = navController, startDestination = START) {


        composable(route = START) {
            if (logInStatus) {
                MainScreen()
            } else {
                SignUpSignInScreen(firstRun = firstRun)
            }
        }

        composable(route = SIGN_UP_SIGN_IN) {
            SignUpSignInScreen(firstRun = firstRun)
        }

        composable(route = ON_BOARDING) {
            OnBoardingScreen {
                firstRun = mainActivityViewModel.changeFirstRunStatus()
                navController.popBackStack()
                navController.navigate(SIGN_UP)
            }
        }

        composable(route = LOG_IN) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LogInScreen {
                    //TODO
                    logInStatus = mainActivityViewModel.changeLogInStatus()
                    navController.popBackStack(route = START, inclusive = true)
                    navController.navigate(MAIN_SCREEN)

                }
            }
        }

        composable(route = SIGN_UP) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                SignUpScreen {
                    //TODO
                    logInStatus = mainActivityViewModel.changeLogInStatus()
                    navController.popBackStack(route = START, inclusive = true)
                    navController.navigate(MAIN_SCREEN)
                }
            }

        }

        composable(route = MAIN_SCREEN) {
            MainScreen()
        }


        composable(
            route = "$PRODUCT_SCREEN/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            ProductScreen(it.arguments!!.getInt("id", 0))
        }

        composable(
            route = "$BLOG_SCREEN/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            BlogScreen(it.arguments!!.getInt("id", 0))
        }

        composable(
            route = "$CATEGORY_LIST_SCREEN/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            CategoryScreen(it.arguments!!.getInt("id", 0))
        }
    }

}