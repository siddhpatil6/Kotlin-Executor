package com.kotlin.siddhant.androidexecutorexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_executor.*

class ExecutorActivity : AppCompatActivity(),Contract.View, View.OnClickListener {


    var progressBar:ProgressBar?=null
    var progressBar2:ProgressBar?=null
    var progressBar3:ProgressBar?=null
    var progressBar4:ProgressBar?=null
    var progressBar5:ProgressBar?=null
    var btnStart:Button?=null

    var presenter:ExecutorPresentor?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_executor)
        presenter= ExecutorPresentor(this)
    }
    // Progressbar initialization
    override fun initView() {
        progressBar=findViewById(R.id.progressBar)
        progressBar2=findViewById(R.id.progressBar2)
        progressBar3=findViewById(R.id.progressBar3)
        progressBar4=findViewById(R.id.progressBar4)
        progressBar5=findViewById(R.id.progressBar5)
        btnStart=findViewById(R.id.btnStart)
        btnStart?.setOnClickListener(this)
    }


    //on click of btnStart to perform action on click
    override fun onClick(view: View?)
    {
        when(view?.id)
        {
            R.id.btnStart ->
                presenter?.onClick()

        }
    }


}
