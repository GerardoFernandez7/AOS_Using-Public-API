package com.gerardo_fdez.usingpublicapi

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch
//Misma logica que en MealViewModel
data class RecipeViewModel(
    val dao: Any,
    val apiService: ApiService,
    val context: Context,
    val idMeal: String
): ViewModel(){

    private val _recipeState = mutableStateOf(RecipeState())
    val recipesState: State<RecipeState> = _recipeState

    private val recipeRepository =OfflineRecipeRepository(dao, apiService, context)


    fun fetchRecipes(idMeal: String) {
        viewModelScope.launch {
            recipeRepository.getRecipe(idMeal).collect { recipes ->
                try {
                    _recipeState.value = _recipeState.value.copy(
                        list = recipes, // Asigna la lista de recipes obtenidos del repositorio
                        loading = false,
                        error = null
                    )
                } catch (e: Exception) {
                    _recipeState.value = _recipeState.value.copy(
                        loading = false,
                        error = "Error fetching Recipes: ${e.message}"
                    )
                }
            }
        }
    }

    fun onRecipeClick(idMeal: String) {
        fetchRecipes(idMeal) // Llama al método para obtener recetas
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<MealRecipe> = emptyList(), // Cambiado a MealRecipe
        val error: String? = null
    )

}
