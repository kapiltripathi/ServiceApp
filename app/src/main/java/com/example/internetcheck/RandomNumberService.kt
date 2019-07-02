package com.example.internetcheck


import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast

import java.lang.UnsupportedOperationException
import java.util.*


class RandomNumberService: Service(){
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    //static variable to keep a track of number of times entry into showfunction
    companion object{
        var count:Int = 0
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //super.onStartCommand(intent, flags, startId)
        mHandler = Handler()
        mRunnable = Runnable { showRandomNumber() }
        //putting in message queue
        mHandler.postDelayed(mRunnable,1000)
        return START_STICKY
    }
    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException("binding not performed")
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"Destroying",Toast.LENGTH_SHORT).show()
        //removing runnables from message queue
        mHandler.removeCallbacks(mRunnable)
        Log.i("Destroying", "the count is $count")
    }

    // method to do find random number
    //recursion
    private fun showRandomNumber() {
        val rand = Random()
        val number = rand.nextInt(100)
        Toast.makeText(this,"Random Number: $number ",Toast.LENGTH_SHORT).show()
        //recursion
        mHandler.postDelayed(mRunnable, 1000)
        Log.i("showRandomNumber","into message queue $count ${Thread.currentThread().name}" )
        count += 1
    }
}

