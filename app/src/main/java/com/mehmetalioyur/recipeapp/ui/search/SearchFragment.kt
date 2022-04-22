package com.mehmetalioyur.recipeapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetalioyur.recipeapp.R
import com.mehmetalioyur.recipeapp.databinding.FragmentSearchBinding
import com.mehmetalioyur.recipeapp.ui.meallist.MealListRecyclerAdapter
import com.mehmetalioyur.recipeapp.util.Resource
import com.mehmetalioyur.recipeapp.util.ViewStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()


    @Inject
    lateinit var mealListRecyclerAdapter: MealListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchMeal()
        setupRV()
        subscribeToObservers()

    }


    private fun searchMeal() {
        var job: Job? = null

        binding.searchMealTextField.editText?.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.searchForMeal(it.toString())
                    }
                }
            }
        }
    }

    private fun setupRV() {
        binding.searchRecyclerView.apply {
            adapter = mealListRecyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())

            mealListRecyclerAdapter.setOnItemClickListener {
                viewModel.changeSharedPreferencesValue(it)
                val action =
                    SearchFragmentDirections.actionSearchFragmentToDetailsViewPagerFragment()
                findNavController().navigate(action)
            }
        }
    }


    private fun subscribeToObservers() {
        viewModel.searchedMealList.observe(viewLifecycleOwner) {

            when (it) {
                is Resource.Success -> {
                    it.data?.let { mealModel ->
                        val meals = mealModel.meals
                        if (meals.isNullOrEmpty()) {
                            ViewStatus().apply {
                                with(binding) {
                                    hideScreen(searchRecyclerView)
                                    hideErrorMessage(searchErrorMessage)
                                    showMealExist(
                                        binding.tvRecipeIsntExist,
                                        binding.animationRecipeIsntExist
                                    )
                                    hideProgressBar(searchProgressBar)
                                }
                            }
                        } else {
                            ViewStatus().apply {
                                with(binding) {
                                    showScreen(searchRecyclerView)
                                    hideErrorMessage(searchErrorMessage)
                                    hideProgressBar(searchProgressBar)
                                    hideMealExist(
                                        binding.tvRecipeIsntExist,
                                        binding.animationRecipeIsntExist
                                    )
                                }
                            }
                            mealListRecyclerAdapter.meals = meals
                        }

                    }


                }


                is Resource.Error -> {
                    ViewStatus().apply {
                        with(binding) {
                            hideScreen(searchRecyclerView)
                            showErrorMessage(searchErrorMessage)
                            hideProgressBar(searchProgressBar)
                            hideMealExist(
                                binding.tvRecipeIsntExist,
                                binding.animationRecipeIsntExist
                            )


                        }
                    }

                }

                is Resource.Loading -> {
                    ViewStatus().apply {
                        with(binding) {
                            hideScreen(searchRecyclerView)
                            hideErrorMessage(searchErrorMessage)
                            showProgressBar(searchProgressBar)
                            hideMealExist(
                                binding.tvRecipeIsntExist,
                                binding.animationRecipeIsntExist
                            )

                        }
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}