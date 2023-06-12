package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MarvelsAdapter(private val onClick: (Marvel) -> Unit) :
    ListAdapter<Marvel, MarvelsAdapter.MarvelViewHolder>(MarvelDiffCallback) {

    /* ViewHolder for Marvel, takes in the inflated view and the onClick behavior. */
    class MarvelViewHolder(itemView: View, val onClick: (Marvel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
       // private val MarvelTextView: TextView = itemView.findViewById(R.id.marvel_text)
        private val MarvelImageView: ImageView = itemView.findViewById(R.id.marvel_image)
        private var currentMarvel: Marvel? = null

        init {
            itemView.setOnClickListener {
                currentMarvel?.let {
                    onClick(it)
                }
            }
        }

        /* Bind Marvel name and image. */
        fun bind(Marvel: Marvel) {
            currentMarvel = Marvel

            //MarvelTextView.text = Marvel.name
            if (Marvel.image != null) {
                MarvelImageView.setImageResource(Marvel.image!!)
            } else {
                MarvelImageView.setImageResource(R.drawable.loki)
            }
        }
    }

    /* Creates and inflates view and return MarvelViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.marvel_item, parent, false)
        return MarvelViewHolder(view, onClick)
    }

    /* Gets current Marvel and uses it to bind view. */
    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {
        val Marvel = getItem(position)
        holder.bind(Marvel)

    }
}

object MarvelDiffCallback : DiffUtil.ItemCallback<Marvel>() {
    override fun areItemsTheSame(oldItem: Marvel, newItem: Marvel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Marvel, newItem: Marvel): Boolean {
        return oldItem.id == newItem.id
    }
}