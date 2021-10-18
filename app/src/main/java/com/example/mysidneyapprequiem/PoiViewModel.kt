package com.example.mysidneyapprequiem

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PoiViewModel : ViewModel() {

    val selectedPoi = MutableLiveData<Poi>()

    init {
        Log.d(TAG, "ViewModel created")
        selectedPoi.value = null
    }

    fun getPoi(): LiveData<Poi> = selectedPoi

    fun setPoi(poi: Poi) {
        selectedPoi.value = poi
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared")
    }

    companion object {
        private const val TAG = "Poi ViewModel"
    }

}