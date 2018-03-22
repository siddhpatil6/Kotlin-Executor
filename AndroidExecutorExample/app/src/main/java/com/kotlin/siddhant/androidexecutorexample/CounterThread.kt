package com.kotlin.siddhant.androidexecutorexample

import android.app.Activity
import android.widget.ProgressBar

/**
 * Created by Siddhant on 22/03/18.
 */
public class CounterThread(progressBar: ProgressBar,activity:Activity):Thread()
{
    var progressBar=progressBar
    var activity=activity


    override fun run() {
        super.run()
        for (progress in 0..100 step 10) // step is use to increase count by 10 (it shows jump)
        {

            (activity as ExecutorActivity).runOnUiThread {
               progressBar.setProgress(progress) // here we set progress android
             }
            Thread.sleep(1000)
        }

    }
}