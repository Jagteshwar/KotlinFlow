package com.jagteshwar.kotlinflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG= "KOTLINFLOW"
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val job = GlobalScope.launch {
            val data: Flow<Int> = producer()
            data.collect{
                Log.d(TAG, it.toString())
            }
        }

        GlobalScope.launch {
            delay(3500)
            job.cancel()
        }
    }

    private fun producer() = flow{
      val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }
}