package com.kotlin.siddhant.androidexecutorexample

import android.app.Activity
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Siddhant on 22/03/18.
 */
public class ExecutorPresentor(activity:Activity):Contract.Presenter
{
    var activity=activity as ExecutorActivity
    init {
        (activity as ExecutorActivity)?.initView()
    }
    override fun onClick()
    {
        activity?.initView()
        var executor=Executors.newScheduledThreadPool(2)
        var counterThread=CounterThread(activity.progressBar!!,activity)
        var counterThread2=CounterThread(activity.progressBar2!!,activity)
        var counterThread3=CounterThread(activity.progressBar3!!,activity)
        var counterThread4=CounterThread(activity.progressBar4!!,activity)
        var counterThread5=CounterThread(activity.progressBar5!!,activity)

        executor.execute(counterThread)
        executor.execute(counterThread2)
        executor.execute(counterThread3)
        executor.execute(counterThread4)
        executor.execute(counterThread5)
    }

}