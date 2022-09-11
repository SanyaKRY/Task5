package com.example.task5.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task5.data.Cat
import com.example.task5.databinding.RecyclerviewItemBinding

class CatPagingAdapter : PagingDataAdapter<Cat, CatPagingViewHolder>(CatDiffItemCallback) {

    override fun onBindViewHolder(holder: CatPagingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatPagingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemBinding.inflate(layoutInflater, parent, false)
        val catPagingViewHolder = CatPagingViewHolder(binding)
        return catPagingViewHolder
    }
}

class CatPagingViewHolder(binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    val id = binding.catItemTextView
    val image = binding.catItemImageView

    fun bind(cat: Cat?) {
        id.text = cat?.id
        Glide.with(image.context).load(cat?.imageUrl).into(image)
    }
}

private object CatDiffItemCallback : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.id == newItem.id && oldItem.imageUrl == newItem.imageUrl
    }
}
