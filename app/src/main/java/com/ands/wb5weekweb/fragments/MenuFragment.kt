package com.ands.wb5weekweb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ands.wb5weekweb.R
import com.ands.wb5weekweb.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.openDotaList.setOnClickListener() {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_menuFragment_to_heroesFragment)
        }

        binding.openSuperHeroList.setOnClickListener() {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_menuFragment_to_superHeroesFragment)
        }

        binding.openTinder.setOnClickListener() {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_menuFragment_to_tinderFragment)
        }
        binding.aboutBtn.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_menuFragment_to_aboutFragment)
        }
    }

}