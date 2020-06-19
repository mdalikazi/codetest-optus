package com.alikazi.codetest.optus.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alikazi.codetest.optus.network.Repository

class MyViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        /*if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(SearchHistoryViewModel::class.java)) {
            return SearchHistoryViewModel(repository) as T
        }*/

        throw ClassNotFoundException("Invalid ViewModel was passed to MyViewModelFactory!")
    }
}