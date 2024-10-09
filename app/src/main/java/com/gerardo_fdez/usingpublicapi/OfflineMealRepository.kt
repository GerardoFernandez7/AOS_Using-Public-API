package com.gerardo_fdez.usingpublicapi

class OfflineMealRepository(private val database: AppDatabase) {
    suspend fun getMeals(): List<MealEntity> {
        return database.mealDao().getAllMeals() // Obtener comidas de la base de datos
    }

    suspend fun insertMeals(meals: List<MealEntity>) {
        meals.forEach { meal ->
            database.mealDao().insertMeals(meal)
        }
    }
}
