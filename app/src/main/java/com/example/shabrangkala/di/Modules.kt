package com.example.shabrangkala.di

import android.content.Context
import androidx.room.Room
import com.example.shabrangkala.model.data.repository.blogRepository.BlogRepository
import com.example.shabrangkala.model.data.repository.blogRepository.BlogRepositoryImp
import com.example.shabrangkala.model.data.repository.productRepository.ProductRepository
import com.example.shabrangkala.model.data.repository.productRepository.ProductRepositoryImp
import com.example.shabrangkala.model.db.AppDatabase
import com.example.shabrangkala.model.net.createApiService
import com.example.shabrangkala.ui.featurs.logInScreen.LogInScreenViewModel
import com.example.shabrangkala.ui.featurs.signUpScreen.SignUpScreenViewModel
import com.example.shabrangkala.ui.featurs.blogScreen.BlogScreenViewModel
import com.example.shabrangkala.ui.featurs.cartScreen.CartScreenViewModel
import com.example.shabrangkala.ui.featurs.categoryListScreen.CategoryScreenViewModel
import com.example.shabrangkala.ui.featurs.mainScreen.MainScreenViewModel
import com.example.shabrangkala.ui.featurs.productScreen.ProductScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {


    single { androidContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE) }
    single { createApiService() }
//
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_dataBase.db").build()
    }
//
//    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<ProductRepository> { ProductRepositoryImp(get(), get<AppDatabase>().productDao(), get<AppDatabase>().cartProductDao()) }
    single<BlogRepository> { BlogRepositoryImp(get()) }

//    single<CommentRepository> { CommentRepositoryImpl(get()) }
//    single<CartRepository> { CartRepositoryImpl(get() , get()) }

    viewModel { SignUpScreenViewModel() }
    viewModel { LogInScreenViewModel() }
    viewModel { MainScreenViewModel(get(), get()) }
    viewModel { ProductScreenViewModel(get()) }
    viewModel { BlogScreenViewModel(get())}
    viewModel { CategoryScreenViewModel(get()) }
    viewModel { CartScreenViewModel(get())}

}