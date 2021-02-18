package com.example.definizioniconroom

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class DefinitionViewModel(application: Application) : AndroidViewModel(application) {
//connette activiy con sorgente dati e dare un observer
    val allDefinitions: LiveData<List<Definition>>
    val definitionDao: DefinitionDao

    init { //istanzia il database
        val defDb = DefinitionDatabase.getDatabase(application)
        definitionDao = defDb!!.definitionDao() //chiama la funzione che restituisce il dao
        allDefinitions = definitionDao.allDefinitions

    }

    fun add(definition: Definition) {
        InsertAsyncTask(definitionDao).execute(definition) //passa 1 n arbitrario di parametri
    }

    companion object {

        private class InsertAsyncTask(private val definitionDao: DefinitionDao) :
            AsyncTask<Definition, Void, Void>() {
            override fun doInBackground(vararg params: Definition): Void? { //array di n elem
                definitionDao.add(params[0]) // gliene sto passando solo 1 quindi non faccio cicli
                    return null
            }
        }
    }
}
//insertAsyncTask estende la  AsynkTask,contiene un oggetto DAO e fa l'override della funzione
// doInBackground a cui passi un array di definizioni da inserire
// che inserisci con l'oggetto DAO che ha come
//