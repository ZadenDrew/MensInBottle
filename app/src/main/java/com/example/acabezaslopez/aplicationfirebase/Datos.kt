package com.example.acabezaslopez.aplicationfirebase

import java.util.*

/**
 * Para guardar los valores que quiero introducir/actualizar en la base de datos
 * Contiene un HashMap con los datos, ya que las funciones que utilizaré necesitan como parámetro
 * un HashMap
 */
data class Datos(var token: String, var nombre: String, var hora: Date ) {
    // contenedor para actualizar los datos
    val miHashMapDatos = HashMap<String, Any>()

    /**
     * Mete los datos del objeto en el HashMap
     */
    fun crearHashMapDatos() {
        miHashMapDatos["token"] = token
        miHashMapDatos["nombre"] = nombre
        miHashMapDatos["hora"] = hora
    }
}