package com.example.videogames

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
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
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import org.json.JSONObject
import java.io.File

class VideoGameActivity : AppCompatActivity() {
    private val url_guardado : String = "http://192.168.7.3/videogames/guardar.php"

    private lateinit var etTitulo : EditText
    private lateinit var etPrecio : EditText
    private lateinit var cbXbox : CheckBox
    private lateinit var cbPlaystation : CheckBox
    private lateinit var cbNintendo : CheckBox
    private lateinit var cbPc : CheckBox
    private lateinit var rbNuevo : RadioButton
    private lateinit var rbUsado : RadioButton

    private var id = 0

    private lateinit var storage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_game)

        etTitulo        = findViewById(R.id.etTitulo)
        etPrecio        = findViewById(R.id.etPrecio)
        cbXbox          = findViewById(R.id.cbXbox)
        cbPlaystation   = findViewById(R.id.cbPlaystation)
        cbNintendo      = findViewById(R.id.cbNintendo)
        cbPc            = findViewById(R.id.cbPc)
        rbNuevo         = findViewById(R.id.rbNuevo)
        rbUsado         = findViewById(R.id.rbUsado)

        configUi()

        storage = Firebase.storage
    }

    private fun configUi() {
        val intent = intent

        if( intent != null && intent.hasExtra("id") ){
            id = intent.getIntExtra("id", 0)

            val nombre = intent.getStringExtra("nombre")
            val precio = intent.getDoubleExtra("precio", 0.0)
            val estado = intent.getStringExtra("estado")

            val xbox            = intent.getBooleanExtra("xbox", false)
            val play_station    = intent.getBooleanExtra("play_station", false)
            val nintendo        = intent.getBooleanExtra("nintendo", false)
            val pc              = intent.getBooleanExtra("pc", false)

            etTitulo.setText( nombre )
            etPrecio.setText( precio.toString() )

            rbNuevo.isChecked = estado.equals("N")
            rbUsado.isChecked = estado.equals("U")

            cbXbox.isChecked = xbox
            cbPlaystation.isChecked = play_station
            cbNintendo.isChecked = nintendo
            cbPc.isChecked = pc

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        if(id == 0) {
            menuInflater.inflate(R.menu.videogame_menu, menu)
        }else{
            menuInflater.inflate(R.menu.videogame_edita_menu, menu)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.opc_guardar){
            guardar()
        }

        if(item.itemId == R.id.opc_eliminar){
            eliminar()
        }

        if(item.itemId == R.id.opc_imagen){
            agregarImagen()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun agregarImagen() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                if(data != null) {
                    val uri: Uri? = data.data

                    subirImagen(uri)
                }
            }
        }
    }

    private fun subirImagen(uri: Uri) {
        var file = Uri.fromFile(File( uri ))
        var storageRef = storage.reference
        val imageRef = storageRef.child("images/${file.lastPathSegment}")
        imageRef.putFile(file)
    }

    private fun eliminar() {
        TODO("Not yet implemented")
    }

    private fun guardar() {

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

        parametros["id"]            = id
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