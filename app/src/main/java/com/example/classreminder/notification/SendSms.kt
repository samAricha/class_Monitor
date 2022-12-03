package com.example.classreminder.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager

//primary constructor used as below
//SendSms(this@MainActivity, "+254708392326", "hiii bro")
class SendSms(ctx: Context, phoneNumber: String, message: String, ) {

    // initializer block
    init {
        sendSMS(ctx,"+254708392326", "Some text here")
    }

    private fun sendSMS(ctx:Context, phoneNumber: String, message: String) {
        val sentPI: PendingIntent = PendingIntent.getBroadcast(ctx, 0, Intent("SMS_SENT"), 0)
        SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, sentPI, null)
    }

}