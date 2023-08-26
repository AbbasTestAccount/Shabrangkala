package com.example.shabrangkala.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shabrangkala.model.data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}