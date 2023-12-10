package com.example.appwijnhuisronny

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.Models.WineTasting
import com.example.appwijnhuisronny.Repositories.WhiteWinesRepository
import com.example.appwijnhuisronny.Repositories.WineTastingsRepository

class WineTastingsViewModel : ViewModel() {
    private val repository: WineTastingsRepository = WineTastingsRepository().getInstance()
    private val _allWineTastings = MutableLiveData<List<WineTasting>>()
    val allWineTastings: LiveData<List<WineTasting>> = _allWineTastings

    init {
        repository.loadWineTastings(_allWineTastings)
    }
}