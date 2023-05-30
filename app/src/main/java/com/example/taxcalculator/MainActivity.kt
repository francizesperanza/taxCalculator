package com.example.taxcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.taxcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0,0)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.btnStart.setOnClickListener{
            val intent = Intent(applicationContext,CalcPage::class.java)
            startActivity(intent)
        }

        binding.btnRates.setOnClickListener{
            val intent = Intent(applicationContext,Rates::class.java)
            startActivity(intent)
        }

        binding.btnExit.setOnClickListener{
            finish()
            System.exit(0)
        }
    }
}