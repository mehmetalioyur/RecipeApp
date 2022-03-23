package com.mehmetalioyur.recipeapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.mehmetalioyur.recipeapp.data.local.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, RecipeDatabase::class.java, "RecipesDB").build()

    @Singleton
    @Provides
    fun injectDao(database: RecipeDatabase) = database.recipeDao()


}