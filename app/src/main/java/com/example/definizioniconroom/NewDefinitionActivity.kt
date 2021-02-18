package com.example.definizioniconroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new.*

class NewDefinitionActivity: AppCompatActivity() { // non chiama lei room e salva l'elemento, raccoglie solo dati
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        bAdd.setOnClickListener{

            val resultIntent = Intent() // crea intento
//controlla i campi di testo, se è vuoto diamo come risultato canceled che ci dice che non è andato a buon fine
            if (TextUtils.isEmpty(etWord.text) || TextUtils.isEmpty(etDefinition.text)) {
                setResult(RESULT_CANCELED, resultIntent)
            } else { // carica i campi di testo
                val word = etWord.text.toString()
                val definition = etDefinition.text.toString()

                resultIntent.putExtra( NEW_WORD, word) //carica il testo inserito nell'intent
                resultIntent.putExtra(NEW_DEFINITION, definition)
                setResult(RESULT_OK, resultIntent) //
            }
            finish()
        }
    }
    companion object {
        const val NEW_WORD = "nuova_parola"
        const val NEW_DEFINITION = "nuova_definizione"
    }
}