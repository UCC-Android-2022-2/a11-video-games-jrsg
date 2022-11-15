package com.example.videogames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val url_listar : String = "http://192.168.7.3/videogames/listar.php"
    private lateinit var adapter : VideoGameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabNuevo : FloatingActionButton = findViewById(R.id.fabNuevo)

        fabNuevo.setOnClickListener {
            nuevoVideoGame()
        }

        val lista : RecyclerView = findViewById(R.id.lista)
        adapter = VideoGameAdapter()

        lista.adapter = adapter
        lista.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun nuevoVideoGame() {
        val intent : Intent = Intent(this, VideoGameActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()

        leerLista()
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
        adapter.limpiar()

        for(i in 0..lista.length() - 1){
            val v = lista[i] as JSONObject

            var videoGame = VideoGame()

            videoGame.id        = v.getInt("id")
            videoGame.nombre    = v.getString("nombre")
            videoGame.precio    = v.getDouble("precio")
            videoGame.estado    = v.getString("estado")

            videoGame.xbox              = v.getBoolean("xbox")
            videoGame.play_station      = v.getBoolean("playstation")
            videoGame.nintendo          = v.getBoolean("nintendo")
            videoGame.pc                = v.getBoolean("pc")

            adapter.guardar( videoGame )
        }

    }
}