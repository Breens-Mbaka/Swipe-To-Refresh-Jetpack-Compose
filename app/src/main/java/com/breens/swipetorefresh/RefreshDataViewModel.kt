package com.breens.swipetorefresh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RefreshDataViewModel: ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            delay(1000)
            _isRefreshing.emit(false)
        }
    }

    fun shuffleItems(): List<Data> {
        return data.shuffled()
    }
}

data class Data(
    val position: Int,
    val details: String
)

val data = listOf(
    Data(1, "Number 1"),
    Data(2, "Number 2"),
    Data(3, "Number 3"),
    Data(4, "Number 4"),
    Data(5, "Number 5"),
    Data(6, "Number 6"),
    Data(7, "Number 7"),
    Data(8, "Number 8"),
    Data(9, "Number 9"),
    Data(10, "Number 10"),
    Data(11, "Number 11"),
    Data(12, "Number 12"),
    Data(13, "Number 13"),
    Data(14, "Number 14"),
    Data(15, "Number 15"),
    Data(16, "Number 16"),
    Data(17, "Number 17"),
    Data(18, "Number 18"),
    Data(19, "Number 19"),
    Data(20, "Number 20")
)