package com.aditya.calender.repositories

import com.aditya.calender.extras.Constants.token
import com.aditya.calender.remote.ResponseHandler
import com.aditya.calender.remote.interfaces.APIClient
import com.aditya.calender.remote.localDb.AppDao
import com.aditya.calender.remote.responses.ResponseModel
import com.aditya.calender.remote.responses.TaskModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppRepo @Inject constructor(val appDao: AppDao, val apiClient: APIClient) {

    private val responseHandler = ResponseHandler()


    fun getResponseFromAPI() {
        CoroutineScope(IO).launch {
            val response = apiClient.getTask(token)
            saveTODB(response)
        }
    }

    fun createTask(responseModel: ResponseModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiClient.storeTask(token, responseModel)
            if (response != null) {
                val newTask = TaskModel(
                    title = response.taskModel.title,
                    description = response.taskModel.description
                )
                appDao.insertDataToDB(newTask)
            }
        }
    }

    private fun saveTODB(response: ResponseModel) {
        appDao.deleteAll()
        appDao.insertDataToDB(response.taskModel)
    }
}