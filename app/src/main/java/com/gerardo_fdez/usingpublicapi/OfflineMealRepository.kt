package com.gerardo_fdez.usingpublicapi

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import android.content.Context
import android.util.Log

//Misma Logica que en el OfflineCategoryRespository

class OfflineMealRepository(
    private val dao: Any,
    private val apiService: ApiService,
    private val context: Context
) {
    val mealDao = dao as MealDao

    fun getMeals(categoryName: String): Flow<List<Meal>> = flow {
        if (InternetCheck(context)) {
            Log.i("SI hay internet", "Si hay internet")
            // Obtén las comidas de la API
            val mFROMAPI = apiService.getMeals(categoryName).meals

            // Mapea la lista a una nueva lista de 'Meal' con la categoría establecida
            val mWithC = mFROMAPI.map { meal ->
                Meal(
                    strMeal = meal.strMeal,
                    strMealThumb = meal.strMealThumb,
                    idMeal = meal.idMeal,
                    category = categoryName
                )
            }


            mealDao.insertMeal(mWithC)


            emit(mFROMAPI)
        } else {
            Log.i("No hay internet", "no hay internet")

            mealDao.getAllMeals(categoryName).collect { mealsFromDb ->
                emit(mealsFromDb)
            }
        }
    }
}
