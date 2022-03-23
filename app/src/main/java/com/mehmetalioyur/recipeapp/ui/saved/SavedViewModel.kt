package com.mehmetalioyur.recipeapp.ui.saved

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetalioyur.recipeapp.model.meal.Meal
import com.mehmetalioyur.recipeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val savedMealList: LiveData<List<Meal>>
        get() = repository.getRecipes()


    fun deleteRecipe(meal: Meal) = viewModelScope.launch {
        repository.deleteRecipe(meal)
    }

    fun changeSharedPreferencesValue(mealId: Int) {
        sharedPreferences.edit().putInt("mealId", mealId).apply()
    }




}