package com.kotlin.siddhant.androidexecutorexample

/**
 * Created by Siddhant on 22/03/18.
 */
public class Contract
{
    public interface View
    {
        public fun initView()
    }

    public interface Presenter
    {
        public fun onClick()
    }


}