package com.example.cosmicexplorar.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cosmicexplorar.repository.EarthRepository

class EarthViewModelFactory(private val repository: EarthRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass : Class<T>):T{
        return EarthViewModel(repository) as T
    }
}