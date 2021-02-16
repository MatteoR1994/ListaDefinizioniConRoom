package com.example.definizioniconroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Definition::class], version = 1)
abstract class DefinitionDatabase : RoomDatabase() {

    abstract fun definitionDao(): DefinitionDao

    companion object {
        private var definitionRoomIstance: DefinitionDatabase? = null

        fun getDatabase(context: Context): DefinitionDatabase? {
            if (definitionRoomIstance == null) {

                synchronized(DefinitionDatabase::class.java) {
                    if (definitionRoomIstance == null) {
                        definitionRoomIstance = Room.databaseBuilder(context.applicationContext,
                                DefinitionDatabase::class.java, "definition_database")
                                .build()
                    }

                }
            }
            return definitionRoomIstance

        }

    }


}