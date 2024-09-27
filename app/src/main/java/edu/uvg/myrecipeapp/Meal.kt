package edu.uvg.myrecipeapp

data class Meal(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: String
)

data class MealsResponse(
    val meals: List<Meal>
)