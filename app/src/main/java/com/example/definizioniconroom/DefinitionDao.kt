package com.example.definizioniconroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DefinitionDao {
    @Insert
    fun add(def:Definition)

    @get:Query("SELECT * FROM   definizioni")
    val allDefinition:LiveData<List<Definition>>

}