package com.mehmetalioyur.recipeapp.ui.meallist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetalioyur.recipeapp.R
import com.mehmetalioyur.recipeapp.databinding.FragmentMealListBinding
import com.mehmetalioyur.recipeapp.util.Resource
import com.mehmetalioyur.recipeapp.util.ViewStatus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MealListFragment : Fragment(R.layout.fragment_meal_list) {

    private var _binding: FragmentMealListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var mealListRecyclerAdapter: MealListRecyclerAdapter

    private val viewModel: MealListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeToObservers()
        setupRV()
    }

    private fun subscribeToObservers() {
        viewModel.mealList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { mealList ->
                        val meals = mealList.meals
                        ViewStatus().apply {
                            with(binding) {
                                showScreen(mealListRv)
                                hideProgressBar(mealListProgressBar)
                                hideErrorMessage(mealListErrorText)
                            }
                        }
                        mealListRecyclerAdapter.meals = meals
                    }
                }
                is Resource.Error -> {
                    ViewStatus().apply {
                        with(binding) {
                            hideScreen(mealListRv)
                            hideProgressBar(mealListProgressBar)
                            showErrorMessage(mealListErrorText)
                        }
                    }
                }
                is Resource.Loading -> {
                    ViewStatus().apply {
                        with(binding) {
                            hideScreen(mealListRv)
                            showProgressBar(mealListProgressBar)
                            hideErrorMessage(mealListErrorText)
                        }
                    }
                }
            }
        }
    }

    private fun setupRV() {
        with(binding.mealListRv) {
            adapter = mealListRecyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())

            mealListRecyclerAdapter.setOnItemClickListener {
                viewModel.changeSharedPreferencesValue(it)
                val action =
                    MealListFragmentDirections.actionMealListFragmentToDetailsViewPagerFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}