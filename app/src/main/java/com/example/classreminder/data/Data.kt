package com.example.classreminder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "class_info")
data class Data(
    val unit: String,
    val room: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
