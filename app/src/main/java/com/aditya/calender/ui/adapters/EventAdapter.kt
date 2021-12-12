package com.aditya.calender.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.calender.R
import com.aditya.calender.databinding.ItemlayoutDetailsBinding
import com.aditya.calender.remote.interfaces.OnDeleteClicked
import com.aditya.calender.remote.responses.getResponse.Task

class EventAdapter(
    var listoftask: List<Task>,
    val onDeleteClicked: OnDeleteClicked
) : RecyclerView.Adapter<TaskListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListHolder {

        val itemlayoutDetailsBinding: ItemlayoutDetailsBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.itemlayout_details, parent, false
            )

        return TaskListHolder(itemlayoutDetailsBinding,onDeleteClicked)
    }

    override fun onBindViewHolder(holder: TaskListHolder, position: Int) {
        val list = listoftask[position]
        holder.setData(list)
        holder.itemlayoutDetailsBinding.btnDelete.setOnClickListener {
            onDeleteClicked.onDelete(position,holder.itemlayoutDetailsBinding.task?.taskId!!.toString().toInt())
        }
    }

    override fun getItemCount(): Int {
        return listoftask.size
    }
}

class TaskListHolder(
    val itemlayoutDetailsBinding: ItemlayoutDetailsBinding,
    val deleteClicked: OnDeleteClicked
) : RecyclerView.ViewHolder(itemlayoutDetailsBinding.root) {

    fun setData(task: Task) {
        itemlayoutDetailsBinding.taskdetails = task.taskDetail
        itemlayoutDetailsBinding.task = task
        itemlayoutDetailsBinding.taskidone.setText(task.taskId.toString())
    }

}
