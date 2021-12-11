package com.aditya.calender.remote.responses


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "app_db")
data class TaskModel(
    @SerializedName("description")
    val description: String?,
    @SerializedName("title")
    val title: String?,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}