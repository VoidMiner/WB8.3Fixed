package com.ands.wb5weekweb.fragments.heroes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ands.wb5weekweb.adapters.SuperHeroesAdapter
import com.ands.wb5weekweb.databinding.FragmentSuperHeroesBinding
import com.ands.wb5weekweb.di.App
import com.ands.wb5weekweb.model.heroes.CommonHeroesStats
import com.ands.wb5weekweb.utils.Screens
import com.ands.wb5weekweb.viewmodels.heroes.SuperHeroesViewModel
import com.github.terrakok.cicerone.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperHeroesFragment : Fragment() {

    private lateinit var binding: FragmentSuperHeroesBinding
    private val viewModel: SuperHeroesViewModel by viewModels()

    private val superHeroesAdapter = SuperHeroesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuperHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObserver()

    }

    private fun setupRecyclerView() {

        binding.superHeroesList.apply {
            adapter = superHeroesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.superHeroes.observe(viewLifecycleOwner) {
            superHeroesAdapter.submitList(it)
        }

        superHeroesAdapter.OnClickListener = {

            val commonHeroesStats = CommonHeroesStats(
                id = it.id,
                image = it.images.lg,
                movementSpeed = it.powerstats.speed,
                baseInt = it.powerstats.intelligence,
                baseStrength = it.powerstats.strength,
                name = it.name
            )

            App.INSTANCE.router.navigateTo(Screens.descriptionScreen(commonHeroesStats))//cicerone
            Log.e("SUPERHEROESFRAGMENT", "Cicerone navigation is working")

//            val action = SuperHeroesFragmentDirections.actionSuperHeroesFragmentToDescriptionFragment(commonHeroesStats)
//
//            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun setupObserver() {
        viewModel.superHeroes.observe(viewLifecycleOwner) {
            superHeroesAdapter.submitList(it)
        }
    }


}