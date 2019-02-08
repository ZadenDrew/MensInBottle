package com.example.acabezaslopez.aplicationfirebase

import java.util.*
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.activity_main.*

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseReference


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    // referencia de la base de datos
    private var database: DatabaseReference? = null
    // para guardar los cambios de la base de datos
    // private var ultimoacceso: DataSnapshot? = null
    // Token del dispositivo
    var FCMToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        // referencia a la base de datos del proyecto en firebase
        database = FirebaseDatabase.getInstance().reference

        // boton de la plantilla

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Tiempo actualizado", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            // cada vez que le damos click actualizamos tiempo
            database!!.child(FCMToken.toString()).setValue(Date())
        }
        // Obtengo el token del dispositivo
        try {
            FCMToken = FirebaseInstanceId.getInstance().token
            /** guardamos el token del dispositivo tiempo actual  */
            database!!.child(FCMToken.toString()).setValue(Date())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // actualizo cada vez que se cambia el valor de la base de datos
        database!!.child(FCMToken.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val fecha = snapshot.getValue(Date().javaClass)
                miText.text = fecha.toString()
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
