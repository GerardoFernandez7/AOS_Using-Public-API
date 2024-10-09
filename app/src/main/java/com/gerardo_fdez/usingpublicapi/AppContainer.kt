package com.gerardo_fdez.usingpublicapi

import android.content.Context

class AppContainer(context: Context) {
    private val database: AppDatabase = AppDatabase.getDatabase(context)

    // Offline Repositorios
    val categoryRepository = OfflineCategoryRepository(database)
    val mealRepository = OfflineMealRepository(database)
    val mealRecipeRepository = OfflineMealRecipeRepository(database)


}
