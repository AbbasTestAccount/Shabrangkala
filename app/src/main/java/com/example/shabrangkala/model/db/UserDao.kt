package com.example.shabrangkala.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shabrangkala.model.data.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAddress(user: User)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)


}