package com.ands.wb5weekweb.fragments.tinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ands.wb5weekweb.adapters.LikedCatsAdapter
import com.ands.wb5weekweb.databinding.FragmentFavouriteCatsBinding
import com.ands.wb5weekweb.viewmodels.tinder.TinderLikedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikedCatsFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteCatsBinding
    private val viewModel: TinderLikedViewModel by viewModels()
    private val likedCatsAdapter = LikedCatsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteCatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserve()
    }

    private fun setupRecyclerView() {
        binding.likedCatsList.apply {
            adapter = likedCatsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObserve() {
        viewModel.likedCats.observe(viewLifecycleOwner) {
            likedCatsAdapter.submitList(it)
        }
    }

}