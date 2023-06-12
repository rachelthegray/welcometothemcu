package com.example.myapplication

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MarvelDetailViewModel(private val datasource: DataSource) : ViewModel() {

    /* Queries datasource to returns a Marvel that corresponds to an id. */
    fun getMarvelForId(id: Long): Marvel? {
        return datasource.getMarvelForId(id)
    }

    /* Queries datasource to remove a Marvel. */
    //fun removeMarvel(Marvel: Marvel) {
    //    datasource.removeMarvel(Marvel)
    //}

    // ADD NEW FUNCTIONS HERE
    // ********************************************************************

    fun shuffleMarvels() {
        datasource.shuffleMarvels()
    }

    fun snapMarvels() {
        datasource.snapMarvels()
    }

    fun reverseSnap(){
        datasource.reverseSnap()
    }

    fun multiverse(){
        datasource.multiverse()
    }
}

class MarvelDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarvelDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MarvelDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}