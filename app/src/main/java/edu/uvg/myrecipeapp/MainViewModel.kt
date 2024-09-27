package edu.uvg.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log


class MainViewModel: ViewModel() {
    // Categories
    private val _categorieState = mutableStateOf(CategoryState())
    val categoriesState: State<CategoryState> = _categorieState

    // Meals
    private val _mealState = mutableStateOf(MealState())
    val mealsState: State<MealState> = _mealState

    // Recipes
    private val _recipeState = mutableStateOf(RecipeState())
    val recipesState: State<RecipeState> = _recipeState

    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            try{
                val response = recipeService.getCategories()
                _categorieState.value = _categorieState.value.copy(
                    list = response.categories
                    , loading = false
                    , error = null
                )
            }catch (e: Exception){
                _categorieState.value = _categorieState.value.copy(
                    loading = false
                    , error = "Error fetching Categories ${e.message}"
                )
            }
        } //End of viewModelScope
    }

    data class CategoryState(
        val loading: Boolean = true
        , val list: List<Category> = emptyList()
        , val error: String? = null
    )

    private fun fetchMeals(category: String){
        viewModelScope.launch {
            try{
                val response = recipeService.getMeals(category)
                _mealState.value = _mealState.value.copy(
                    list = response.meals,
                    loading = false,
                    error = null
                )
            } catch (e: Exception){
                _mealState.value = _mealState.value.copy(
                    loading = false
                    , error = "Error fetching Meals ${e.message}"
                )
            }
        }
    }

    fun onCategoryClick(category: String) {
        fetchMeals(category)
    }

    data class MealState(
        val loading: Boolean = true
        , val list: List<Meal> = emptyList()
        , val error: String? = null
    )

    private fun fetchRecipes(idMeal: String) {
        viewModelScope.launch {
            try {
                val response = recipeService.getRecipes(idMeal) // Usa idMeal en vez de "52772"
                Log.i("IDMEAL*************", response.toString())

                val meals = response.meals ?: emptyList() // Maneja el caso de null

                _recipeState.value = _recipeState.value.copy(
                    list = meals,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _recipeState.value = _recipeState.value.copy(
                    loading = false,
                    error = "Error fetching Meals: ${e.message}"
                )
            }
        }
    }

    fun onRecipeClick(idMeal: String) {
        fetchRecipes(idMeal)
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<MealRecipe> = emptyList(),
        val error: String? = null
    )
}