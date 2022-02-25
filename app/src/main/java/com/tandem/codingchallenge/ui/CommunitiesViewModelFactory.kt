package com.tandem.codingchallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tandem.codingchallenge.data.MyApi

class CommunitiesViewModelFactory(
    private val api: MyApi
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CommunitiesViewModel(api) as T
    }
}