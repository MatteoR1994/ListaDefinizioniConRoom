package com.example.definizioniconroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*


class MainActivity : AppCompatActivity(), OnDeleteClickListener {

    private lateinit var definitionViewModel: DefinitionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
   //     setSupportActionBar(toolbar)
// passo all'adapter l'activity 2 volte: la 1 come contesto, la 2 come oggetto di tipo ondeleteclicklistener
        val definitionListAdapter = DefinitionListAdapter(this, this) //crea adapter passa contesto
        recyclerview.adapter = definitionListAdapter // assegno adapter alla recycleview
        recyclerview.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { view -> //intento esplicito: sa a che activity passare
            val intent = Intent(this, NewDefinitionActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE) //codice costante
        } // forResult dice che ottenuto il risultato torniamo a questa activity che chiama ONACTIVITYRESULT più sotto

        definitionViewModel = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)

        definitionViewModel.allDefinitions.observe(this, Observer { definitions -> //quando alldefinitions cambia resetta adapter
            definitions?.let {
                definitionListAdapter.setDefinitions(definitions)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val id = UUID.randomUUID().toString()
            val word = data?.getStringExtra(NewDefinitionActivity.NEW_WORD)
            val wordDefinition = data?.getStringExtra(NewDefinitionActivity.NEW_DEFINITION)

            val definition = Definition(id, word!!, wordDefinition!!)

            definitionViewModel.add(definition)

            Toast.makeText(applicationContext, R.string.saved, Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(applicationContext, R.string.not_saved, Toast.LENGTH_LONG).show()
        }

        if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val id2 = data?.getStringExtra("id_passato")
            val word2 = data?.getStringExtra("nuova_parola")
            val wordDefinition2 = data?.getStringExtra("nuova_definizione")

            val definition2 = Definition(id2!!, word2!!, wordDefinition2!!)

            definitionViewModel.update(definition2)

            Toast.makeText(applicationContext, "Aggiornato con successo.", Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(applicationContext, "Problemi durante l'aggiornamento.", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
        const val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }

    override fun onDeleteDefinition(definition: Definition, position: Int) {
        definitionViewModel.delete(definition)
        definitionViewModel.readAll()
    }
}


