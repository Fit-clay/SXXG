package com.eric.sxxg.http

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Eric on 2018/6/7.
 */
interface ApiService {
    @FormUrlEncoded
    @POST("/jsxu/login")
     fun login(
            @Field("userName") version: String,
            @Field("passworld") channel: String): Observable<ResponseBody>
}