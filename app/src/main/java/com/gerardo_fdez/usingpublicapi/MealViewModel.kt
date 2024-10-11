package com.gerardo_fdez.usingpublicapi

// Importamos las librerías necesarias
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Definimos el ViewModel para manejar la lógica relacionada con las comidas (Meals)
class MealViewModel(
    val dao: Any, // DAO genérico que se convertirá a MealDao más adelante
    val apiService: ApiService, // Servicio API para obtener los datos en línea
    val context: Context, // Contexto para verificar el estado de la conexión a internet
    val categoryName: String // Nombre de la categoría de comidas a consultar
): ViewModel()  {

    // Variable de estado mutable para almacenar el estado de las comidas (Meals)
    private val _mealState = mutableStateOf(MealState())
    // Exponemos el estado de las comidas como una variable inmutable para la UI
    val mealsState: State<MealState> = _mealState

    // Instancia del repositorio que gestionará la obtención de meals ya sea de la API o la base de datos local
    private val mealRepository = OfflineMealRepository(dao, apiService, context)

    // Función que se encarga de obtener las comidas por categoría
    fun fetchMeals(category: String) {
        // Usamos 'viewModelScope.launch' para realizar operaciones de forma asíncrona sin bloquear el hilo principal
        viewModelScope.launch {
            // Llamamos al repositorio para obtener las comidas filtradas por categoría
            mealRepository.getMeals(categoryName).collect { meals ->
                // Actualizamos el estado de las comidas cuando recibimos los datos
                _mealState.value = _mealState.value.copy(
                    list = meals, // Actualizamos la lista con las comidas obtenidas
                    loading = false, // Cambiamos el estado de carga a false
                    error = null // No hay errores
                )
            }
        }
    }

    // Clase de datos que representa el estado de las comidas (Meals) dentro de la UI
    data class MealState(
        val loading: Boolean = true, // Indica si la información se está cargando
        val list: List<Meal> = emptyList(), // Lista de comidas (inicialmente vacía)
        val error: String? = null // Almacena mensajes de error en caso de que haya problemas
    )
}