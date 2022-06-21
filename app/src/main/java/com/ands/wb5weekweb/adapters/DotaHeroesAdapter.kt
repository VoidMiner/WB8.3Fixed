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
import com.ands.wb5weekweb.model.heroes.DotaHeroesResponse
import com.ands.wb5weekweb.utils.Constants


class DotaHeroesAdapter :
    ListAdapter<DotaHeroesResponse, DotaHeroesAdapter.HeroesViewHolder>(HeroesDiffUtil()) {

    var OnClickListener: ((DotaHeroesResponse) -> Unit)? = null

    class HeroesViewHolder(private val binding: HeroItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: DotaHeroesResponse) {
            Log.e("TAG", hero.toString())
            binding.apply {
                name.text = hero.localizedName
                icon.load(Constants.DOTA_BASE_URL + hero.icon) {
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    class HeroesDiffUtil : DiffUtil.ItemCallback<DotaHeroesResponse>() {
        override fun areItemsTheSame(
            oldItem: DotaHeroesResponse,
            newItem: DotaHeroesResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DotaHeroesResponse,
            newItem: DotaHeroesResponse
        ): Boolean {
            return oldItem == newItem
        }

    }//было бы правильнее это разбить на классы, вынести viewholder и diffutil отсюда,
    // но для удобства оставил все тут, потому что маленькие проект

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(
            HeroItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener() {
            OnClickListener?.invoke(getItem(position))
        }
    }

}