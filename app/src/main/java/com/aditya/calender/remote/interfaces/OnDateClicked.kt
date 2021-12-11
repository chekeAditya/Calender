package com.aditya.calender.remote.interfaces

import java.time.LocalDate

interface OnDateClicked {

    fun onDateClicked(position: Int, date: String)
}