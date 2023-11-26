package com.example.appwijnhuisronny.Adapters

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class WhiteWineAdapter : RecyclerView.Adapter<WhiteWineAdapter.ViewHolder>() {

    private val winesList = ArrayList<Wine>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.wines_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return winesList.size
    }

    fun updateWinesList(winesList: List<Wine>) {
        this.winesList.clear()
        this.winesList.addAll(winesList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = winesList[position]

        holder.nameTextView.text = currentItem.Naam
        holder.priceTextView.text = currentItem.Prijs
        Picasso.get().load(currentItem.Image).into(holder.imageView)
        Picasso.get().load(currentItem.BackgroundImage).into(holder.backgroundImageView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textName)
        val priceTextView: TextView = view.findViewById(R.id.textPriceAmount)
        val imageView: ImageView = view.findViewById(R.id.imageWine)
        val backgroundImageView: ImageView = view.findViewById(R.id.backgroundImage)
    }
}