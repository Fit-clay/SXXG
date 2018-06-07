package com.eric.sxxg

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.eric.sxxg.manage.ActivityManager
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Eric on 2018/6/6.
 */
open class BActivity : AppCompatActivity() {

    open    lateinit var mContext :Context
    open   lateinit  var TAG :String
    open   var mDisposables: CompositeDisposable? = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext=this
        TAG=packageName
        ActivityManager.getInstance().regist(this)

        initView()
        setData()
        loadData()
        doLinstener()
    }

    open  fun doLinstener() {
    }
    open  fun setData() {
    }
    open    fun loadData() {
    }
    open fun initView() {
    }

    override fun onDestroy() {
        ActivityManager.getInstance().unregist(mContext as Activity)
        if (mDisposables != null && mDisposables!!.isDisposed()) {
            mDisposables!!.dispose()
        }

        super.onDestroy()
    }

    open fun startActivityForResult(cls: Class<*>, requestCode: Int) {
        val intent = Intent()
        intent.setClass(mContext, cls)
        startActivityForResult(intent, requestCode)
    }

}