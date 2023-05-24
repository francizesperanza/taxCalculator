package com.example.taxcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.TextView

class Rates : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rates)
        val SSSLink = findViewById<TextView>(R.id.linkSSS);
        if (SSSLink != null) {
            SSSLink.setMovementMethod(LinkMovementMethod.getInstance());
        }

        val PhilHealthLink = findViewById<TextView>(R.id.linkPhilHealth);
        if (PhilHealthLink != null) {
            PhilHealthLink.setMovementMethod(LinkMovementMethod.getInstance());
        }

        val PagIBIGLink = findViewById<TextView>(R.id.linkPagIbig);
        if (PagIBIGLink != null) {
            PagIBIGLink.setMovementMethod(LinkMovementMethod.getInstance());
        }

        val WithholdingLink = findViewById<TextView>(R.id.linkWithholding);
        if (WithholdingLink != null) {
            WithholdingLink.setMovementMethod(LinkMovementMethod.getInstance());
        }

        var btnBackToMainMenu = findViewById<Button>(R.id.btnBackToMain2)
        btnBackToMainMenu.setOnClickListener{
            finish()
            this.overridePendingTransition(0,0)
        }
    }
}