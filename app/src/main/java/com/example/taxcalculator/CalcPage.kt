package com.example.taxcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import com.example.taxcalculator.databinding.ActivityCalcPageBinding

/**
 * This class accepts the monthly income input from the user
 */
class CalcPage : AppCompatActivity() {
    private lateinit var binding: ActivityCalcPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        binding = ActivityCalcPageBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        var btnBackToMainMenu = findViewById<Button>(R.id.btnBackToMain)

        // On click listener for Calculate button
        binding.btnCalc.setOnClickListener {
            val intent = Intent(applicationContext, Results::class.java)
            var monthlyIncome = binding.etMonthlyIncome.text.toString()
            intent.putExtra("input_key", monthlyIncome)
            startActivity(intent)
        }

        // On click listener for Calculate button
        btnBackToMainMenu.setOnClickListener{
            finish()
            this.overridePendingTransition(0,0)
        }
    }
}