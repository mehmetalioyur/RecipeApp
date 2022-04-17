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

                        viewModel.addItemToListIfExist(strIngredient1,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient2,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient3,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient4,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient5,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient6,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient7,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient8,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient9,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient10,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient11,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient12,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient13,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient14,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient15,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient16,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient17,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient18,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient19,ingredientList)
                        viewModel.addItemToListIfExist(strIngredient20,ingredientList)

                        viewModel.addItemToListIfExist(strMeasure1,measureList)
                        viewModel.addItemToListIfExist(strMeasure2,measureList)
                        viewModel.addItemToListIfExist(strMeasure3,measureList)
                        viewModel.addItemToListIfExist(strMeasure4,measureList)
                        viewModel.addItemToListIfExist(strMeasure5,measureList)
                        viewModel.addItemToListIfExist(strMeasure6,measureList)
                        viewModel.addItemToListIfExist(strMeasure7,measureList)
                        viewModel.addItemToListIfExist(strMeasure8,measureList)
                        viewModel.addItemToListIfExist(strMeasure9,measureList)
                        viewModel.addItemToListIfExist(strMeasure10,measureList)
                        viewModel.addItemToListIfExist(strMeasure11,measureList)
                        viewModel.addItemToListIfExist(strMeasure12,measureList)
                        viewModel.addItemToListIfExist(strMeasure13,measureList)
                        viewModel.addItemToListIfExist(strMeasure14,measureList)
                        viewModel.addItemToListIfExist(strMeasure15,measureList)
                        viewModel.addItemToListIfExist(strMeasure16,measureList)
                        viewModel.addItemToListIfExist(strMeasure17,measureList)
                        viewModel.addItemToListIfExist(strMeasure18,measureList)
                        viewModel.addItemToListIfExist(strMeasure19,measureList)
                        viewModel.addItemToListIfExist(strMeasure20,measureList)

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



