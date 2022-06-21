package com.ands.wb5weekweb.utils

import com.ands.wb5weekweb.fragments.heroes.DescriptionFragment
import com.ands.wb5weekweb.model.heroes.CommonHeroesStats
import com.github.terrakok.cicerone.androidx.FragmentScreen


object Screens {    //cicerone

    fun descriptionScreen(common: CommonHeroesStats) =
        FragmentScreen { DescriptionFragment.newInstanceCommonStats(commonStats = common) }

}