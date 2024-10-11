package com.gerardo_fdez.usingpublicapi

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Meal")
data class Meal(
    @PrimaryKey val strMeal: String,
    val strMealThumb: String,
    val idMeal: String,
    val category: String
)

data class MealsResponse(
    val meals: List<Meal>
)

