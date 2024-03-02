package com.example.cosmicexplorar.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cosmicexplorar.apiClasses.marsPhotos.marsPhotos
import com.example.cosmicexplorar.repository.MarsPhotosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarsPhotosViewModel(val repository: MarsPhotosRepository) : ViewModel() {
    val marsData : LiveData<marsPhotos>
        get() = repository.marsPhotosData

    fun getData(date : String){

        CoroutineScope(Dispatchers.IO).launch {
            repository.getData(date)

        }
    }
}


class MarsViewModelFactory(private val repository: MarsPhotosRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass : Class<T>):T{
        return MarsPhotosViewModel(repository) as T
    }
}
