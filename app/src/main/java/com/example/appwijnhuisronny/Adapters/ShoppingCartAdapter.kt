package com.example.appwijnhuisronny.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.R
import com.squareup.picasso.Picasso

class ShoppingCartAdapter() : RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    private val cartItems = mutableListOf<Wine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.shoppingcart_row,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = cartItems[position]

        holder.productNameTextView.text = currentItem.Naam
        holder.productTotalPriceTextView.text = currentItem.Prijs
        Picasso.get().load(currentItem.Image).into(holder.productImageView)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun updateCartItems(newItems: List<Wine>) {
        cartItems.clear()
        cartItems.addAll(newItems)
        notifyDataSetChanged()
        Log.d("ShoppingCartAdapter", "Updated cart items: $newItems")
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productNameTextView: TextView = view.findViewById(R.id.productNameTextView)
        val productTotalPriceTextView: TextView = view.findViewById(R.id.productTotalPriceTextView)
        val productImageView: ImageView = view.findViewById(R.id.productImageView)
    }
}
