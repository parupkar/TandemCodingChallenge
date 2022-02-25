package com.tandem.codingchallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tandem.codingchallenge.data.MyApi

class CommunitiesViewModel(
    private val api: MyApi
) : ViewModel() {
    val passengers =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2), pagingSourceFactory = {
            CommunitiesDataSource(api)
        }).flow.cachedIn(viewModelScope)
}
