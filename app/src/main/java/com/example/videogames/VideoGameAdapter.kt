package com.example.videogames

import android.content.Intent
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

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, VideoGameActivity::class.java)

            intent.putExtra("id", videoGame.id)
            intent.putExtra("nombre", videoGame.nombre)
            intent.putExtra("precio", videoGame.precio)
            intent.putExtra("estado", videoGame.estado)
            intent.putExtra("xbox", videoGame.xbox)
            intent.putExtra("play_station", videoGame.play_station)
            intent.putExtra("nintendo", videoGame.nintendo)
            intent.putExtra("pc", videoGame.pc)

            holder.itemView.context.startActivity(intent)
        }
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