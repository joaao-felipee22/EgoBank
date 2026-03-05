package com.egobank.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.egobank.ui.viewmodel.StatementUiState
import com.egobank.ui.viewmodel.StatementViewModel

@Composable
fun StatementScreen(viewModel: StatementViewModel) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is StatementUiState.Loading -> // Mostrar um indicador de carregamento
        is StatementUiState.Success -> // Mostrar a lista de transações
        is StatementUiState.Error -> // Mostrar uma mensagem de erro
    }
}