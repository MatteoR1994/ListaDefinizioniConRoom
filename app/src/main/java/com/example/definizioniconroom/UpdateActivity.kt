package com.example.definizioniconroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new) //riutilizzo il layout di new che va bene per il mio scopo

        val id=intent.getStringExtra("id")
        val parola=intent.getStringExtra("parola")
        val definizione=intent.getStringExtra("definizione")
    }
}

// metti i campi di testo nel layout, setta un listener sul bottone: leggo il campo di testo, creo intento
// chiamo setresult , il controllo torna sulla mainactivity
