package com.example.appwijnhuisronny.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.R
import com.example.appwijnhuisronny.WhiteWinesFragment
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class WhiteWineAdapter(private val addToCartClickListener: (Wine) -> Unit) : RecyclerView.Adapter<WhiteWineAdapter.ViewHolder>() {

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
        holder.wijndomeinTextView.text = currentItem.Wijndomein
        Picasso.get().load(currentItem.Image).into(holder.imageView)
        Picasso.get().load(currentItem.BackgroundImage).into(holder.backgroundImageView)

        holder.addToCartButton.setOnClickListener {
            addToCartClickListener.invoke(currentItem)
            Log.d("WhiteWineAdapter", "Item added to cart: $currentItem")
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textName)
        val priceTextView: TextView = view.findViewById(R.id.textPriceAmount)
        val wijndomeinTextView: TextView = view.findViewById(R.id.textWijndomein)
        val imageView: ImageView = view.findViewById(R.id.imageWine)
        val backgroundImageView: ImageView = view.findViewById(R.id.backgroundImage)
        val addToCartButton: Button = view.findViewById(R.id.addToCartButton)
    }
}