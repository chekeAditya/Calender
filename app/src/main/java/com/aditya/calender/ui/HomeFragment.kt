package com.aditya.calender.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.aditya.calender.R
import com.aditya.calender.databinding.FragmentHomeBinding
import com.aditya.calender.remote.interfaces.OnDateClicked
import com.aditya.calender.remote.responses.ResponseModel
import com.aditya.calender.remote.responses.TaskModel
import com.aditya.calender.ui.adapters.AppAdapter
import com.aditya.calender.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : Fragment(), OnDateClicked {

    private lateinit var homeFragmentBinding: FragmentHomeBinding
    lateinit var date: LocalDate
    private val viewModel: AppViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        date = LocalDate.now()
        setMonthView()
        homeFragmentBinding.btnPrevious.setOnClickListener {
            date = date.minusMonths(1)
            setMonthView()
        }

        homeFragmentBinding.btnForward.setOnClickListener {
            date = date.plusMonths(1)
            setMonthView()
        }
    }


    private fun monthYearFromDate(date: LocalDate): String {
        val dateFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(dateFormatter)
    }

    fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray: ArrayList<String> = arrayListOf()
        val yearMonth = YearMonth.from(date)

        val daysInMonth: Int = yearMonth.lengthOfMonth()

        val firstOfMonth: LocalDate = date.withDayOfMonth(1)
        val dayOfWeek: Int = firstOfMonth.dayOfWeek.value

        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    private fun setMonthView() {
        homeFragmentBinding.monthYearTV.text = monthYearFromDate(date)
        val daysInMonth: ArrayList<String> = daysInMonthArray(date)
        val adapter = AppAdapter(daysInMonth, this)
        homeFragmentBinding.calendarRecyclerView.layoutManager = GridLayoutManager(context, 7)
        homeFragmentBinding.calendarRecyclerView.adapter = adapter
    }

    override fun onDateClicked(position: Int, date: String) {
        val task = TaskModel("done with the sudat", "Response updated")
        val responseModel = ResponseModel(task, 1008)
        viewModel.createTask(responseModel)
    }

}