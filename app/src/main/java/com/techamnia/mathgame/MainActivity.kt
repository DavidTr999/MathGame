package com.techamnia.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var addition : Button
    lateinit var subtraction : Button
    lateinit var multi : Button
    lateinit var division : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition = findViewById(R.id.buttonAdd)
        subtraction = findViewById(R.id.buttonSub)
        multi = findViewById(R.id.buttonMulti)
        division = findViewById(R.id.buttonDivide)

        addition.setOnClickListener {

            val intent = Intent(this@MainActivity, AdditionActivity::class.java)
            startActivity(intent)
        }

        subtraction.setOnClickListener {
            val intent = Intent(this@MainActivity, SubtractionActivity::class.java)
            startActivity(intent)

        }

        multi.setOnClickListener {
            val intent = Intent(this@MainActivity, MultiplicationActivity::class.java)
            startActivity(intent)

        }

        division.setOnClickListener {
            val intent = Intent(this@MainActivity, DivisionActivity::class.java)
            startActivity(intent)
        }
    }
}