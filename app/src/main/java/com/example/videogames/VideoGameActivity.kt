package com.example.videogames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class VideoGameActivity : AppCompatActivity() {
    private val url_guardado : String = "http://192.168.7.3/videogames/guardar.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_game)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.videogame_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.opc_guardar){
            guardar()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun guardar() {
        val etTitulo : EditText = findViewById(R.id.etTitulo)
        val etPrecio : EditText = findViewById(R.id.etPrecio)

        val cbXbox : CheckBox = findViewById(R.id.cbXbox)
        val cbPlaystation : CheckBox = findViewById(R.id.cbPlaystation)
        val cbNintendo : CheckBox = findViewById(R.id.cbNintendo)
        val cbPc : CheckBox = findViewById(R.id.cbPc)

        val rbNuevo : RadioButton = findViewById(R.id.rbNuevo)
        val rbUsado : RadioButton = findViewById(R.id.rbUsado)
        ////////////////////////////////////////////////////////////////////////////////

        val titulo : String = etTitulo.text.toString()
        val precio : String = etPrecio.text.toString()

        val xbox : Int          = if (cbXbox.isChecked) 1 else 0
        val playstation : Int   = if (cbPlaystation.isChecked) 1 else 0
        val nintendo : Int      = if (cbNintendo.isChecked) 1 else 0
        val pc : Int            = if (cbPc.isChecked) 1 else 0

        val estado : String     = if (rbNuevo.isChecked) "N" else "U"
        //////////////////////////////////////////////////////////////////////////////////

        val parametros = mutableMapOf<String, Any?>()

        parametros["titulo"]        = titulo
        parametros["precio"]        = precio

        parametros["xbox"]          = xbox.toString()
        parametros["playstation"]   = playstation.toString()
        parametros["nintendo"]      = nintendo.toString()
        parametros["pc"]            = pc.toString()

        parametros["estado"]        = estado

        val post : JSONObject = JSONObject(parametros)

        enviarDatos( post )
    }

    private fun enviarDatos( post : JSONObject ) {
        // cola de peticiones
        val queue = Volley.newRequestQueue(this)

        val request : JsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url_guardado,
            post,
            {
                response ->
                    if(response.getBoolean("exito")){
                        finish()
                    }else{
                        val mensaje : String = response.getString("mensaje")
                        Toast.makeText(applicationContext, mensaje, Toast.LENGTH_SHORT).show()
                    }
            },
            {
                errorResponse ->
                    Toast.makeText(applicationContext, getString(R.string.error_comunicacion), Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(request)
    }
}