package com.aditya.calender.repositories

import com.aditya.calender.extras.Constants.TOKEN
import com.aditya.calender.extras.Constants.USER_ID
import com.aditya.calender.remote.Resource
import com.aditya.calender.remote.ResponseHandler
import com.aditya.calender.remote.interfaces.APIClient
import com.aditya.calender.remote.responses.getResponse.GetTaskResponse
import com.aditya.calender.remote.responses.getResponse.ResponseData
import com.aditya.calender.remote.responses.getResponse.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppRepo @Inject constructor(val apiClient: APIClient) {

    private val responseHandler = ResponseHandler()

    suspend fun getResponseFromApi(): Resource<ResponseData> {
        return try {
            val response = apiClient.getResponseFromAPI(GetTaskResponse(USER_ID))
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    fun storeTaskToAPI(task: Task) {
        CoroutineScope(IO).launch {
            apiClient.storeResponseToAPI(TOKEN, task)
        }
    }

    fun deleteTaskFromAPI(task_id: Int) {
        CoroutineScope(IO).launch {
            apiClient.deleteTaskFromAPI(USER_ID, task_id)
        }
    }
}