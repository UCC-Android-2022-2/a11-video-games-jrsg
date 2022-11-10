package com.example.videogames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private val url_listar : String = "http://192.168.7.3/videogames/listar.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabNuevo : FloatingActionButton = findViewById(R.id.fabNuevo)

        fabNuevo.setOnClickListener {
            nuevoVideoGame()
        }
    }

    private fun nuevoVideoGame() {
        val intent : Intent = Intent(this, VideoGameActivity::class.java)
        startActivity(intent)
    }

    private fun leerLista(){
        val queue : RequestQueue = Volley.newRequestQueue(this)

        val request : JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url_listar,
            null,
            {
                response->
                    if(response.getBoolean("exito")){
                        llenarLista( response.getJSONArray("lista") )
                    }
            },
            {
                errorListener->
            }
        )

        queue.add(request)
    }

    private fun llenarLista(lista: JSONArray) {

    }
}