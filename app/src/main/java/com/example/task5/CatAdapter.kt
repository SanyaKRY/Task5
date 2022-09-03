package com.example.task5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task5.data.Cat
import com.example.task5.databinding.RecyclerviewItemBinding

class CatAdapter : RecyclerView.Adapter<CatViewHolder>(){

    private val catItems = mutableListOf<Cat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemBinding.inflate(layoutInflater, parent, false)
        val catViewHolder = CatViewHolder(binding)
        return catViewHolder
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val listItem = catItems[position]
        holder.bind(listItem.id, listItem.imageUrl)
    }

    override fun getItemCount(): Int {
        return catItems.size
    }

    fun addItems(newItems: List<Cat>) {
        catItems.addAll(newItems)
        notifyDataSetChanged()
    }
}

class CatClickListener(val clickListener: (cat: Cat) -> Unit) {
    fun onClick(cat: Cat) = clickListener(cat)
}
