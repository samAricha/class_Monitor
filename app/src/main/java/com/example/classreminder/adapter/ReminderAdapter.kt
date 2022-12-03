package com.example.classreminder.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.classreminder.R
import com.example.classreminder.data.Data
import com.example.classreminder.viewmodels.ClassReminderViewModel
import kotlinx.android.synthetic.main.unit_schedule_recycleview.view.*

class ReminderAdapter(var data: List<Data>, private val viewModel: ClassReminderViewModel) :
    RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {

    inner class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.unit_schedule_recycleview, parent, false)

        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val currentItem = data[position]
        holder.itemView.apply {
            tvUnitName.text = currentItem.unit
            tvVenue.text = currentItem.room
            btnDelete.setOnClickListener {
                viewModel.delete(currentItem)
            }
            btnSetNotification.setOnClickListener {
                findNavController().navigate(R.id.action_scheduleFragment_to_localNotification)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}