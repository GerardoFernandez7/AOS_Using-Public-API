package com.gerardo_fdez.usingpublicapi

// Importamos las librerías necesarias para trabajar con flujos, contextos y el registro de mensajes
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import android.content.Context
import android.util.Log

// Definimos una clase Repository que maneja las categorías, ya sea de la API o desde la base de datos local.
class OfflineCategoryRepository(
    private val dao: Any, // Se recibe un DAO genérico (se convierte a CategoryDao más adelante)
    private val apiService: ApiService, // El servicio API para obtener los datos online
    private val context: Context // El contexto se utiliza para verificar el estado de la conexión a internet
) {
    // Se convierte el 'dao' genérico al tipo específico 'CategoryDao' para manejar las operaciones en la base de datos
    val categoryDao = dao as CategoryDao

    // Método que obtiene las categorías, ya sea desde la API o la base de datos local. Devuelve un flujo (Flow).
    fun getCategories(): Flow<List<Category>> = flow {
        // Verificamos si hay conexión a internet
        if (InternetCheck(context)) {
            Log.i("HAY INTERNET", "HAY INTERNET")
            // Si hay internet, obtenemos las categorías de la API
            val cFROMAPI = apiService.getCategories().categories
            // Insertamos las categorías obtenidas en la base de datos local para almacenamiento persistente
            categoryDao.insertCategory(cFROMAPI)  // Asegúrate de que este método es suspend
            // Emitimos la lista de categorías obtenida de la API a los suscriptores del flujo
            emit(cFROMAPI)
        } else {
            Log.i("NO HAY INTERNET", "NO HAY INTERNET")
            // Si no hay internet, obtenemos las categorías de la base de datos local
            categoryDao.getAllCategories().collect { DatabaseC ->
                // Emitimos la lista de categorías obtenida de la base de datos local
                emit(DatabaseC)
            }
        }
    }
}
