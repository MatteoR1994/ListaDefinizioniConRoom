package com.example.definizioniconroom

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DefinitionDao {
    @Insert
    fun add(def:Definition)

    @Delete
    fun delete(def:Definition)

    @Update
    fun update(def:Definition)

    @get:Query("SELECT * FROM   definizioni")
    val allDefinitions:LiveData<List<Definition>>

}

// viene creata l'interfaccia DAO data access object per il db
// che definisce per l'appunto i metodi di accesso al database
// l'insert di un oggetto di tipo Definition (la entity)
// la query sulla tabella "definizioni" descritta dalla entity
// viene creato un oggetto liveData formato da una lista di entità definition