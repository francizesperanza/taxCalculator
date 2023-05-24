package com.example.taxcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.taxcalculator.databinding.ActivityCalcPageBinding
import com.example.taxcalculator.databinding.ActivityMainBinding

class CalcPage : AppCompatActivity() {
    private lateinit var binding: ActivityCalcPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        binding = ActivityCalcPageBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.btnCalc.setOnClickListener {
            val intent = Intent(applicationContext, Results::class.java)
            var monthlyIncome = binding.etMonthlyIncome.text.toString()
            intent.putExtra("input_key", monthlyIncome)
            startActivity(intent)
        }
    }
}