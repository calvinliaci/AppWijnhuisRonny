package com.example.appwijnhuisronny.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appwijnhuisronny.Models.WineTasting
import com.example.appwijnhuisronny.R
import com.squareup.picasso.Picasso

class WineTastingsAdapter : RecyclerView.Adapter<WineTastingsAdapter.ViewHolder>() {
    private val wineTastingsList = ArrayList<WineTasting>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.wine_tasting_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return wineTastingsList.size
    }

    fun updateWineTastingsList(wineTastingsList: List<WineTasting>) {
        this.wineTastingsList.clear()
        this.wineTastingsList.addAll(wineTastingsList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = wineTastingsList[position]

        holder.wineTastingType.text = currentItem.DegustatieType
        holder.wineTastingPrice.text = currentItem.DegustatiePrijs
        holder.wineTastingDate.text = currentItem.Datum
        Picasso.get().load(currentItem.DegustatieImage).into(holder.imageView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wineTastingType: TextView = view.findViewById(R.id.wineTastingType)
        val wineTastingPrice: TextView = view.findViewById(R.id.wineTastingPrice)
        val wineTastingDate: TextView = view.findViewById(R.id.wineTastingDate)
        val imageView: ImageView = view.findViewById(R.id.wineTastingImage)
    }
}