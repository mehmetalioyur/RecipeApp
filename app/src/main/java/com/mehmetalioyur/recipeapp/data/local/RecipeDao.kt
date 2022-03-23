package com.mehmetalioyur.recipeapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mehmetalioyur.recipeapp.model.meal.Meal


@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(meal: Meal)

    @Delete //item sil
    suspend fun deleteRecipe(meal: Meal)

    @Query("SELECT * FROM recipes")
    fun observeRecipes(): LiveData<List<Meal>>

    @Query("SELECT EXISTS(SELECT * FROM recipes WHERE idMeal = :idMeal)")
    suspend fun isRowExist(idMeal : Int?) : Boolean

}
