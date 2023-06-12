package com.example.myapplication

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/* Handles operations on MarvelsLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialMarvelList = MarvelList(resources)
    private val MarvelsLiveData = MutableLiveData(initialMarvelList)

    // DO NOT REMOVE
    // ********************************************************************

    /* Returns Marvel given an ID. */
    fun getMarvelForId(id: Long): Marvel? {
        MarvelsLiveData.value?.let { Marvels ->
            return Marvels.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getMarvelList(): LiveData<List<Marvel>> {
        return MarvelsLiveData
    }

    /* Returns a random Marvel asset for Marvels that are added. */
    fun getRandomMarvelImageAsset(): Int? {
        val randomNumber = (initialMarvelList.indices).random()
        return initialMarvelList[randomNumber].image
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }

    /* Adds Marvel to liveData and posts value. */
    fun addMarvel(Marvel: Marvel) {
        val currentList = MarvelsLiveData.value
        if (currentList == null) {
            MarvelsLiveData.postValue(listOf(Marvel))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, Marvel)
            MarvelsLiveData.postValue(updatedList)
        }
    }

    // MY HELPER FUNCTIONS
    // ********************************************************************

    fun getCurrentMarvels(): MutableList<Marvel>? {
        val currentMarvels = MarvelsLiveData.value
        return currentMarvels?.toMutableList()
    }

    fun getOriginalMarvels(): MutableList<Marvel>? {
        val originalMarvels = initialMarvelList
        return originalMarvels?.toMutableList()
    }

    fun updateMarvels(marvels: MutableList<Marvel>?){
        MarvelsLiveData.postValue(marvels)
    }

    fun getNRandomMarvels(marvels: MutableList<Marvel>?, n: Int): MutableList<Marvel>?{
        return marvels?.asSequence()?.shuffled()?.take(n)?.toMutableList()
    }

    fun getMarvel(marvels: MutableList<Marvel>?, name: String): Marvel?{
        return marvels?.find { it.name == name }
    }

    fun removeMarvel(name: String){
        val marvels = getCurrentMarvels()
        marvels?.let { getMarvel(it, name)?.let { marvels.remove(it) } }
        marvels?.let { updateMarvels(it) }
    }

    fun getMarvelImage(name: String, multiverse: Boolean): Int {
        if (multiverse){
            if(name == "Loki")
                return R.drawable.loki_2
            else if (name == "Tony Stark")
                return R.drawable.tony_stark_2
            else if (name == "Thanos")
                return R.drawable.thanos_2
            else if (name == "Doctor Strange")
                return R.drawable.doctor_strange_2
            else if (name == "Scarlet Witch")
                return R.drawable.scarlet_witch_2
            else if (name == "Hawkeye")
                return R.drawable.hawkeye_2
            else if (name == "Hulk")
                return R.drawable.hulk_2
            else if (name == "Black Widow")
                return R.drawable.black_widow_2
            else if (name == "Captain America")
                return R.drawable.captain_america_2
            else if (name == "Thor")
                return R.drawable.thor_2
            else
                return R.drawable.marvel
        }
        else {
            if(name == "Loki")
                return R.drawable.loki
            else if (name == "Tony Stark")
                return R.drawable.tony_stark
            else if (name == "Thanos")
                return R.drawable.thanos
            else if (name == "Doctor Strange")
                return R.drawable.doctor_strange
            else if (name == "Scarlet Witch")
                return R.drawable.scarlet_witch
            else if (name == "Hawkeye")
                return R.drawable.hawkeye
            else if (name == "Hulk")
                return R.drawable.hulk
            else if (name == "Black Widow")
                return R.drawable.black_widow
            else if (name == "Captain America")
                return R.drawable.captain_america
            else if (name == "Thor")
                return R.drawable.thor
            else
                return R.drawable.marvel
        }
    }

    // ADD NEW FUNCTIONS HERE
    // ********************************************************************

    fun shuffleMarvels() {
        val currentMarvels = getCurrentMarvels()
        currentMarvels?.shuffle()
        updateMarvels(currentMarvels)
    }

    fun snapMarvels(){
        val currentMarvels = getCurrentMarvels()
        val tony = getMarvel(currentMarvels, "Tony Stark")
        currentMarvels?.remove(tony)
        val n: Int? = currentMarvels?.size?.div(2)
        val newMarvels = n?.let { getNRandomMarvels(currentMarvels, it) }
        if (tony != null) {
            newMarvels?.add(tony)
        }
        updateMarvels(newMarvels)
    }

    fun multiverse(){
        val current = getCurrentMarvels()
        current?.forEach({
            it.image = getMarvelImage(it.name, true)
        })
        updateMarvels(current)

    }

    fun reverseSnap(){
        val originalMarvels = getOriginalMarvels()
        updateMarvels(originalMarvels)
    }
}