package com.mehmetalioyur.recipeapp.ui.details.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mehmetalioyur.recipeapp.R
import com.mehmetalioyur.recipeapp.databinding.FragmentDetailsViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsViewPagerFragment : Fragment(R.layout.fragment_details_view_pager) {

    private var _binding: FragmentDetailsViewPagerBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsViewPagerBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = binding.tabLayoutDetails
        val viewPager = binding.viewPagerDetails
        viewPager.adapter = DetailsViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            RECIPE_PAGE_INDEX -> getString(R.string.recipe_title)
            INGREDIENTS_PAGE_INDEX -> getString(R.string.ingredients_title)
            else -> null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}