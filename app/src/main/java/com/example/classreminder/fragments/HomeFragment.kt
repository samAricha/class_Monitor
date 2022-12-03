package com.example.classreminder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.classreminder.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.btnSchedules.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_scheduleFragment)
        }

        view.btnMyAttendance.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_attendanceFragment)
        }

        view.btnUnits.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_unitsFragment2)
        }



        return view
    }
}