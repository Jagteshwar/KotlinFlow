package com.jagteshwar.kotlinflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG= "KOTLINFLOW"
    private val channel = Channel<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        producer()
        consumer()
    }

    private fun producer(){
        CoroutineScope(Dispatchers.Main).launch {
            channel.send(1)
            channel.send(2)
        }
    }
    private fun consumer(){
        CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG, channel.receive().toString())
            Log.d(TAG, channel.receive().toString())
        }
    }
}