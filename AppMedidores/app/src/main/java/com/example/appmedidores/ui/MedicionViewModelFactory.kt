package com.example.appmedidores.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appmedidores.database.MedicionDao

class MedicionViewModelFactory(private val medicionDao: MedicionDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MedicionViewModel(medicionDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}