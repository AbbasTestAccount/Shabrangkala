package com.example.shabrangkala.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shabrangkala.model.data.Search

@Dao
interface SearchedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSearchToList(lastSearches: Search)

    @Query("SELECT * FROM Search")
    suspend fun getAllSearch(): List<Search>

    @Delete
    suspend fun deleteSearchItem(search: Search)

}