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

        binding.btnStartWork.setOnClickListener {
            //create a work request that takes in the worker class
            //val workRequest = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            //OR

            val data = workDataOf("WORK MESSAGE" to "Work Completed!")
            val constraints = Constraints.Builder().setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val workRequest = OneTimeWorkRequestBuilder<SimpleWorker>().setConstraints(constraints)
                .setInputData(data).build()

            //enqueue your work request into the work manager instance
            workManager.enqueue(workRequest)
        }

        binding.btnWorkStatus.setOnClickListener {
            Snackbar.make(binding.root, "Is work completed? : ${WorkStatusSingleton.workMessage}", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnResetStatus.setOnClickListener {
            WorkStatusSingleton.isWorkComplete = false
            WorkStatusSingleton.workMessage = ""
        }

        binding.btnDoWorkOnUIThread.setOnClickListener {
            Thread.sleep(10000)
            WorkStatusSingleton.isWorkComplete = true
        }
    }
}