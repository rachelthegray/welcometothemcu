package com.example.myapplication

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class MarvelsListViewModel(val dataSource: DataSource) : ViewModel() {

    val MarvelsLiveData = dataSource.getMarvelList()

    /* If the name and description are present, create new Marvel and add it to the datasource */
    fun insertMarvel(MarvelName: String?, MarvelDescription: String?) {
        if (MarvelName == null || MarvelDescription == null) {
            return
        }

        val image = dataSource.getRandomMarvelImageAsset()
        val newMarvel = Marvel(
            Random.nextLong(),
            MarvelName,
            image,
            MarvelDescription
        )

        dataSource.addMarvel(newMarvel)
    }
}

class MarvelsListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarvelsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MarvelsListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}