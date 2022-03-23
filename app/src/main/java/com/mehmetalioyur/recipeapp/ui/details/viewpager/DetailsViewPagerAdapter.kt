package com.mehmetalioyur.recipeapp.ui.details.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mehmetalioyur.recipeapp.ui.details.ingredients.IngredientsFragment
import com.mehmetalioyur.recipeapp.ui.details.recipe.RecipeFragment


const val RECIPE_PAGE_INDEX = 0
const val INGREDIENTS_PAGE_INDEX = 1
const val YOUTUBE_PAGE_INDEX = 2


class DetailsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        RECIPE_PAGE_INDEX to { RecipeFragment() },
        INGREDIENTS_PAGE_INDEX to { IngredientsFragment() }
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment =
        tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
}