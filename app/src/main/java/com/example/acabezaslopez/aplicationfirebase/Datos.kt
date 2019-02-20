package com.example.acabezaslopez.aplicationfirebase

import java.util.*

/**
 * Para guardar los valores que quiero introducir/actualizar en la base de datos
 * Contiene un HashMap con los datos, ya que las funciones que utilizaré necesitan como parámetro
 * un HashMap
 */
data class Datos(var token: String, var nombreDispositivo: String, var hora: Date ) {
    // contenedor para actualizar los datos
    val miHashMapDatos = HashMap<String, Any>()

    /**
     * Mete los datos del objeto en el HashMap
     */
    fun crearHashMapDatos() {
        miHashMapDatos.put("token", token)
        miHashMapDatos.put("nombre", nombreDispositivo)
        miHashMapDatos.put("hora", hora)
    }
}