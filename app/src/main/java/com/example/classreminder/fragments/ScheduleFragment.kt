package com.example.classreminder.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.classreminder.R
import com.example.classreminder.adapter.ReminderAdapter
import com.example.classreminder.data.Data
import com.example.classreminder.database.MonitorDatabase
import com.example.classreminder.dialog.AddDialogListener
import com.example.classreminder.dialog.AddReminderDialog
import com.example.classreminder.repository.ClassReminderRepository
import com.example.classreminder.viewmodels.ClassReminderViewModel
import com.example.classreminder.viewmodels.ClassReminderViewModelFactory
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*

class ScheduleFragment : Fragment() {

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        val database = MonitorDatabase(requireContext())
        val repository = ClassReminderRepository(database)
        val factory = ClassReminderViewModelFactory(repository)
        val viewmodel = ViewModelProvider(this, factory).get(ClassReminderViewModel::class.java)

        val reminderAdapter = ReminderAdapter(listOf(), viewmodel)
        view.rvSchedules.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reminderAdapter
        }

        viewmodel.getAllSchedules().observe(viewLifecycleOwner, Observer {
            reminderAdapter.data = it
            reminderAdapter.notifyDataSetChanged()
        })

        view.btnAddSchedule.setOnClickListener{
             AddReminderDialog(requireContext(), object :AddDialogListener{
                 override fun onAddButtonClicked(item: Data) {
                     viewmodel.upsert(item)
                 }
             }).show()
        }


        return view
    }
}