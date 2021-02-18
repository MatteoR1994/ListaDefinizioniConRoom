package com.example.definizioniconroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//creiamo il database, elenchiamo tutte le entities presenti e la sua versione
@Database(entities = [Definition::class], version = 1)
abstract class DefinitionDatabase : RoomDatabase() {
// la classe astratta del database implementa la classe database della libreria room
    abstract fun definitionDao(): DefinitionDao
// creo una funzione che mi restituisce un oggetto DAO
    companion object {
        private var definitionRoomIstance: DefinitionDatabase? = null
//creo una variabile statica di tipo classe che inizializzo a null
    //il cui scopo è di controllo dell'essere o meno stato istanziato
        fun getDatabase(context: Context): DefinitionDatabase? {
            if (definitionRoomIstance == null) {
//singleton design pattern:se non ho istanziato il db fa partire un blocco syncronized: accessibile
    //da 1 thread x volta,fa doppio controllo per avviarlo meno possibile xchè dispendioso
                synchronized(DefinitionDatabase::class.java) {
                    if (definitionRoomIstance == null) {
                        definitionRoomIstance = Room.databaseBuilder(context.applicationContext,
                                DefinitionDatabase::class.java, "definition_database")
                                .build()
                    } // builder design pattern: si occupa di costruire, dici come :
//contesto, classe database, nome database e poi dai il comando di farlo
                }
            }
            return definitionRoomIstance
        }
    }
}
// quindi alla fine la classe database estende la RoomDatabase
//crea un oggetto DAO
// crea una variabile statica che mi tenga traccia del fatto che sia stato creato o meno
// fa una get del database in cui setta 'sta variabile quando lo istanzia