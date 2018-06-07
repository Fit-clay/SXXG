package com.eric.sxxg.http

/**
 * Created by Eric on 2018/6/7.
 */
class ApiExcept( status:String, msg:String) : Exception() {

    private var status: String = ""
    private var msg: String = ""

    init {
       this.status = status
        this.msg = msg
    }
    fun getStatus(): String {
        return status
    }
    fun getMsg(): String {
        return msg
    }
}