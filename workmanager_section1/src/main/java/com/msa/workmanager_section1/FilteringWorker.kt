package com.msa.workmanager_section1

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
/**
 * Created by Ali Soleimani on 08,May,2022
 * Github https://github.com/ALISCHILLER
 * in Iran.
 */
class FilteringWorker(context: Context,params:WorkerParameters) : Worker(context,params) {


    override fun doWork(): Result {
        try {

            for (i in 0 ..3000) {
                Log.i("MYTAG", "Filtering $i")
            }

            return Result.success()
        } catch (e:Exception){
            return Result.failure()
        }
    }
}