package com.example.shabrangkala.di

import android.content.Context
import com.example.shabrangkala.ui.featurs.LogInScreenViewModel
import com.example.shabrangkala.ui.featurs.SignUpScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {


    single { androidContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE) }
//    single { createApiService() }
//
//    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_dataBase.db").build() }
//
//    single<UserRepository> { UserRepositoryImpl(get(), get()) }
//    single<ProductRepository> { ProductRepositoryImpl(get(), get<AppDatabase>().productDao()) }
//    single<CommentRepository> { CommentRepositoryImpl(get()) }
//    single<CartRepository> { CartRepositoryImpl(get() , get()) }

    viewModel { SignUpScreenViewModel() }
    viewModel { LogInScreenViewModel() }

}