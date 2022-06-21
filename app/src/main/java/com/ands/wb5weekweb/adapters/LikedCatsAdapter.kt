package com.ands.wb5weekweb.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ands.wb5weekweb.databinding.LikedCatItemBinding
import com.ands.wb5weekweb.model.tinder.Cat
import com.ands.wb5weekweb.model.tinder.LikedCats


class LikedCatsAdapter : ListAdapter<LikedCats, LikedCatsAdapter.LikedCatsViewHolder>(LikedCatsDiffUtil()) {

    class LikedCatsViewHolder(private val binding: LikedCatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: LikedCats) {
            Log.e("TAG", cat.toString())
            binding.apply {
                val url = "https://cdn2.thecatapi.com/images/" + cat.image_id + ".jpg"
                id.text = cat.id
                icon.load(url) {
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    class LikedCatsDiffUtil : DiffUtil.ItemCallback<LikedCats>() {
        override fun areItemsTheSame(oldItem: LikedCats, newItem: LikedCats): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LikedCats, newItem: LikedCats): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedCatsViewHolder {
        return LikedCatsViewHolder(
            LikedCatItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holderSuper: LikedCatsViewHolder, position: Int) {
        holderSuper.bind(getItem(position))
    }

}