package com.mehmetalioyur.recipeapp.ui.overview

        import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.mehmetalioyur.recipeapp.R
import com.mehmetalioyur.recipeapp.databinding.FragmentOverviewBinding
import com.mehmetalioyur.recipeapp.util.Resource
import com.mehmetalioyur.recipeapp.util.ViewStatus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OverviewFragment : Fragment(R.layout.fragment_overview) {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomAppBar: BottomAppBar

    @Inject
    lateinit var overviewRecyclerAdapter: OverviewRecyclerAdapter

    @Inject
    lateinit var countriesRecyclerAdapter: CountriesRecyclerAdapter

    private val viewModel: OverviewViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        bottomAppBar = activity!!.findViewById(R.id.bottomAppBar)
        bottomAppBar.visibility = View.VISIBLE

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        subscribeToObservers()
        setupCountryRV()
        setupRV()
        viewModel.getRandomMeal()
    }

    private fun subscribeToObservers() {
        viewModel.categories.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { categories ->
                        val category = categories.categories
                        ViewStatus().showScreen(binding.overviewReyclerView)
                        ViewStatus().hideErrorMessage(binding.overviewErrorTV)
                        ViewStatus().hideProgressBar(binding.overviewProgressBar)
                        overviewRecyclerAdapter.categories = category
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                    ViewStatus().hideScreen(binding.overviewReyclerView)
                    ViewStatus().showErrorMessage(binding.overviewErrorTV)
                    ViewStatus().hideProgressBar(binding.overviewProgressBar)
                }
                is Resource.Loading -> {
                    ViewStatus().hideScreen(binding.overviewReyclerView)
                    ViewStatus().hideErrorMessage(binding.overviewErrorTV)
                    ViewStatus().showProgressBar(binding.overviewProgressBar)
                }
            }
        }
        viewModel.randomMeal.observe(viewLifecycleOwner){mealModel->
            when (mealModel) {
                is Resource.Success -> {
                    binding.randomMealButton.setOnClickListener {

                        mealModel.data?.meals?.get(0)?.idMeal?.let { it1 ->
                            viewModel.changeSharedPreferencesValue(
                                it1
                            )
                        }

                        val action = OverviewFragmentDirections.actionOverviewFragmentToDetailsViewPagerFragment()
                        findNavController().navigate(action)
                    }

                    }
                is Resource.Error -> {}
                is Resource.Loading -> {}
            }
        }
    }

    private fun setupRV() {
        binding.overviewReyclerView.apply {
            adapter = overviewRecyclerAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            overviewRecyclerAdapter.setOnItemClickListener {
                val action = OverviewFragmentDirections.actionOverviewFragmentToMealListFragment(
                    it,
                    CATEGORY_CHOICED
                ) //enum
                findNavController().navigate(action)
            }
        }
    }

    private fun setupCountryRV() {
        binding.countryRecyclerView.apply {
            adapter = countriesRecyclerAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            countriesRecyclerAdapter.countries = viewModel.countryList

            countriesRecyclerAdapter.setOnItemClickListener {
                val action = OverviewFragmentDirections.actionOverviewFragmentToMealListFragment(
                    it, COUNTRY_CHOICED
                ) //enum
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bottomAppBar.visibility = View.GONE
        _binding = null
    }
companion object {
    private const val CATEGORY_CHOICED = 0
    private const val COUNTRY_CHOICED = 1
}


}
