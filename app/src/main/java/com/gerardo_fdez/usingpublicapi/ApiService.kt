package com.gerardo_fdez.usingpublicapi

// Importamos las librerías necesarias de Retrofit para crear el servicio y realizar solicitudes HTTP
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Se crea una instancia de Retrofit utilizando el patrón Builder.
private val retrofit = Retrofit.Builder()
    // Establecemos la URL base de la API, en este caso, la API pública de TheMealDB.
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    // Añadimos un convertidor de Gson, que automáticamente transformará las respuestas JSON
    // en objetos de Kotlin (en este caso, las clases definidas como CategoriesResponse, MealsResponse, etc.)
    .addConverterFactory(GsonConverterFactory.create())
    // Finalmente, construimos el objeto Retrofit con la configuración previa.
    .build()

// Creamos una instancia de la interfaz ApiService utilizando el objeto Retrofit.
// Esto permitirá realizar las solicitudes HTTP definidas en ApiService.
val recipeService = retrofit.create(ApiService::class.java)

// Definimos la interfaz ApiService que contiene las diferentes solicitudes que se pueden hacer a la API.
interface ApiService {

    // Método que realiza una solicitud GET para obtener las categorías de comidas.
    // La respuesta será convertida a un objeto de tipo CategoriesResponse por Gson.
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    // Método que realiza una solicitud GET para obtener las comidas filtradas por categoría.
    // El parámetro 'category' se pasa como query string. La respuesta será de tipo MealsResponse.
    @GET("filter.php")
    suspend fun getMeals(@Query("c") category: String): MealsResponse

    // Método que realiza una solicitud GET para obtener los detalles de una receta específica.
    // El parámetro 'idMeal' se pasa como query string. La respuesta será de tipo RecipesResponse.
    @GET("lookup.php")
    suspend fun getRecipes(@Query("i") idMeal: String): RecipesResponse
}
