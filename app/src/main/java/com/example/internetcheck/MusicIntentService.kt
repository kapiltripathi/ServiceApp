package com.example.internetcheck

import android.app.IntentService
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log

class MusicIntentService: IntentService("workerThreadNew"){
    override fun onHandleIntent(intent: Intent?) {
          Log.i("thread name", "the thread name= ${Thread.currentThread().name}")
        var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.mask_off)
        mediaPlayer?.start()
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.i("destroy service", "musicintentservice destroyed")
    }

}