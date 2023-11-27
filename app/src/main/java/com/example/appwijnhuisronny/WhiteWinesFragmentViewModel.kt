package com.example.appwijnhuisronny

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.Repositories.WhiteWinesRepository

class WhiteWinesFragmentViewModel : ViewModel() {
    private val repository : WhiteWinesRepository = WhiteWinesRepository().getInstance()
    private val _allWines = MutableLiveData<List<Wine>>()
    val allWines : LiveData<List<Wine>> = _allWines
    val shoppingCart: MutableLiveData<List<Wine>> = MutableLiveData()

    init {

        repository.loadWines(_allWines)
        Log.d("ViewModel", "ViewModel initialized")

    }
    fun addToCart(wine: Wine) {
        val currentList = shoppingCart.value.orEmpty().toMutableList()
        currentList.add(wine)
        shoppingCart.postValue(currentList)
    }
}