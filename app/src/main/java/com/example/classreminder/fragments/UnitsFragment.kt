package com.example.classreminder.fragments

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.classreminder.R
import com.example.classreminder.adapter.UnitsAdapter
import com.example.classreminder.data.AttendanceData
import com.example.classreminder.data.UnitModel
import com.google.firebase.database.*

class UnitsFragment : Fragment() {

    lateinit var mDatabaseRef: DatabaseReference
    lateinit var unitsList: MutableList<UnitModel>
    lateinit var recyclerView: RecyclerView
    lateinit var unitsAdapter: UnitsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_units, container, false)

        recyclerView = root.findViewById(R.id.unitsRView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        mDatabaseRef = database.getReference("root")

        unitsList = arrayListOf()
        loadUnits()
        return root
    }

    private fun loadUnits() {
        mDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.children
                var count = 0;

                for (unit in data){
                    val unitDetails = unit.getValue(UnitModel::class.java)

                    if (unitDetails != null) {
                        count = unitDetails.unitName?.let { getPercentage(it) }!!
                        unitDetails.count = count+1
                    }
                    unitsList.add(unitDetails!!)
                }
                unitsAdapter = UnitsAdapter()
                activity?.let { unitsAdapter.setUnitsList(unitsList, it.applicationContext) }
                recyclerView.adapter = unitsAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException())
            }
        })
    }

    private fun getPercentage(unitName:String): Int{
        var count = 0
        mDatabaseRef.child(unitName).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.children
                for (unit in data){
                    val unitDetails = unit.getValue(AttendanceData::class.java)
                    count++

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return count
    }
}