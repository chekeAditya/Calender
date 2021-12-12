package com.aditya.calender.remote.interfaces

interface OnDateClicked {

    fun onDateClicked(position: Int, date: String)
}

interface OnDeleteClicked {
    fun onDelete(position: Int, task_id: Int)
}