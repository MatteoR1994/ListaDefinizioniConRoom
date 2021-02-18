package com.example.definizioniconroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "definizioni")
class Definition (
    @PrimaryKey val id: String,
    @ColumnInfo(name="parola")
    val word:String,
    @ColumnInfo(name="descrizione")
    val description:String) {

}
// questa classe descrive l'entità tabella "definizioni"
// viene definita la primary key
// e le altre colonne facenti parte di essa, nominate in modo diverso dalla variabile