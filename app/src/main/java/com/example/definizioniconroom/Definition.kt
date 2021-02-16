package com.example.definizioniconroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "definizioni")
class Definition (@PrimaryKey val id: String, @ColumnInfo(name="parola")  val word:String,
@ColumnInfo(name="descrizione")  val description:String) {

}