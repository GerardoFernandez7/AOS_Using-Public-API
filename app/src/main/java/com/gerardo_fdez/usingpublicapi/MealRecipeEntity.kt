package com.gerardo_fdez.usingpublicapi

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealRecipeEntity(
    @PrimaryKey val idMeal: String,
    val strMeal: String,
    val strDrinkAlternate: String?,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strTags: String?,
    val strYoutube: String?,
    // Para los ingredientes
    val strIngredient1: String?,
    val strMeasure1: String?,
    // ... agrega todos los ingredientes hasta 20
    val strIngredient20: String?,
    val strMeasure20: String?
)
