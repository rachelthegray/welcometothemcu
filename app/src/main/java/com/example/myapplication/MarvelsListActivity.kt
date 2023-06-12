package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

const val Marvel_ID = "Marvel id"

class MarvelsListActivity : AppCompatActivity() {
    private val newMarvelActivityRequestCode = 1
    private val MarvelsListViewModel by viewModels<MarvelsListViewModel> {
        MarvelsListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Instantiates headerAdapter and MarvelsAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val MarvelsAdapter = MarvelsAdapter { Marvel -> adapterOnClick(Marvel) }
        val concatAdapter = ConcatAdapter(headerAdapter, MarvelsAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        MarvelsListViewModel.MarvelsLiveData.observe(this, {
            it?.let {
                MarvelsAdapter.submitList(it as MutableList<Marvel>)
                headerAdapter.updateMarvelCount(it.size)
            }
        })
    }

    /* Opens MarvelDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(Marvel: Marvel) {
        val intent = Intent(this, MarvelDetailActivity()::class.java)
        intent.putExtra(Marvel_ID, Marvel.id)
        startActivity(intent)
    }

    /* Adds Marvel to MarvelList when FAB is clicked. */
    private fun fabOnClick() {
        val intent = Intent(this, MainActivity::class.java)
        startActivityForResult(intent, newMarvelActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts Marvel into viewModel. */
        if (requestCode == newMarvelActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val MarvelName = data.getStringExtra(Marvel_NAME)
                val MarvelDescription = data.getStringExtra(Marvel_DESCRIPTION)

                MarvelsListViewModel.insertMarvel(MarvelName, MarvelDescription)
            }
        }
    }
}