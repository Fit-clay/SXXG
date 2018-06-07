package com.eric.sxxg

import android.app.Application
import android.content.Context
import com.eric.sxxg.http.HttpHelper

/**
 * Created by Eric on 2018/6/7.
 */
class JxxgApplication : Application() {

    companion object {
        private var instance:Application?=null;
        fun getintent()= instance!!;
    }

   override fun onCreate() {
        super.onCreate()
       instance=this
    }


}