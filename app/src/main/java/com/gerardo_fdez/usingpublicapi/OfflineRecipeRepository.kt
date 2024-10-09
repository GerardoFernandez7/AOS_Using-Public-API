package com.gerardo_fdez.usingpublicapi

class OfflineMealRecipeRepository(private val database: AppDatabase) {
    suspend fun getMealRecipes(): List<MealRecipeEntity> {
        return database.mealrecipeDao().getAllMealRecipes() // Obtener todas las recetas de la base de datos
    }

    suspend fun getMealRecipeById(id: String): MealRecipeEntity? {
        return database.mealrecipeDao().getMealRecipeById(id) // Obtener receta por ID
    }

    suspend fun insertMealRecipes(recipes: List<MealRecipeEntity>) {
        recipes.forEach { recipe ->
            database.mealrecipeDao().insertMealRecipes(recipe) // Insertar receta
        }
    }
}

