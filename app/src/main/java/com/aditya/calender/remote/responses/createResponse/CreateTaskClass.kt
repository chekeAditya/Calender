package com.aditya.calender.remote.responses.createResponse

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class CreateTaskClass(
    @SerializedName("date")
    var date: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("title")
    var title: String,
)
