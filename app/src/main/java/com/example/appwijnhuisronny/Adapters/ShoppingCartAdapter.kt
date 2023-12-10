package com.example.appwijnhuisronny.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.R
import com.squareup.picasso.Picasso

class ShoppingCartAdapter(private val deleteItemClickListener: (Wine) -> Unit) : RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

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
        holder.productTotalPriceTextView.text = currentItem.TotalPrice.toString()
        holder.quantityTextView.text = currentItem.Aantal.toString()
        Picasso.get().load(currentItem.Image).into(holder.productImageView)
        Picasso.get().load(currentItem.BackgroundImage).into(holder.backgroundImageView)

        holder.deleteItemButton.setOnClickListener {
            // Roep de deleteItem-methode aan met het huidige item
            deleteItemClickListener.invoke(currentItem)
        }
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
        val quantityTextView : TextView = view.findViewById(R.id.aantalTextView)
        val productImageView: ImageView = view.findViewById(R.id.imageWine)
        val backgroundImageView: ImageView = view.findViewById(R.id.backgroundImage)
        val deleteItemButton : ImageButton = view.findViewById(R.id.deleteProductButton)
    }
}
