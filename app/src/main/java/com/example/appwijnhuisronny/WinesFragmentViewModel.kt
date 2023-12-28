package com.example.appwijnhuisronny

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.Repositories.WinesRepository

class WinesFragmentViewModel : ViewModel() {

    private val repository: WinesRepository = WinesRepository().getInstance()
    private val _allWines = MutableLiveData<List<Wine>>()
    val allWines: LiveData<List<Wine>> = _allWines

    private val _shoppingCart = MutableLiveData<List<Wine>>()
    val shoppingCart: LiveData<List<Wine>> = _shoppingCart


    fun init(category: String) {
        repository.loadWines(category, _allWines)
        _shoppingCart.value = emptyList()
        Log.d("ViewModel", "ViewModel initialized with category: $category")
    }

}
