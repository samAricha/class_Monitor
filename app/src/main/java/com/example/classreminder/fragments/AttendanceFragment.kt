package com.example.classreminder.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.classreminder.R
import com.example.classreminder.data.AttendanceData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//NB btns placement as follows
/*
* 1. Entrepreneurship
* 2. Mobile Development
* 3. Cloud Computing
* 4. N-tier Architecture
* */
class AttendanceFragment : Fragment(), View.OnClickListener{

    private lateinit var database: DatabaseReference
    lateinit var unit1:String
    lateinit var unit2:String
    lateinit var unit3:String
    lateinit var unit4:String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_attendance, container, false)
         database = FirebaseDatabase.getInstance().reference

        unit1 = "Entrepreneurship"
        unit2 = "Mobile Development"
        unit3 = "Cloud Computing"
        unit4 = "N-tier Architecture"


        //onclick listener for buttons
        var btn1Absent = root.findViewById(R.id.btn1Absent) as Button
        btn1Absent.setOnClickListener(this); // calling onClick() method
        var btn2Absent = root.findViewById(R.id.btn2Absent) as Button
        btn2Absent.setOnClickListener(this); // calling onClick() method
        var btn3Absent = root.findViewById(R.id.btn3Absent) as Button
        btn3Absent.setOnClickListener(this); // calling onClick() method
        var btn4Absent = root.findViewById(R.id.btn4Absent) as Button
        btn4Absent.setOnClickListener(this); // calling onClick() method

        var btn1Present = root.findViewById(R.id.btn1Present) as Button
        btn1Present.setOnClickListener(this); // calling onClick() method
        var btn2Present = root.findViewById(R.id.btn2Present) as Button
        btn2Present.setOnClickListener(this); // calling onClick() method
        var btn3Present = root.findViewById(R.id.btn3Present) as Button
        btn3Present.setOnClickListener(this); // calling onClick() method
        var btn4Present = root.findViewById(R.id.btn4Present) as Button
        btn4Present.setOnClickListener(this); // calling onClick() method

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn1Absent -> { // Toast message appears when button pressed
                recordAttendance(unit1, false, getCurrentTime())
                Toast.makeText(activity, getCurrentTime(), Toast.LENGTH_SHORT).show()
            }
            R.id.btn2Absent -> {
                recordAttendance(unit2, false, getCurrentTime())
                Toast.makeText(activity, "button2 pressed", Toast.LENGTH_SHORT).show()
            }
            R.id.btn3Absent -> {
                recordAttendance(unit3, false, getCurrentTime())
                Toast.makeText(activity, "button3 pressed", Toast.LENGTH_SHORT).show()
            }
            R.id.btn4Absent -> {
                recordAttendance(unit4, false, getCurrentTime())
                Toast.makeText(activity, "button3 pressed", Toast.LENGTH_SHORT).show()
            }

            R.id.btn1Present -> {
                recordAttendance(unit1, true, getCurrentTime())
                Toast.makeText(activity, "button3 pressed", Toast.LENGTH_SHORT).show()
            }
            R.id.btn2Present -> {
                recordAttendance(unit2, true, getCurrentTime())
                Toast.makeText(activity, "button3 pressed", Toast.LENGTH_SHORT).show()
            }
            R.id.btn3Present -> {
                recordAttendance(unit3, true, getCurrentTime())
                Toast.makeText(activity, "button3 pressed", Toast.LENGTH_SHORT).show()
            }
            R.id.btn4Present -> {
                recordAttendance(unit4, true, getCurrentTime())
                Toast.makeText(activity, "button3 pressed", Toast.LENGTH_SHORT).show()
            }

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun recordAttendance(unitName: String, attended: Boolean, dateRecorded: String) {
        val attendance = AttendanceData(unitName, attended, dateRecorded)
        var key = database.push().key!!
        database.child("root").child(unitName).child(getCurrentDate()).setValue(attendance)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTime():String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)

        return current

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDate():String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val current = LocalDateTime.now().format(formatter)

        return current

    }


}