package com.jagteshwar.kotlinflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG = "KOTLINFLOW"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
                result.collect {
                    Log.d(TAG, "First collector-> $it")
                   // Log.d(TAG, "Collector thread ${Thread.currentThread().name}")

                }
        }

        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            delay(2500)
            result.collect {
                Log.d(TAG, "Second collector-> $it")
            }
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun producer(): Flow<Int> {
      val mutableSharedFlow = MutableSharedFlow<Int>(2)
        GlobalScope.launch {
            val list = listOf(1, 2, 3, 4, 5)
            list.forEach {
                mutableSharedFlow.emit(it)
                delay(1000)
            }
        }
        return mutableSharedFlow
    }
}