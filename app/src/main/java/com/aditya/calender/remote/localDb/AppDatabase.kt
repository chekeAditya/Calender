package com.aditya.calender.remote.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aditya.calender.remote.responses.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {


    abstract fun getResponseFromDB() : AppDao


}