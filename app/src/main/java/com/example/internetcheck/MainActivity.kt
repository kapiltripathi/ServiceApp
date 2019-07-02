package com.example.internetcheck

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver

import android.content.Intent
import android.content.IntentFilter


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private val br: BroadcastReceiver = ConnectionReciever()
    private lateinit var handler1: Handler
    private lateinit var progressBar: ProgressBar
    private lateinit var handler2: Handler
    private lateinit var textView: TextView
    private var elapsedTime = 0
    private lateinit var message: Message

    //initialize the handler it is linked to main now
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //if using only post to send runnables use simple Handler()
        handler1 = Handler()
        handler2 = Handler()
        progressBar = findViewById(R.id.progressBar1)
        textView = findViewById((R.id.hello))


        //val serviceClass = RandomNumberService::class.java

        // Initialize a new Intent instance
       // val intent1 = Intent(applicationContext, serviceClass)
    }
    //Inherited from Handler class to implement handleMessage function
    inner class HandlerNew: Handler(){
        override fun handleMessage(msg: Message) {
            progressBar.setProgress(msg.arg1)
            Log.i("thread name handle message", "the name of thread is ${Thread.currentThread().name}")
        }
    }
    //recursive ccall to count1
    fun count1(view: View) {
        elapsedTime++
        textView.setText(elapsedTime.toString())
        //posting to main thread
        handler2.postDelayed({count1(view)},1000)
    }

   //private var r: Runnable = Runnable {count1()}
    //creating a new thread
    //if only usig runnables use Task()
    fun startProgress(view: View){
        Thread(Task()).start()
    }
    //keyword inner is imp
    inner class Task: Runnable{
        override fun run() {//this for loop runs in the new thread created
            for(i in 1 until 100 step 10){
                try{Log.i("thread name", "the thread of the Task  is ${Thread.currentThread().name}")
                    Thread.sleep(1000)
                } catch (e: InterruptedException){
                    e.printStackTrace()
                }
                handler1.post{ progressBar.setProgress(i)
                //here we enter into main thread which is attached to the handler
                Log.i("thread name","the thread of the handler is ${Thread.currentThread().name}")}


            }
        }
    }
    inner class Task1: Runnable{
        override fun run(){
            for(i in 1 until 100 step 10){
                try { message = Message.obtain()
                    message.arg1 = i
                    message.arg2 = i*i
                    Log.i("thread 3", "the different thread is ${Thread.currentThread().name}")
                    Thread.sleep(1000)
                } catch(e: InterruptedException){
                    e.printStackTrace()
                }
                handler1.sendMessage(message)
                Log.i("thread name","the handler thread is ${Thread.currentThread().name}")
            }
        }
    }
    //register receiver only when screen has focus
    override fun onResume(){
        super.onResume()
        Log.i("on resume", " onResume Called")
        val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        registerReceiver(br, filter)
    }
    //unregister receiver
    override fun onPause(){
        super.onPause()
        Log.i("on Pause", " onPause called")
        unregisterReceiver(br)
    }
    fun stopService(view: View){
        stopService(Intent(baseContext,MyService::class.java))
    }
    fun startService(view: View){
        startService(Intent(baseContext, MyService::class.java))
    }
    //start service random number
    fun startService1(view: View){
        startService(Intent(baseContext, RandomNumberService::class.java))
    }
    //stop service random number
    fun stopService1(view: View){
        stopService(Intent(baseContext, RandomNumberService::class.java))
    }
    //func to start intent service
    fun startService2(view: View){
        startService(Intent(baseContext, MusicIntentService::class.java))
    }
}
