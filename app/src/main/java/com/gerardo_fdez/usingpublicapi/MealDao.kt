package com.gerardo_fdez.usingpublicapi

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(meal: MealEntity) // Asegúrate de usar MealEntity

    @Query("SELECT * FROM meals")
    suspend fun getAllMeals(): List<MealEntity>

    @Query("SELECT * FROM meals WHERE idMeal = :id")
    suspend fun getMealById(id: String): MealEntity?
}
