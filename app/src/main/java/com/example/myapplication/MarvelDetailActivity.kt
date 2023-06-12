package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MarvelDetailActivity : AppCompatActivity() {

    private val MarvelDetailViewModel by viewModels<MarvelDetailViewModel> {
        MarvelDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marvel_detail_activity)

        var currentMarvelId: Long? = null

        /* Connect variables to UI elements. */
        val MarvelName: TextView = findViewById(R.id.marvel_detail_name)
        val MarvelImage: ImageView = findViewById(R.id.marvel_detail_image)
        val MarvelDescription: TextView = findViewById(R.id.marvel_detail_description)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentMarvelId = bundle.getLong(Marvel_ID)
        }

        /* If currentMarvelId is not null, get corresponding Marvel and set name, image and
        description */
        currentMarvelId?.let {
            val currentMarvel = MarvelDetailViewModel.getMarvelForId(it)
            MarvelName.text = currentMarvel?.name
            if (currentMarvel?.image == null) {
                MarvelImage.setImageResource(R.drawable.marvel)
            } else {
                MarvelImage.setImageResource(currentMarvel.image!!)
            }
            MarvelDescription.text = currentMarvel?.description

            MarvelImage.setOnClickListener{
                if (currentMarvel?.name == "Loki"){
                    MarvelDetailViewModel.shuffleMarvels()
                } else if (currentMarvel?.name == "Thanos"){
                    MarvelDetailViewModel.snapMarvels()
                } else if (currentMarvel?.name == "Tony Stark"){
                    MarvelDetailViewModel.reverseSnap()
                } else if (currentMarvel?.name == "Doctor Strange"){
                    MarvelDetailViewModel.multiverse()
                }

                finish()
            }
        }
    }
}
