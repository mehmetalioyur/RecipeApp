package com.mehmetalioyur.recipeapp.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mehmetalioyur.recipeapp.R
import com.mehmetalioyur.recipeapp.databinding.FragmentSavedBinding
import com.mehmetalioyur.recipeapp.ui.meallist.MealListRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved) {


    @Inject
    lateinit var recyclerAdapter: MealListRecyclerAdapter

    private val viewModel: SavedViewModel by viewModels()

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val swipeCallBack =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val layoutPosition = viewHolder.layoutPosition
                val selectedMeal = recyclerAdapter.meals[layoutPosition]
                viewModel.deleteRecipe(selectedMeal)
                Toast.makeText(requireContext(), "Recipe Deleted", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.savedRecyclerView.adapter = recyclerAdapter
        binding.savedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter.setOnItemClickListener {
            viewModel.changeSharedPreferencesValue(it)
            val action = SavedFragmentDirections.actionSavedFragmentToDetailsViewPagerFragment()
            findNavController().navigate(action)
        }
        subscribeToObservers()
        goToCategories()

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.savedRecyclerView)
    }

    private fun subscribeToObservers() {
        viewModel.savedMealList.observe(viewLifecycleOwner) {
            recyclerAdapter.meals = it
            isThereMeal(
                binding.emptyScreenLayout, binding.savedRecyclerView, it.size
            )
            println(it.size)
        }
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }

    private fun isThereMeal(viewEmpty: View, viewNotEmpty: View, size: Int) {
        when (size) {
            0 -> {
                viewEmpty.visibility = View.VISIBLE
                viewNotEmpty.visibility = View.GONE
            }
            else -> {
                viewEmpty.visibility = View.GONE
                viewNotEmpty.visibility = View.VISIBLE
            }
        }
    }

    private fun goToCategories() {
        binding.goToCategoriesButton.setOnClickListener {
            val action = SavedFragmentDirections.actionSavedFragmentToOverviewFragment()
            findNavController().navigate(action)
        }
    }
}