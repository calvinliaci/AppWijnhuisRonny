package com.example.appwijnhuisronny.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.Models.WineTasting
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WineTastingsRepository {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Degustaties")

    @Volatile private var INSTANCE : WineTastingsRepository ?= null

    fun getInstance(): WineTastingsRepository {
        return INSTANCE ?: synchronized(this) {
            INSTANCE = WineTastingsRepository()
            INSTANCE!!
        }
    }

    fun loadWineTastings(wineTastingsList: MutableLiveData<List<WineTasting>>) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Repository", "Data changed: $snapshot")
                try {
                    val _wineTastingsList: List<WineTasting> = snapshot.children.map { dataSnapshot ->
                        val wineTastingsMap = dataSnapshot.value as Map<*, *>
                        WineTasting(
                            wineTastingsMap["DegustatieType"] as? String,
                            wineTastingsMap["Datum"] as? String,
                            wineTastingsMap["DegustatiePrijs"] as? String,
                            wineTastingsMap["DegustatieImage"] as? String
                        )
                    }
                    wineTastingsList.postValue(_wineTastingsList)
                    Log.d("Repository", "Data loaded successfully")
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