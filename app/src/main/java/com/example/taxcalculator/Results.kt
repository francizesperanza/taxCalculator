package com.example.taxcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0,0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val formatter: NumberFormat = DecimalFormat("₱ #,##0.00")
        formatter.roundingMode = RoundingMode.CEILING

        val intent = intent
        var str = intent.getStringExtra("input_key")
        var monthlyIncome = str.toString().toDouble()

        var SSSContribution: Double = computeSSSContribution(monthlyIncome)
        var philHealthContribution: Double = computePhilHealthContribution(monthlyIncome)
        var pagIbigContribution: Double = computePagIbigContribution(monthlyIncome)
        var contributions: Double = SSSContribution + philHealthContribution + pagIbigContribution
        var taxableIncome: Double = monthlyIncome - contributions
        var incomeTax: Double = computeIncomeTax(taxableIncome)
        var netPayAfterTax: Double = monthlyIncome - incomeTax
        var totalDeductions: Double = incomeTax + contributions
        var netPayAfterDeductions: Double = monthlyIncome - totalDeductions

        findViewById<TextView>(R.id.monthlyInc).setText(formatter.format(monthlyIncome).toString())
        findViewById<TextView>(R.id.SSS).setText(formatter.format(SSSContribution).toString())
        findViewById<TextView>(R.id.PhilHealth).setText(formatter.format(philHealthContribution).toString())
        findViewById<TextView>(R.id.PagIBIG).setText(formatter.format(pagIbigContribution).toString())
        findViewById<TextView>(R.id.totalCont).setText(formatter.format(contributions).toString())
        findViewById<TextView>(R.id.Taxable).setText(formatter.format(taxableIncome).toString())
        findViewById<TextView>(R.id.IncomeTax).setText(formatter.format(incomeTax).toString())
        findViewById<TextView>(R.id.afterTax).setText(formatter.format(netPayAfterTax).toString())
        findViewById<TextView>(R.id.totalDeduct).setText(formatter.format(totalDeductions).toString())
        findViewById<TextView>(R.id.afterDeduct1).setText(formatter.format(netPayAfterDeductions).toString())

        var btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener{
            finish()
        }
    }
    fun computeSSSContribution (monthlyIncome: Double): Double {
        var bracketNo: Int

        if (monthlyIncome < 1000.0)
            return 0.0
        else if (monthlyIncome >= 1000.0 && monthlyIncome <= 3249.99)
            bracketNo = 0
        else if (monthlyIncome >= 3250.0 && monthlyIncome <= 24749.99)
            bracketNo = Math.floorDiv((monthlyIncome - 3250.0).toInt(), 500) + 1
        else
            bracketNo = 44

        var contribution = 135 + (22.5 * bracketNo)

        return contribution
    }

    fun computePhilHealthContribution (monthlyIncome: Double): Double {
        if (monthlyIncome < 10000)
            return 225.0
        else if (monthlyIncome >= 10000 && monthlyIncome < 90000)
            return monthlyIncome * .045 / 2
        else
            return 4050.0
    }

    fun computePagIbigContribution (monthlyIncome: Double): Double {
        if (monthlyIncome < 5000)
            if (monthlyIncome <= 1500)
                return monthlyIncome * .01
            else
                return monthlyIncome * .02
        else
            return 100.0
    }

    fun computeIncomeTax (taxableIncome: Double): Double {
        var baseTax: Double
        var withholdingTax: Double

        // when statements are exclusive to Kotlin
        // - it is a more flexible switch statement
        // - it makes writing multiple if-else statements easier

        when {
            taxableIncome < 0.0 ||
                    taxableIncome in 0.0..20832.0 -> {
                baseTax = 0.0
                withholdingTax = 0.0
            }
            taxableIncome in 20833.0..33332.0 -> {
                baseTax = 0.0
                withholdingTax = (taxableIncome - 20833.0) * .2
            }
            taxableIncome in 33333.0..66666.0 -> {
                baseTax = 2500.0
                withholdingTax = (taxableIncome - 33333.0) * .25
            }
            taxableIncome in 66667.0..166666.0 -> {
                baseTax = 10833.33
                withholdingTax = (taxableIncome - 66667.0) * .30
            }
            taxableIncome in 166667.0..666666.0 -> {
                baseTax = 40833.33
                withholdingTax = (taxableIncome - 166667.0) * .32
            }
            else -> {
                baseTax = 200833.33
                withholdingTax = (taxableIncome - 666667.0) * .35
            }
        }
        return baseTax + withholdingTax
    }
}