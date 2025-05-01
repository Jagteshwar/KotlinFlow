package com.jagteshwar.kotlinflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG= "KOTLINFLOW"
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     GlobalScope.launch {
            val data: Flow<Int> = producer()
            data
                .onStart {
                    emit(-1)
                    Log.d(TAG, "Starting out")
                }
                .onCompletion {
                    emit(11)
                    Log.d(TAG, "Completed")
                }
                .onEach {
                    Log.d(TAG, "About to emit $it")
                }
                .map {
                    it * 2
                }
                .filter {
                    it % 3 == 0
                }
                .collect{
                Log.d(TAG, it.toString())
            }
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