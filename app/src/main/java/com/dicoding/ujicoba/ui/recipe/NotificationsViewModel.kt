package com.dicoding.ujicoba.ui.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    // LiveData to hold the lamp state
    private val _isLampOn = MutableLiveData(false)
    val isLampOn: LiveData<Boolean> get() = _isLampOn

    // Function to toggle the lamp state
    fun toggleLamp() {
        _isLampOn.value = _isLampOn.value?.not()
    }
}