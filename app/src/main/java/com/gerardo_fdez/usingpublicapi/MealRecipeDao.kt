package com.gerardo_fdez.usingpublicapi

import androidx.room.Dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealRecipes(mealRecipes: MealRecipeEntity)

    @Query("SELECT * FROM meals")
    suspend fun getAllMealRecipes(): List<MealRecipeEntity>

    @Query("SELECT * FROM meals WHERE idMeal = :id")
    suspend fun getMealRecipeById(id: String): MealRecipeEntity?
}
