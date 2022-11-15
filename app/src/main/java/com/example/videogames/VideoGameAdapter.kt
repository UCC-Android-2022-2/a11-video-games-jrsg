package com.example.videogames

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class VideoGameAdapter : RecyclerView.Adapter<VideoGameVH>() {
    private lateinit var data : ArrayList<VideoGame>

    init {
        data = ArrayList<VideoGame>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoGameVH {
        val view : View = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return VideoGameVH( view )
    }

    override fun onBindViewHolder(holder: VideoGameVH, position: Int) {
        val videoGame : VideoGame = data.get(position)

        holder.text1.text = videoGame.nombre
        holder.text2.text = "Precio: $" + videoGame.precio
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun guardar(videoGame: VideoGame) {
        data.add(videoGame)
        notifyDataSetChanged()
    }

    fun limpiar() {
        data.clear()
    }


}