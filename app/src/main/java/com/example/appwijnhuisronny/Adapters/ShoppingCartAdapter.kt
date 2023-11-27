package com.example.appwijnhuisronny.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.R

class ShoppingCartAdapter(private val addToCartClickListener: WhiteWineAdapter.OnAddToCartClickListener) :
    RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    private val cartItems = mutableListOf<Wine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.shoppingcart_row,
            parent, false
        )
        return ShoppingCartAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = cartItems[position]
        // ... bind data to ViewHolder
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun updateCartItems(newItems: List<Wine>) {
        cartItems.clear()
        cartItems.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // ... ViewHolder components
    }
}
