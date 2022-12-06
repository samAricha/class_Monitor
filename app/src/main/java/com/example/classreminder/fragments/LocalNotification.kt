package com.example.classreminder.fragments

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import com.example.classreminder.R
import com.example.classreminder.notification.*
import kotlinx.android.synthetic.main.add_dialog.*
import kotlinx.android.synthetic.main.fragment_local_notification.*
import kotlinx.android.synthetic.main.fragment_local_notification.view.*
import java.util.Calendar
import java.util.Date

class LocalNotification : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_local_notification, container, false)

        createNotification()

        view.btnScheduleNotification.setOnClickListener {
            scheduleNotification()
            findNavController().navigate(R.id.action_localNotification_to_scheduleFragment)

        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun scheduleNotification() {
        val intent = Intent(requireContext(), Notification::class.java)

        val title = "Class reminder"
        val message = "You have a class"

        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()

        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, time, pendingIntent
        )
        showAlert(time, title, message)

    }

    private fun showAlert(time: Long, title: String, message: String) {

        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(requireContext())
        val timeFormat = android.text.format.DateFormat.getTimeFormat(requireContext())

        AlertDialog.Builder(requireContext()).setTitle("Notification scheduled")
            .setMessage(
                "Title: " + title + "\n Message: " + message + "\n At: " + dateFormat.format(
                    date
                ) + " " + timeFormat.format(date)
            ).setPositiveButton("Okay") { _, _ -> }.show()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getTime(): Long {
        val minute = timePicker.minute
        val hour = timePicker.hour
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year

        val calender = Calendar.getInstance()
        calender.set(year, month, day, hour, minute)

        return calender.timeInMillis
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification() {
        val name = "Notify Class"
        val desc = "Information of the class"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc

        val notificationManager =
            activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

}
