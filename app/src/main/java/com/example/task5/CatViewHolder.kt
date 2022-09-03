package com.example.task5

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task5.databinding.RecyclerviewItemBinding

class CatViewHolder(binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val id = binding.catItemTextView
    val image = binding.catItemImageView

    fun bind(catId: String?, catImageUrl: String?) {
        id.text = catId
        Glide.with(image.context).load(catImageUrl).into(image)
    }
}