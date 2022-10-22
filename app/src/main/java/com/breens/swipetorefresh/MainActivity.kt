package com.breens.swipetorefresh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breens.swipetorefresh.ui.theme.SwipeToRefreshTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwipeToRefreshTheme {
                RefreshData()
            }
        }
    }

    @Composable
    private fun RefreshData() {
        val viewModel: RefreshDataViewModel = viewModel()
        val isRefreshing by viewModel.isRefreshing.collectAsState()
        val items = remember { mutableStateOf(data) }

        SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefreshing), onRefresh = {
            viewModel.refresh()
            items.value = viewModel.shuffleItems()
        }) {
            LazyColumn(
                contentPadding = PaddingValues(15.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items.value) { item ->
                    CardComponent(data = item)
                }
            }
        }
    }
}