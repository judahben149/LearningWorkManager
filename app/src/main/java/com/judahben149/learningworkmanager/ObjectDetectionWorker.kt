package com.judahben149.learningworkmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class ObjectDetectionWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    override fun doWork(): Result {
        Thread.sleep(1000)
        println(this::class.java.name)

        val success = inputData.getBoolean("SUCCESS", false)
        val name = inputData.getString("NAME")

        return if (success) {
            if (name != null){
                println("$name success")
            }

            val (colour, product) = ProductDatabase.products[Random.nextInt(0, ProductDatabase.products.size)]
            println("Detected $colour $product")
            Result.success()
        } else {
            if (name!= null) {
                print("$name failure")
            }
            Result.failure()
        }
    }
}