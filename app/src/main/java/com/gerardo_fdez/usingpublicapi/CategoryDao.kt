package com.gerardo_fdez.usingpublicapi

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categories: List<Category>)

    @Query("SELECT * FROM Category")
    fun getAllCategories(): Flow<List<Category>>
}