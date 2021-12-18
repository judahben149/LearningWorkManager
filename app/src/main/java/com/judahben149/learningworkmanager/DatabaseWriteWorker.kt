package com.judahben149.learningworkmanager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class DatabaseWriteWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    override fun doWork(): ListenableWorker.Result {
        Thread.sleep(1000)
        println(this::class.java.name)

        val success = inputData.getBoolean("SUCCESS", false)
        val name = inputData.getString("NAME")

        return if (success) {
            if (name != null){
                println("$name success")
            }
            println("Stored recommendation")
            Result.success()
        } else {
            if (name!= null) {
                print("$name failure")
            }
            Result.failure()
        }
    }
}