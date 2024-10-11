package com.gerardo_fdez.usingpublicapi

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


//Misma Logica que en MealViewModel

class MainViewModel(
    val dao: Any,
    val apiService: ApiService,
    val context: Context
): ViewModel() {
    // Variables para categories
    private val _categoriesState = MutableStateFlow(CategoryState())
    val categoriesState: StateFlow<CategoryState> = _categoriesState.asStateFlow()




    private val categoryRepository = OfflineCategoryRepository(dao, apiService, context)

    init {
        fetchCategories()
    }

    fun fetchCategories() {
        viewModelScope.launch {
            categoryRepository.getCategories().collect { categories ->
                _categoriesState.value = _categoriesState.value.copy(
                    list = categories,
                    loading = false,
                    error = null
                )
            }
        }
    }

    data class CategoryState(
        val loading: Boolean = true
        , val list: List<Category> = emptyList()
        , val error: String? = null
    )


}