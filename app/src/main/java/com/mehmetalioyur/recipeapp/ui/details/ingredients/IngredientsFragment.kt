package com.mehmetalioyur.recipeapp.ui.details.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetalioyur.recipeapp.R
import com.mehmetalioyur.recipeapp.databinding.FragmentIngredientsBinding
import com.mehmetalioyur.recipeapp.util.Resource
import com.mehmetalioyur.recipeapp.util.ViewStatus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IngredientsFragment : Fragment(R.layout.fragment_ingredients) {

    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var ingredientsRecyclerAdapter: IngredientsRecyclerAdapter

    private var ingredientList = mutableListOf<String>()
    private var measureList = mutableListOf<String>()

    private val viewModel: IngredientsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRv()
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.mealDetails.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.meals?.get(0)?.apply {

                        viewModel.isItemExist(strIngredient1,ingredientList)
                        viewModel.isItemExist(strIngredient2,ingredientList)
                        viewModel.isItemExist(strIngredient3,ingredientList)
                        viewModel.isItemExist(strIngredient4,ingredientList)
                        viewModel.isItemExist(strIngredient5,ingredientList)
                        viewModel.isItemExist(strIngredient6,ingredientList)
                        viewModel.isItemExist(strIngredient7,ingredientList)
                        viewModel.isItemExist(strIngredient8,ingredientList)
                        viewModel.isItemExist(strIngredient9,ingredientList)
                        viewModel.isItemExist(strIngredient10,ingredientList)
                        viewModel.isItemExist(strIngredient11,ingredientList)
                        viewModel.isItemExist(strIngredient12,ingredientList)
                        viewModel.isItemExist(strIngredient13,ingredientList)
                        viewModel.isItemExist(strIngredient14,ingredientList)
                        viewModel.isItemExist(strIngredient15,ingredientList)
                        viewModel.isItemExist(strIngredient16,ingredientList)
                        viewModel.isItemExist(strIngredient17,ingredientList)
                        viewModel.isItemExist(strIngredient18,ingredientList)
                        viewModel.isItemExist(strIngredient19,ingredientList)
                        viewModel.isItemExist(strIngredient20,ingredientList)

                        viewModel.isItemExist(strMeasure1,measureList)
                        viewModel.isItemExist(strMeasure2,measureList)
                        viewModel.isItemExist(strMeasure3,measureList)
                        viewModel.isItemExist(strMeasure4,measureList)
                        viewModel.isItemExist(strMeasure5,measureList)
                        viewModel.isItemExist(strMeasure6,measureList)
                        viewModel.isItemExist(strMeasure7,measureList)
                        viewModel.isItemExist(strMeasure8,measureList)
                        viewModel.isItemExist(strMeasure9,measureList)
                        viewModel.isItemExist(strMeasure10,measureList)
                        viewModel.isItemExist(strMeasure11,measureList)
                        viewModel.isItemExist(strMeasure12,measureList)
                        viewModel.isItemExist(strMeasure13,measureList)
                        viewModel.isItemExist(strMeasure14,measureList)
                        viewModel.isItemExist(strMeasure15,measureList)
                        viewModel.isItemExist(strMeasure16,measureList)
                        viewModel.isItemExist(strMeasure17,measureList)
                        viewModel.isItemExist(strMeasure18,measureList)
                        viewModel.isItemExist(strMeasure19,measureList)
                        viewModel.isItemExist(strMeasure20,measureList)

                    }
                    ingredientsRecyclerAdapter.ingredients = ingredientList
                    ingredientsRecyclerAdapter.measures = measureList

                    binding.apply {
                        ViewStatus().apply {
                            showScreen(ingredientsRV)
                            hideErrorMessage(ingredientsErrorTV)
                            hideProgressBar(ingredientsProgressBar)
                        }
                    }
                }
                is Resource.Error -> {
                    binding.apply {
                        ViewStatus().apply {
                            hideScreen(ingredientsRV)
                            showErrorMessage(ingredientsErrorTV)
                            hideProgressBar(ingredientsProgressBar)
                        }
                    }
                }
                is Resource.Loading -> {
                    binding.apply {
                        ViewStatus().apply {
                            hideScreen(ingredientsRV)
                            hideErrorMessage(ingredientsErrorTV)
                            showProgressBar(ingredientsProgressBar)
                        }
                    }
                }
            }
        }
    }

        private fun setupRv() {
        binding.ingredientsRV.apply {
            adapter = ingredientsRecyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}



