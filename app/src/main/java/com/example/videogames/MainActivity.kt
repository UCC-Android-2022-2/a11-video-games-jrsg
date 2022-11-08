package com.example.videogames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
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
}