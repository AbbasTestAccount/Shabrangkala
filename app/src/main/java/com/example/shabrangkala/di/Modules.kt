package com.example.shabrangkala.di

import android.content.Context
import androidx.room.Room
import com.example.shabrangkala.model.data.repository.BlogRepository
import com.example.shabrangkala.model.data.repository.BlogRepositoryImp
import com.example.shabrangkala.model.data.repository.ProductRepository
import com.example.shabrangkala.model.data.repository.ProductRepositoryImp
import com.example.shabrangkala.model.db.AppDatabase
import com.example.shabrangkala.model.net.createApiService
import com.example.shabrangkala.ui.featurs.LogInScreenViewModel
import com.example.shabrangkala.ui.featurs.SignUpScreenViewModel
import com.example.shabrangkala.ui.featurs.blogScreen.BlogScreenViewModel
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
    single<ProductRepository> { ProductRepositoryImp(get()) }
    single<BlogRepository> { BlogRepositoryImp(get()) }

//    single<CommentRepository> { CommentRepositoryImpl(get()) }
//    single<CartRepository> { CartRepositoryImpl(get() , get()) }

    viewModel { SignUpScreenViewModel() }
    viewModel { LogInScreenViewModel() }
    viewModel { MainScreenViewModel(get(), get()) }
    viewModel { ProductScreenViewModel(get()) }
    viewModel { BlogScreenViewModel(get())}

}