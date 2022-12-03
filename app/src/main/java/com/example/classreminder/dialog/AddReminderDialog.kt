package com.example.classreminder.dialog

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.classreminder.R
import com.example.classreminder.data.Data
import kotlinx.android.synthetic.main.add_dialog.*

class AddReminderDialog(context: Context, val addDialogListener: AddDialogListener): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_dialog)

        btnAdd.setOnClickListener {
            val unitName = etUnitName.text.toString()
            val location = etRoom.text.toString()

            if (unitName.isEmpty() || location.isEmpty()){
                Toast.makeText(context, "Enter all the details", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            val item = Data(unitName, location)
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }
        btnCancel.setOnClickListener {
            cancel()
        }

    }
}