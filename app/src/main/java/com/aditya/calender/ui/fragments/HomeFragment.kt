package com.aditya.calender.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.aditya.calender.R
import com.aditya.calender.databinding.FragmentHomeBinding
import com.aditya.calender.extras.Constants.USER_ID
import com.aditya.calender.remote.interfaces.OnDateClicked
import com.aditya.calender.remote.responses.createResponse.CreateResponseModel
import com.aditya.calender.remote.responses.createResponse.CreateTaskDetails
import com.aditya.calender.remote.responses.getResponse.Task
import com.aditya.calender.remote.responses.getResponse.TaskDetail
import com.aditya.calender.ui.adapters.AppAdapter
import com.aditya.calender.viewmodels.AppViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : Fragment(), OnDateClicked {

    private lateinit var homeFragmentBinding: FragmentHomeBinding
    lateinit var selectedDate: LocalDate
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
        selectedDate = LocalDate.now()
        setMonthView()
        homeFragmentBinding.btnPrevious.setOnClickListener {
            previousButtonClicked()
        }
        homeFragmentBinding.btnForward.setOnClickListener {
            nextButtonClicked()
        }
        homeFragmentBinding.btnFab.setOnClickListener {
            fabButtonClicked()
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
        homeFragmentBinding.monthYearTV.text = monthYearFromDate(selectedDate)
        val daysInMonth: ArrayList<String> = daysInMonthArray(selectedDate)
        val adapter = AppAdapter(daysInMonth, this)
        homeFragmentBinding.calendarRecyclerView.layoutManager = GridLayoutManager(context, 7)
        homeFragmentBinding.calendarRecyclerView.adapter = adapter
    }

    override fun onDateClicked(position: Int, date: String) {
        /** Bottom Sheet function */
        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val dialog = BottomSheetDialog(requireActivity())
        dialog.setContentView(view)
        dialog.show()

        val mdate = "$date ${monthYearFromDate(selectedDate)}"
        dialog.etDate.text = mdate
        val title = dialog.etTask.text
        val desc = dialog.etDesciption.text

        dialog.btnSave.setOnClickListener {
            val task = CreateTaskDetails(date = mdate, description = desc.toString(),title = title.toString())
            val response = CreateResponseModel(task, USER_ID)
            viewModel.storeNewData(response)
            dialog.dismiss()
        }
    }

    private fun previousButtonClicked() {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    private fun nextButtonClicked() {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

    fun fabButtonClicked() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_homeFragment_to_eventFragment)
    }

}