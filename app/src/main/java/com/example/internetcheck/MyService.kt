package com.example.internetcheck

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class MyService: Service(){
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(this,"Service Started",Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"Service Destroyed",Toast.LENGTH_SHORT).show()

    }

}