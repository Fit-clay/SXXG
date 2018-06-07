package com.eric.sxxg.http

import android.content.Context
import com.eric.sxxg.JxxgApplication
import com.eric.sxxg.util.LogUtils
import com.eric.sxxg.util.NetWorkUtils
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.text.ParseException
import java.util.*

/**
 * Created by Eric on 2018/6/7.
 */
abstract class MySubscriber<T>(var context:Context, var mDisposables: CompositeDisposable) : Observer<T> {

    //对应HTTP的状态码
    private val UNAUTHORIZED = 401
    private val FORBIDDEN = 403
    private val NOT_FOUND = 404
    private val REQUEST_TIMEOUT = 408
    private val INTERNAL_SERVER_ERROR = 500
    private val BAD_GATEWAY = 502
    private val SERVICE_UNAVAILABLE = 503
    private val GATEWAY_TIMEOUT = 504



    override fun onSubscribe(d: Disposable) {
        mDisposables!!.add(d)
    }

    override fun onNext(t: T) {
        _onNext(t)
    }

    override fun onError(e: Throwable) {
        LogUtils.logd("==========serverException===========")
        //网络
        if (!NetWorkUtils.isNetConnected(JxxgApplication.getintent())) { //没有网络
            _onError("网络不可用")
        } else if (e is HttpException) { //HTTP 错误
            when (e.code()) {
                UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> _onError("网络错误")
                else -> _onError("网络错误")
            }
        } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException) {
            _onError("解析错误")
        } else if (e is ConnectException) {
            _onError("连接失败")
        } else if (e is ApiExcept) {
            val serverException = e
            _onError(e.getStatus())
        } else {
            _onError("未知错误" + e.message)
        }
    }
    /**
     * 错误回调
     */
    protected  abstract fun _onError(msg: String)

    protected abstract fun _onNext(t: T)

}