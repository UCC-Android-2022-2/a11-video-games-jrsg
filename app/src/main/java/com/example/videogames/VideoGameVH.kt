package com.example.videogames

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoGameVH : RecyclerView.ViewHolder {
    val text1 : TextView
    var text2 : TextView

    constructor(itemView : View) : super(itemView){
        text1 = itemView.findViewById(android.R.id.text1)
        text2 = itemView.findViewById(android.R.id.text2)
    }

}
