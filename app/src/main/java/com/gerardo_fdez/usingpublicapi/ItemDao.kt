package com.gerardo_fdez.usingpublicapi

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Category)

    @Update
    suspend fun update(item: Category)

    @Delete
    suspend fun delete(item: Category)

    @Query("SELECT * from categories WHERE idCategory = :id")
    fun getItem(id: Int): Flow<Category>

    @Query("SELECT * from categories ORDER BY  strCategory")
    fun getAllItems(): Flow<List<Category>>
}