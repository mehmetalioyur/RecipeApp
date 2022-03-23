package com.mehmetalioyur.recipeapp.mock

import com.mehmetalioyur.recipeapp.R
import com.mehmetalioyur.recipeapp.model.country.CountryModel

object CountryMockData {

    fun getCountries(): List<CountryModel> {

        val countryList = mutableListOf<CountryModel>()

        val turkey = CountryModel("Turkey", "Turkish", R.drawable.turkey)
        val canada = CountryModel("Canada", "Canadian", R.drawable.canada)
        val china = CountryModel("China", "Chinese", R.drawable.china)
        val croatia = CountryModel("Croatia", "Croatian", R.drawable.croatia)
        val england = CountryModel("England", "British", R.drawable.england)
        val egypt = CountryModel("Egypt", "Egyptian", R.drawable.egypt)
        val france = CountryModel("France", "French", R.drawable.france)
        val greece = CountryModel("Greece", "Greek", R.drawable.greece)
        val india = CountryModel("India", "Indian", R.drawable.india)
        val ireland = CountryModel("Ireland", "Irish", R.drawable.ireland)
        val italy = CountryModel("Italy", "Italian", R.drawable.italy)
        val jamaica = CountryModel("Jamaica", "Jamaican", R.drawable.jamaica)
        val japan = CountryModel("Japan", "Japanese", R.drawable.japan)
        val kenya = CountryModel("Kenya", "Kenyan", R.drawable.kenya)
        val malaysia = CountryModel("Malaysia", "Malaysian", R.drawable.malaysia)
        val mexico = CountryModel("Mexico", "Mexican", R.drawable.mexico)
        val morocco = CountryModel("Morocco", "Moroccan", R.drawable.morocco)
        val netherlands = CountryModel("Netherlands", "Dutch", R.drawable.netherlands)
        val poland = CountryModel("Poland", "Polish", R.drawable.poland)
        val portugal = CountryModel("Portugal", "Portuguese", R.drawable.portugal)
        val russia = CountryModel("Russia", "Russian", R.drawable.russia)
        val spain = CountryModel("Spain", "Spanish", R.drawable.spain)
        val thailand = CountryModel("Thailand", "Thai", R.drawable.thailand)
        val tunisia = CountryModel("Tunisia", "Tunisian", R.drawable.tunisia)
        val usa = CountryModel("Unites States", "American", R.drawable.usa)
        val vietnam = CountryModel("Vietnam", "Vietnamese", R.drawable.vietnam)
        val unknown = CountryModel("Unknown", "Unknown", R.drawable.earth)


        countryList.add(turkey)
        countryList.add(canada)
        countryList.add(china)
        countryList.add(croatia)
        countryList.add(england)
        countryList.add(egypt)
        countryList.add(france)
        countryList.add(greece)
        countryList.add(india)
        countryList.add(ireland)
        countryList.add(italy)
        countryList.add(jamaica)
        countryList.add(japan)
        countryList.add(kenya)
        countryList.add(malaysia)
        countryList.add(mexico)
        countryList.add(morocco)
        countryList.add(netherlands)
        countryList.add(poland)
        countryList.add(portugal)
        countryList.add(russia)
        countryList.add(spain)
        countryList.add(thailand)
        countryList.add(tunisia)
        countryList.add(usa)
        countryList.add(vietnam)
        countryList.add(unknown)

        return countryList
    }
}