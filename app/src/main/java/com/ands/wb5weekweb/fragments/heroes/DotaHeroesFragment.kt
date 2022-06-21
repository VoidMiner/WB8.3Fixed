package com.ands.wb5weekweb.fragments.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ands.wb5weekweb.R
import com.ands.wb5weekweb.adapters.DotaHeroesAdapter
import com.ands.wb5weekweb.databinding.FragmentHeroesBinding
import com.ands.wb5weekweb.fragments.heroes.DescriptionFragment.Companion.newInstanceCommonStats
import com.ands.wb5weekweb.model.heroes.CommonHeroesStats
import com.ands.wb5weekweb.utils.Constants
import com.ands.wb5weekweb.viewmodels.heroes.DotaHeroesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DotaHeroesFragment : Fragment() {

    private lateinit var binding: FragmentHeroesBinding
    private val viewModelDota: DotaHeroesViewModel by viewModels()

    private val heroesAdapter = DotaHeroesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        binding.heroesRV.apply {
            adapter = heroesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModelDota.dotaHeroes.observe(viewLifecycleOwner) {
            heroesAdapter.submitList(it)
        }



        heroesAdapter.OnClickListener = {

            val commonHeroesStats = CommonHeroesStats(
                id = it.id.toString(),
                image = Constants.DOTA_BASE_URL + it.img,
                movementSpeed = it.moveSpeed.toString(),
                baseInt = it.baseInt.toString(),
                baseStrength = it.baseStrength.toString(),
                name = it.localizedName
            )

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, newInstanceCommonStats(commonHeroesStats))
                .addToBackStack(null)
                .commit() //fragmentManager

//            val action = DotaHeroesFragmentDirections.actionHeroesFragmentToDescriptionFragment(
//                    commonHeroesStats
//                )
//
//            Navigation.findNavController(binding.root).navigate(action)
        }
    }


}