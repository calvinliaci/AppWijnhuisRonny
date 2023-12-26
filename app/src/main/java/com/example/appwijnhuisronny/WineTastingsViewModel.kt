package com.example.appwijnhuisronny

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appwijnhuisronny.Models.OrderDetails
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.Models.WineTasting
import com.example.appwijnhuisronny.Repositories.WhiteWinesRepository
import com.example.appwijnhuisronny.Repositories.WineTastingsRepository

class WineTastingsViewModel : ViewModel() {
    fun getBodyText(wineTasting: WineTasting): String {
        val stringBuilder = StringBuilder()

        // Thank you message
        stringBuilder.append("Ik/Wij zouden graag komen naar de: ${wineTasting.DegustatieType}!\n\n")
        stringBuilder.append("Wij zijn in totaal met: ZELF IN TE VULLEN!\n")
        stringBuilder.append("\n")
        stringBuilder.append("Graag alle namen invullen: ZELF IN TE VULLEN!\n")
        stringBuilder.append("\n")
        stringBuilder.append("Prijs bedraagt €15 per persoon\n")
        stringBuilder.append("Gelieve te betalen bij afhaal! (Cash/Payconiq)\n")

        return stringBuilder.toString()
    }

    private val repository: WineTastingsRepository = WineTastingsRepository().getInstance()
    private val _allWineTastings = MutableLiveData<List<WineTasting>>()
    val allWineTastings: LiveData<List<WineTasting>> = _allWineTastings

    init {
        repository.loadWineTastings(_allWineTastings)
    }
}