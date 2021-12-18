package com.judahben149.learningworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.google.android.material.snackbar.Snackbar
import com.judahben149.learningworkmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //get an instance of the work manager
    //this uses a context that lives through the application
    val workManager = WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSingleChainSucceed.setOnClickListener {
            val objectDetectionWorkRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            val networkRequestWorkRequest = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            val databaseWriteWorkRequest = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(objectDetectionWorkRequest)
                .then(networkRequestWorkRequest)
                .then(databaseWriteWorkRequest)
                .enqueue()
        }

        binding.btnSingleChainFail.setOnClickListener {
            val objectDetectionWorkRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            val networkRequestWorkRequest = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to false))
                .build()

            val databaseWriteWorkRequest = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(objectDetectionWorkRequest)
                .then(networkRequestWorkRequest)
                .then(databaseWriteWorkRequest)
                .enqueue()
        }

        binding.btnGroupChainSucceed.setOnClickListener {
            val objectDetectionWorkRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            val objectDetectionWorkRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            val networkRequestWorkRequest = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            val databaseWriteWorkRequest = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(listOf(objectDetectionWorkRequest, objectDetectionWorkRequest2))
                .then(networkRequestWorkRequest)
                .then(databaseWriteWorkRequest)
                .enqueue()
        }

        binding.btnGroupChainFail.setOnClickListener {
            val objectDetectionWorkRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            val objectDetectionWorkRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to false))
                .build()

            val networkRequestWorkRequest = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            val databaseWriteWorkRequest = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(listOf(objectDetectionWorkRequest, objectDetectionWorkRequest2))
                .then(networkRequestWorkRequest)
                .then(databaseWriteWorkRequest)
                .enqueue()
        }

        binding.btnMultipleChainSucceed.setOnClickListener {
            val objectDetectionWorkRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()

            val objectDetectionWorkRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()

            val networkRequestWorkRequest = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()

            val networkRequestWorkRequest2 = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()

            val databaseWriteWorkRequest = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()

            val databaseWriteWorkRequest2 = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()

            val recommendation1 = workManager.beginWith(objectDetectionWorkRequest)
                .then(networkRequestWorkRequest)
                .then(databaseWriteWorkRequest)

            val recommendation2 = workManager.beginWith(objectDetectionWorkRequest2)
                .then(networkRequestWorkRequest2)
                .then(databaseWriteWorkRequest2)

            val root = WorkContinuation.combine(listOf(recommendation1, recommendation2))
            root.enqueue()
        }

        binding.btnMultipleChainFail.setOnClickListener {
            val objectDetectionWorkRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()

            val objectDetectionWorkRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()

            val networkRequestWorkRequest = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()

            val networkRequestWorkRequest2 = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to false, "NAME" to "TWO"))
                .build()

            val databaseWriteWorkRequest = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()

            val databaseWriteWorkRequest2 = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()


            val recommendation1 = workManager.beginWith(objectDetectionWorkRequest)
                .then(networkRequestWorkRequest)
                .then(databaseWriteWorkRequest)

            val recommendation2 = workManager.beginWith(objectDetectionWorkRequest2)
                .then(networkRequestWorkRequest2)
                .then(databaseWriteWorkRequest2)

            val root = WorkContinuation.combine(listOf(recommendation1, recommendation2))
            root.enqueue()
        }
    }
}