package com.eric.sxxg.http

import android.content.Context
import com.eric.sxxg.util.UrlConfig
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Eric on 2018/6/7.
 */
class HttpHelper {

    //读超时长，单位：毫秒
    val READ_TIME_OUT = 10000
    //连接时长，单位：毫秒
    val CONNECT_TIME_OUT = 10000
    lateinit var retrofit: Retrofit
    lateinit var movieService: ApiService

    companion object  {
         var token: String? = null
         var _instance: HttpHelper? = null
        @Synchronized
        fun getInstance(): HttpHelper {
            if (_instance == null) {
                _instance = HttpHelper()

            }
            //初始化默认信息
            return _instance as HttpHelper
        }
    }
    init {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(logInterceptor)
                .build()
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(UrlConfig.PRODUCT)
                .build()
        movieService = retrofit.create(ApiService::class.java)
    }




    fun login(name:String,password :String): Observable<ResponseBody> {
        return movieService.login(name,password)
    }


}