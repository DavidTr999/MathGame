package com.techamnia.mathgame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import java.util.*
import kotlin.random.Random

class AdditionActivity : AppCompatActivity() {

    lateinit var textScore: TextView
    lateinit var textLife : TextView
    lateinit var textTime : TextView

    lateinit var textQuestion : TextView
    lateinit var editTextAnswer : EditText

    lateinit var buttonOK : Button
    lateinit var buttonNext : Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer : CountDownTimer
    private val startTimerInMillis : Long = 15000
    var timeLeftInMillis : Long = startTimerInMillis


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar!!.title = "Addition"

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTime = findViewById(R.id.textViewTime)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonOK = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()

        editTextAnswer.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                editTextAnswer.setHint("")
            }
        }

        buttonNext.isVisible = false

        buttonOK.setOnClickListener {

            val input = editTextAnswer.text.toString()
            if (input == "")
            {
            Toast.makeText(applicationContext,"You need to enter your answer", Toast.LENGTH_LONG).show()
            }
            else{
                buttonOK.isVisible = false
                buttonNext.isVisible = true
                pauseTimer()
                val userAnswer = input.toInt()

                if (userAnswer == correctAnswer)
                {
                    userScore = userScore + 10
                    textQuestion.text = "Congratulations, your answer is correct"
                    textScore.text = userScore.toString()
                }
                else{
                    userLife--
                    textQuestion.text = "Sorry, your answer is wrong, correct answer is $correctAnswer"
                    textLife.text = userLife.toString()
                }
            }

        }


        buttonNext.setOnClickListener {

                buttonOK.isVisible = true
                buttonNext.isVisible = false
                pauseTimer()
                resetTimer()
                editTextAnswer.setText("")

            if(userLife == 0)
            {
                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@AdditionActivity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            }
            else
            {
                gameContinue()

            }
        }

    }

    fun gameContinue()
    {
        val number1 = Random.nextInt(0,100)
        val number2 = Random.nextInt(0,100)

        textQuestion.text = "$number1 + $number2"

        correctAnswer = number1 + number2

        startTimer()
    }

    fun startTimer()
    {
        timer = object : CountDownTimer(timeLeftInMillis,1000){
            override fun onTick(p0: Long) {
                timeLeftInMillis = p0
                updateText()

            }

            override fun onFinish() {

                pauseTimer()
                resetTimer()
                updateText()

                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry, Time is up, correct answer is $correctAnswer"
                buttonOK.isVisible = false
                buttonNext.isVisible = true
                buttonNext.setOnClickListener {
                    if (userLife == 0) {
                        Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                        val intnet = Intent(this@AdditionActivity, ResultActivity::class.java)
                        intnet.putExtra("score", userScore)
                        startActivity(intnet)
                        finish()
                    } else {
                        editTextAnswer.setText("")
                        buttonNext.isVisible = false
                        buttonOK.isVisible = true
                        resetTimer()
                        gameContinue()

                    }
                }

            }


        }.start()
    }

    fun updateText()
    {
        val remainingTime : Int = (timeLeftInMillis / 1000).toInt()
        textTime.text = String.format(Locale.getDefault(),"%02d", remainingTime)
    }
    fun pauseTimer()
    {
        timer.cancel()
    }
    fun resetTimer()
    {
        timeLeftInMillis = startTimerInMillis
        updateText()

    }
}