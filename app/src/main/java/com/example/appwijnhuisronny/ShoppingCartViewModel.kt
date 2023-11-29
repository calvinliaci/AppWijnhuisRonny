package com.example.appwijnhuisronny

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appwijnhuisronny.Models.Wine

class ShoppingCartViewModel : ViewModel() {
    private val _shoppingCart = MutableLiveData<List<Wine>>()
    val shoppingCart: LiveData<List<Wine>> = _shoppingCart

    fun addToCart(wine: Wine) {
        val currentList = _shoppingCart.value ?: emptyList()
        val updatedList = currentList.toMutableList()
        updatedList.add(wine)

        _shoppingCart.postValue(updatedList)

        Log.d("ViewModel", "Item added to cart: $wine")
        Log.d("ViewModel", "Cart Items: ${_shoppingCart.value}")
    }
}
