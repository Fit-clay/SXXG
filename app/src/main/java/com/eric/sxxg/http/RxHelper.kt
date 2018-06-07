package com.eric.sxxg.http

import com.alibaba.fastjson.JSON
import com.eric.sxxg.util.LogUtils
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.IOException

/**
 * Created by Eric on 2018/6/7.
 */
class RxHelper {
    /**
     * 对服务器返回数据进行预处理
     *
     * @param
     * @return 只返回result
     */
    fun handleResult(): ObservableTransformer<ResponseBody, String> {
        return ObservableTransformer { upstream ->
            upstream.flatMap(Function<ResponseBody, ObservableSource<String>> {
                var body = ""
                var msg = ""
                try {
                    body = it.string()
                    LogUtils.logd(body)
                    val status = JSON.parseObject(body).getBoolean("success")
                    msg = JSON.parseObject(body).getString("map")
                    val result = JSON.parseObject(body).getString("errorCode")
                    return@Function if (status!!) {
                        createData(if (msg != null) msg else " ")
                    } else {
                        Observable.error(ApiExcept(result ?: "9999", ""))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    return@Function Observable.error(ApiExcept("get_body_failed", "网络异常"))
                }

            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param
     * @return
     */
    private fun createData(data: String): Observable<String> {
        return Observable.create { e ->
            try {
                e.onNext(data)
                e.onComplete()
            } catch (ex: Exception) {
                e.onError(ex)
            }
        }

    }

}