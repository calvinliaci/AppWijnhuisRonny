package com.example.appwijnhuisronny

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appwijnhuisronny.Models.Wine

class ShoppingCartViewModel : ViewModel() {
    private val _shoppingCart = MutableLiveData<List<Wine>>()
    val shoppingCart: LiveData<List<Wine>> = _shoppingCart

    fun addToCart(item: Wine) {
        val currentList = _shoppingCart.value ?: emptyList()
        val updatedList = currentList.toMutableList()

        val existingItem = updatedList.find { it == item }

        if (existingItem == null) {
            updatedList.add(item)
        } else {
            existingItem.Aantal++
            existingItem.TotalPrice = existingItem.Prijs!!.toDouble() * existingItem.Aantal
        }

        _shoppingCart.value = updatedList

        updateTotalAmount()
        Log.d("ShoppingCartViewModel", "Item added to cart: $item")
        Log.d("ViewModel", "Item added to cart: $item")
        Log.d("ViewModel", "Cart Items: ${_shoppingCart.value}")
    }

    fun deleteItem(item: Wine) {
        val currentList = _shoppingCart.value ?: emptyList()
        val updatedList = currentList.toMutableList()

        // Zoek het item in de winkelwagen
        val existingItem = updatedList.find { it == item }

        if (existingItem != null) {
            // Als het item wordt gevonden, verwijder één exemplaar
            if (existingItem.Aantal > 1) {
                // Als er meer dan één exemplaar is, verminder de hoeveelheid
                existingItem.Aantal--
                existingItem.TotalPrice = existingItem.Prijs!!.toDouble() * existingItem.Aantal
            } else {
                // Als er slechts één exemplaar is, verwijder het volledige item
                updatedList.remove(existingItem)
            }

            // Update de winkelwagen
            _shoppingCart.value = updatedList

            updateTotalAmount()

            Log.d("ViewModel", "Item verwijderd uit winkelwagen: $item")
            Log.d("ViewModel", "Winkelwagen items: ${_shoppingCart.value}")
        }
    }

    private val _totalAmount = MutableLiveData(0.00
    )
    val totalAmount: LiveData<Double> = _totalAmount

    private fun updateTotalAmount() {
        val currentList = _shoppingCart.value ?: emptyList()
        val total = currentList.sumByDouble { it.TotalPrice ?: 0.00  }
        _totalAmount.postValue(total)
    }

    fun getShoppingCart(): List<Wine> {
        val wines = _shoppingCart.value ?: emptyList()
        Log.d("ShoppingCartViewModel", "Wines in cart: $wines")
        return wines
    }

    fun getTotalAmount(): Double {
        val total = _totalAmount.value ?: 0.00
        Log.d("ShoppingCartViewModel", "Total price: $total")
        return total
    }
}
