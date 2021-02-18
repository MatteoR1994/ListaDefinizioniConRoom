package com.example.definizioniconroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_new.*

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new) //riutilizzo il layout di new che va bene per il mio scopo

        val id = intent.getStringExtra("id")
        val parola = intent.getStringExtra("parola")
        val definizione = intent.getStringExtra("definizione")

        etWord.setText(parola)
        etDefinition.setText(definizione)

        bAdd.setOnClickListener {
            val resultIntent = Intent()

            if (TextUtils.isEmpty(etWord.text) || TextUtils.isEmpty(etDefinition.text)) {
                setResult(RESULT_CANCELED, resultIntent)
            } else { // carica i campi di testo
                val nuovaParola = etWord.text.toString()
                val nuovaDefinizione = etDefinition.text.toString()

                resultIntent.putExtra("nuova_parola", nuovaParola)
                resultIntent.putExtra("nuova_definizione", nuovaDefinizione)
                resultIntent.putExtra("id_passato", id)
                setResult(RESULT_OK, resultIntent)
            }
            finish()
        }

    }
}
