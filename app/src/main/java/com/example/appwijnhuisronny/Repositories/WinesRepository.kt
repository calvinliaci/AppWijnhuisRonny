package com.example.appwijnhuisronny.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appwijnhuisronny.Models.Wine
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WinesRepository {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Wijnen")
    init {
        Log.d("Repository", "Database reference initialized: $databaseReference")
    }

    @Volatile private var INSTANCE : WinesRepository ?= null
    fun getInstance(): WinesRepository {
        Log.d("Repository", "Getting instance of WinesRepository")
        return INSTANCE ?: synchronized(this) {
            INSTANCE = WinesRepository()
            INSTANCE!!
        }
    }

    fun loadWines(category: String, winesList: MutableLiveData<List<Wine>>) {
        val categoryReference = databaseReference.child(category)
        categoryReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _winesList: List<Wine> = snapshot.children.map { dataSnapshot ->
                        val wineMap = dataSnapshot.value as Map<*, *>
                        Wine(
                            wineMap["BackgroundImage"] as? String,
                            wineMap["Image"] as? String,
                            wineMap["Druif"] as? String,
                            wineMap["Info"] as? String,
                            wineMap["Inhoud"] as? String,
                            wineMap["Naam"] as? String,
                            wineMap["Prijs"] as? String,
                            wineMap["Regio"] as? String,
                            wineMap["Wijndomein"] as? String
                        )
                    }
                    winesList.postValue(_winesList)
                } catch (e: Exception) {
                    Log.w("Repository", "Failed to read:", e)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("Repository", "Failed to read value.", error.toException())
            }
        })
    }
}