package com.example.appwijnhuisronny.Repositories

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomePagePhotosRepository private constructor() {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Startpagina")

    init {
        Log.d("Repository", "Database reference initialized: $databaseReference")
    }

    companion object {
        @Volatile
        private var INSTANCE: HomePagePhotosRepository? = null

        fun getInstance(): HomePagePhotosRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = HomePagePhotosRepository()
                INSTANCE!!
            }
        }
    }

    // Modify the callback to explicitly specify the type
    fun getPhotoUrls(callback: (List<String>) -> Unit) {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val photoUrls = mutableListOf<String>()
                for (childSnapshot in snapshot.children) {
                    // Adjust the path based on your data structure
                    val photoUrl = childSnapshot.child("Url").value as? String
                    photoUrl?.let {
                        photoUrls.add(it)
                    }
                }
                callback(photoUrls)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("HomePagePhotosRepository", "Failed to retrieve photo URLs", error.toException())
                callback(emptyList())
            }
        })
    }
}
