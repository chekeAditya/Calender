package com.aditya.calender.remote.localDb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aditya.calender.remote.responses.TaskModel

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataToDB(taskModel: TaskModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataToDBList(task: ArrayList<TaskModel>)

    @Query("select * from app_db")
    fun getDataFromDb(): LiveData<List<TaskModel>>

    @Update
    fun updateData(task: TaskModel)

    @Delete
    fun deleteTask(task: TaskModel)

    @Query("delete from app_db")
    fun deleteAll()


}