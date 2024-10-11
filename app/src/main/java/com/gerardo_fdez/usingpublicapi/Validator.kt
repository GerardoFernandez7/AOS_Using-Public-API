package com.gerardo_fdez.usingpublicapi
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

// Función que verifica si hay conexión a internet
fun InternetCheck(context: Context): Boolean {
    // Obtiene el servicio de administración de conectividad (ConnectivityManager) para manejar las conexiones de red
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // Si la versión de Android es igual o superior a Marshmallow (M), se utiliza la clase NetworkCapabilities
    // para verificar qué tipo de red está conectada a internet
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        // Obtiene la red activa (la red de datos predeterminada) o retorna false si no hay ninguna
        val network = connectivityManager.activeNetwork ?: return false

        // Obtiene las capacidades de la red activa, o retorna false si no hay ninguna
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        // Verifica el tipo de conexión y retorna true si hay conectividad
        return when {
            // Si la red activa usa Wi-Fi, indica que hay conectividad por Wi-Fi
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Si la red activa usa datos móviles, indica que hay conectividad por datos celulares
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // Si no es Wi-Fi ni datos móviles, retorna false (no hay conexión)
            else -> false
        }
    } else {
        // Para versiones anteriores a Marshmallow (M), se utiliza el método deprecated (obsoleto) `activeNetworkInfo`
        @Suppress("DEPRECATION") val networkInfo = connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        // Retorna true si la red está conectada
        return networkInfo.isConnected
    }
}