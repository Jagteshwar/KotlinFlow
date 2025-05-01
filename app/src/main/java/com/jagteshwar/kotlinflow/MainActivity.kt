package com.jagteshwar.kotlinflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG = "KOTLINFLOW"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .flowOn(Dispatchers.IO)
                .collect {
                  //  Log.d(TAG, it.toString())
                    Log.d(TAG, "Collector thread ${Thread.currentThread().name}")

                }
        }

    }

    private fun producer(): Flow<Int> {
        return flow {
            val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                list.forEach {
                    delay(1000)
                    Log.d(TAG, "Emitter thread ${Thread.currentThread().name}")
                    emit(it)
            }
        }
    }
}