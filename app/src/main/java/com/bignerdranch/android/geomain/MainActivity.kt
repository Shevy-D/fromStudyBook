package com.bignerdranch.android.geomain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var lastButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var resultTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0
    private var countTrueAnswer = 0
    private var countAnswer = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        lastButton = findViewById(R.id.last_button)
        questionTextView = findViewById(R.id.question_text_view)
        resultTextView = findViewById(R.id.result)

        trueButton.setOnClickListener { /*view: View ->*/
            checkAnswer(true)
        }
        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        lastButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
        trueButton.isClickable = true
        falseButton.isClickable = true
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        countAnswer+=1.0
        if (userAnswer == correctAnswer) countTrueAnswer++
        if (countAnswer >= 6.0){
            resultTextView.text = "Правильных ответов: "+ (((countTrueAnswer.toDouble()/countAnswer)*100).toInt().toString())+"%"
            nextButton.isClickable = false
            lastButton.isClickable = false
            trueButton.isClickable = false
            falseButton.isClickable = false
        }
        Log.d("MainActivity", "Count = $countTrueAnswer")

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        trueButton.isClickable = false
        falseButton.isClickable = false
    }
}