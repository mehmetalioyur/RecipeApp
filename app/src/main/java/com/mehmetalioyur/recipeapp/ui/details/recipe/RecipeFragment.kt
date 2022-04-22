package com.mehmetalioyur.recipeapp.ui.details.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.RequestManager
import com.mehmetalioyur.recipeapp.R
import com.mehmetalioyur.recipeapp.databinding.FragmentRecipeBinding
import com.mehmetalioyur.recipeapp.model.meal.Meal
import com.mehmetalioyur.recipeapp.util.Resource
import com.mehmetalioyur.recipeapp.util.ViewStatus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeFragment() : Fragment(R.layout.fragment_recipe) {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var glide: RequestManager

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            viewModel.insertRecipe()
            Toast.makeText(requireContext(), "Recipe Saved", Toast.LENGTH_SHORT).show()
            binding.fab.visibility = View.GONE
        }

        subscribeToObservers()
        isMealExistInDatabase()
    }


    private fun subscribeToObservers() {

        viewModel.mealDetails.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.meals?.get(0)?.apply {
                        applyChangesToViews(this)
                    }

                    with(binding) {
                        ViewStatus().apply {
                            showScreen(scrollView)
                            hideErrorMessage(recipeErrorText)
                            hideProgressBar(recipeProgressBar)
                        }
                    }
                }
                is Resource.Error -> {
                    with(binding) {
                        ViewStatus().apply {
                            hideScreen(scrollView)
                            showErrorMessage(recipeErrorText)
                            hideProgressBar(recipeProgressBar)

                        }
                    }
                }

                is Resource.Loading -> {
                    with(binding) {
                        ViewStatus().apply {
                            hideScreen(scrollView)
                            hideErrorMessage(recipeErrorText)
                            showProgressBar(recipeProgressBar)
                        }
                    }
                }
            }
        }
    }

    private fun isMealExistInDatabase() {
        viewModel.isSavedBefore.observe(viewLifecycleOwner) {
            when (it) {
                true -> binding.fab.visibility = View.GONE
                false -> binding.fab.visibility = View.VISIBLE
            }
        }
    }

    private fun applyChangesToViews(meal: Meal) {

        binding.tvMealTitle.text = meal.strMeal
        binding.tvMealCategory.text = "Category : ${meal.strCategory}"
        binding.tvMealCountry.text = "Nation : ${meal.strArea}"
        val recipeText = meal.strInstructions?.replace("\n", "\n\n")
        binding.tvMealRecipe.text = recipeText
        glide.load(meal.strMealThumb).into(binding.mealImage)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}