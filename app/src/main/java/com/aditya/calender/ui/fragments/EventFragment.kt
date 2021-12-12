package com.aditya.calender.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.calender.R
import com.aditya.calender.databinding.FragmentEventBinding
import com.aditya.calender.remote.Status
import com.aditya.calender.remote.interfaces.OnDeleteClicked
import com.aditya.calender.remote.responses.getResponse.Task
import com.aditya.calender.ui.adapters.EventAdapter
import com.aditya.calender.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : Fragment() ,OnDeleteClicked{


    private lateinit var fragmentEventBinding: FragmentEventBinding
    private val viewModel: AppViewModel by viewModels()
    private var taskModelList = emptyList<Task>()
    private lateinit var adapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentEventBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_event, container, false)
        return fragmentEventBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadApi()
//        fragmentEventBinding.etSearch.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
////                loadApi(s.toString())
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//
//        })
    }

    private fun loadApi() {
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.ERROR -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        taskModelList = it.data?.tasks!! as ArrayList<Task>
                        setRecycelrView()
                        adapter.notifyDataSetChanged()
                }
                Status.LOADING -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setRecycelrView() {
        adapter = EventAdapter(taskModelList,this)
        fragmentEventBinding.recyclerView.adapter = adapter
        fragmentEventBinding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDelete(position: Int, task_id: Int) {
        viewModel.deleteTaskFromAPI(task_id)
        loadApi()
    }

}