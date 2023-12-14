package com.D121211074.makeup.ui.activities.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.D121211074.makeup.data.models.Product
import com.D121211074.makeup.data.repository.MakeupRepository
import com.D121211074.makeup.MyApplication
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val product: List<Product>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val makeupRepository: MakeupRepository): ViewModel() {

    // initial state
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getMakeup() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = makeupRepository.getMakeup()
            mainUiState = MainUiState.Success(result.results.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    init {
        getMakeup()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val makeupRepository = application.container.makeupRepository
                MainViewModel(makeupRepository)
            }
        }
    }
}