package com.ands.wb5weekweb.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ands.wb5weekweb.databinding.HeroItemBinding
import com.ands.wb5weekweb.model.heroes.SuperHeroesResponse
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


class SuperHeroesAdapter :
    ListAdapter<SuperHeroesResponse, SuperHeroesAdapter.SuperHeroesViewHolder>(HeroesDiffUtil()) {

    var OnClickListener: ((SuperHeroesResponse) -> Unit)? = null

    class SuperHeroesViewHolder(private val binding: HeroItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: SuperHeroesResponse) {
            Log.e("TAG", hero.toString())
            binding.apply {
                name.text = hero.name
                Picasso.get()
                    .load(hero.images.lg)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .resize(50, 50)
                    .centerCrop()
                    .into(icon)
            }
        }
    }

    class HeroesDiffUtil : DiffUtil.ItemCallback<SuperHeroesResponse>() {
        override fun areItemsTheSame(
            oldItem: SuperHeroesResponse,
            newItem: SuperHeroesResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SuperHeroesResponse,
            newItem: SuperHeroesResponse
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesViewHolder {
        return SuperHeroesViewHolder(
            HeroItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holderSuper: SuperHeroesViewHolder, position: Int) {
        holderSuper.bind(getItem(position))
        holderSuper.itemView.setOnClickListener() {
            OnClickListener?.invoke(getItem(position))
        }
    }

}