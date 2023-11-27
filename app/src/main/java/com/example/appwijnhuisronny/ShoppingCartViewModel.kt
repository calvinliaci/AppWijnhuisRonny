package com.example.appwijnhuisronny

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appwijnhuisronny.Models.Wine

class ShoppingCartViewModel : ViewModel() {
    private val _shoppingCart = MutableLiveData<List<Wine>>()
    val shoppingCart: LiveData<List<Wine>> = _shoppingCart
}