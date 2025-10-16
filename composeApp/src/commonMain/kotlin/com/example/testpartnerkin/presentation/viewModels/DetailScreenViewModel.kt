package com.example.testpartnerkin.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testpartnerkin.domain.`interface`.Repository
import com.example.testpartnerkin.domain.models.DetailScreenData
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    private val repository: Repository
) : ViewModel() {
    private var _detailScreenState = MutableStateFlow(DetailScreenData())
    val detailScreenState = _detailScreenState.asStateFlow()

    init {
        viewModelScope.launch {
            _detailScreenState.update {
                _detailScreenState.value.copy(
                    isLoading = true
                )
            }
            viewModelScope.launch {
                try {
                    val response = repository.getDetailsDto()
                    _detailScreenState.update {
                        _detailScreenState.value.copy(
                            isLoading = false,
                            detailsData = response.data
                        )
                    }
                } catch (e: Exception) {
                    coroutineContext.ensureActive()
                    _detailScreenState.update {
                        _detailScreenState.value.copy(
                            isLoading = false,
                            error = e.message ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}