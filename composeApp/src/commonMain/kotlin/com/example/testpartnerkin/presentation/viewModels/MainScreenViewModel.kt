package com.example.testpartnerkin.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testpartnerkin.domain.`interface`.Repository
import com.example.testpartnerkin.domain.models.MainScreenData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repository: Repository
) : ViewModel() {
    private var _mainScreenData = MutableStateFlow(MainScreenData())
    val mainScreenData = _mainScreenData.asStateFlow()

    init {
        _mainScreenData.update {
            _mainScreenData.value.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            try {
                val response = repository.getResponseDto()
                _mainScreenData.update {
                    _mainScreenData.value.copy(
                        isLoading = false,
                        confList = response
                    )
                }
            } catch (e: Exception) {
                _mainScreenData.update {
                    _mainScreenData.value.copy(
                        isLoading = false,
                        error = e.message ?: "An error occurred"
                    )
                }
            }
        }
    }
}