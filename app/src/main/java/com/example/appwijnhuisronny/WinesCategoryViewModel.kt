package com.example.appwijnhuisronny

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appwijnhuisronny.Repositories.WinesCategoryPhotosRepository

class WinesCategoryViewModel : ViewModel() {

    private val winesCategoryRepository = WinesCategoryPhotosRepository.getInstance()

    private val _photoUrlsLiveData: MutableLiveData<List<String>> = MutableLiveData()
    val photoUrlsLiveData: LiveData<List<String>> = _photoUrlsLiveData

    fun loadPhotoUrls() {
        winesCategoryRepository.getPhotoUrls { photoUrls ->
            _photoUrlsLiveData.postValue(photoUrls)
        }
    }
}
