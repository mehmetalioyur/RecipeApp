package com.mehmetalioyur.recipeapp.util

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.mehmetalioyur.recipeapp.R

class ViewStatus {

    fun hideScreen(view: View) {
        view.visibility = View.GONE
    }

    fun showScreen(view: View) {
        view.visibility = View.VISIBLE
    }

    fun hideProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }

    fun showProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    fun hideErrorMessage(textView: TextView) {
        textView.visibility = View.GONE
    }

    fun showErrorMessage(textView: TextView) {
        textView.visibility = View.VISIBLE
        textView.text = R.string.error_message.toString()
    }
    fun showMealExist(textView: TextView,animation :LottieAnimationView){
        textView.visibility = View.VISIBLE
        animation.visibility= View.VISIBLE
    }

    fun hideMealExist(textView: TextView,animation :LottieAnimationView){
        textView.visibility = View.GONE
        animation.visibility= View.GONE
    }

}