package com.egobank.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egobank.domain.model.Transaction
import com.egobank.usecase.GetStatementUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class StatementUiState {
    object Loading : StatementUiState()
    data class Success(val list: List<Transaction>) : StatementUiState()
    data class Error(val message: String) : StatementUiState()
}

class StatementViewModel(private val useCase: GetStatementUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<StatementUiState>(StatementUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadStatement() {
        viewModelScope.launch {
            useCase().onSuccess { list ->
                _uiState.value = StatementUiState.Success(list)
            }.onFailure {
                _uiState.value = StatementUiState.Error("Falha ao carregar")
            }
        }
    }
}