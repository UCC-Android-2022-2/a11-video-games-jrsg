package com.example.videogames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import com.android.volley.toolbox.Volley

class VideoGameActivity : AppCompatActivity() {
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

        enviarDatos()
    }

    private fun enviarDatos() {
        // cola de peticiones
        val queue = Volley.newRequestQueue(this)


    }
}