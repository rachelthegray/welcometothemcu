package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

/* A list always displaying one element: the number of Marvels. */

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var MarvelCount: Int = 0

    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val MarvelNumberTextView: TextView = itemView.findViewById(R.id.marvel_number_text)
        //private val MarvelNumberTextView: TextView = TextView()

        fun bind(MarvelCount: Int) {
            MarvelNumberTextView.text = MarvelCount.toString()
        }
    }

    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)
        return HeaderViewHolder(view)
    }

    /* Binds number of Marvels to the header. */
    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(MarvelCount)
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return 1
    }

    /* Updates header to display number of Marvels when a Marvel is added or subtracted. */
    fun updateMarvelCount(updatedMarvelCount: Int) {
        MarvelCount = updatedMarvelCount
        notifyDataSetChanged()
    }
}