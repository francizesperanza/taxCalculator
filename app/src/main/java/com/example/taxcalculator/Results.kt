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
            this.overridePendingTransition(0,0)
        }
    }
    fun computeSSSContribution (monthlyIncome: Double): Double {
        val bracketNo: Int = if (monthlyIncome < 1000.0)
            return 0.0
        else if (monthlyIncome in 1000.0..3249.99)
            0
        else if (monthlyIncome in 3250.0..24749.99)
            Math.floorDiv((monthlyIncome - 3250.0).toInt(), 500) + 1
        else
            44

        return 135 + (22.5 * bracketNo)
    }

    fun computePhilHealthContribution (monthlyIncome: Double): Double {
        return if (monthlyIncome < 10000)
            200.0
        else if (monthlyIncome in 10000.0..79999.99)
            monthlyIncome * .04 / 2
        else
            3200.0
    }

    fun computePagIbigContribution (monthlyIncome: Double): Double {
        return if (monthlyIncome < 5000)
            if (monthlyIncome <= 1500)
                monthlyIncome * .01
            else
                monthlyIncome * .02
        else
            100.0
    }

    fun computeIncomeTax (taxableIncome: Double): Double {
        val baseTax: Double
        val withholdingTax: Double

        when{
            taxableIncome < 0.0 ||
                    taxableIncome in 0.0..20832.0 -> {baseTax = 0.0
                withholdingTax = 0.0}
            taxableIncome in 20833.0..33332.0 -> {baseTax = 0.0
                withholdingTax = (taxableIncome-20833.0) * .2}
            taxableIncome in 33333.0..66666.0 -> {baseTax = 2500.0
                withholdingTax = (taxableIncome-33333.0) * .25}
            taxableIncome in 66667.0..166666.0 -> {baseTax = 10833.33
                withholdingTax = (taxableIncome-66667.0) * .30}
            taxableIncome in 166667.0..666666.0 -> {baseTax = 40833.33
                withholdingTax = (taxableIncome-166667.0) * .32}
            else -> {baseTax = 200833.33
                withholdingTax = (taxableIncome-666667.0) * .35}
        }
        return baseTax + withholdingTax
    }
}