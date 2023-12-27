package com.example.appwijnhuisronny

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appwijnhuisronny.Repositories.HomePagePhotosRepository

class HomepageFragmentViewModel : ViewModel() {

    private val whiteWinesRepository = HomePagePhotosRepository.getInstance()

    val photoUrlsLiveData: MutableLiveData<List<String>> = MutableLiveData()

    fun loadPhotoUrls() {
        whiteWinesRepository.getPhotoUrls { photoUrls ->
            photoUrlsLiveData.postValue(photoUrls)
        }
    }
}