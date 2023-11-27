package com.example.appwijnhuisronny.Models

import androidx.lifecycle.MutableLiveData

data class Wine(
    var BackgroundImage: String? = null,
    var Image : String ?= null,
    var Druif : String ?= null,
    var Info : String ?= null,
    var Inhoud : String ?= null,
    var Naam : String ?= null,
    var Prijs : String ?= null,
    var Regio : String ?= null,
    var Wijndomein : String ?= null,
    val quantity: MutableLiveData<Int> = MutableLiveData(0)
)
