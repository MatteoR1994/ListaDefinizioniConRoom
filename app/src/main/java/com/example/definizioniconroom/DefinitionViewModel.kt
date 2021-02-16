package com.example.definizioniconroom

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class DefinitionViewModel(application: Application) : AndroidViewModel(application) {

    val definitions: LiveData<List<Definition>>
    val definitionDao: DefinitionDao

    init {
        val defDb = DefinitionDatabase.getDatabase(application)
        definitionDao = defDb!!.definitionDao()
        definitions = definitionDao.allDefinition

    }

    fun add(definition: Definition) {
        InsertAsyncTask(definitionDao).execute(definition)
    }

    companion object {

        private class InsertAsyncTask(private val definitionDao: DefinitionDao) :
            AsyncTask<Definition, Void, Void>() {
            override fun doInBackground(vararg params: Definition): Void? {
                definitionDao.add(params[0])
                    return null
            }
        }
    }


}