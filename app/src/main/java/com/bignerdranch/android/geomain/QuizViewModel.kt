package com.bignerdranch.android.geomain

import android.util.Log
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    var currentIndex = 0
    var isCheater = false

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    val currentQuestionAnswer: Boolean
    get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
    get() = questionBank[currentIndex].textResId

    fun moveToText(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
    fun lastToText(){
        currentIndex = (currentIndex - 1) % questionBank.size
    }
}
