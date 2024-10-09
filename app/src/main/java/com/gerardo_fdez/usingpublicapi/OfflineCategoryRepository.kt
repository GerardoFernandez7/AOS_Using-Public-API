package com.gerardo_fdez.usingpublicapi

import kotlinx.coroutines.flow.first

class OfflineCategoryRepository(private val database: AppDatabase) {
    suspend fun getCategories(): List<CategoryEntity> {
        return database.categoryDao().getAllCategories() // Obtener categorías de la base de datos
    }

    suspend fun insertCategories(categories: List<CategoryEntity>) {
        categories.forEach { category ->
            database.categoryDao().insertCategories(category)
        }
    }
}
