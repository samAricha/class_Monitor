package com.example.classreminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.classreminder.database.MonitorDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val database = MonitorDatabase(this)
    }
}