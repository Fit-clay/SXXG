package com.eric.sxxg.login

import com.eric.sxxg.BasePresenter
import com.eric.sxxg.BaseView

/**
 * Created by Eric on 2018/6/7.
 */
interface LoginPresenter {

    interface View : BaseView<Presenter> {
        fun clearPasswd()
        fun showDialog(msg:String)
        fun hideDialog()
        fun goMainActivity()
        fun goRegisterActivity()
        fun goForgetPasswdActivity()
    }

    interface Presenter  : BasePresenter {
        fun login(userName: String, passWorld: String)
        fun forgetPasswd()
        fun visitorLogin()
        fun tripartite()
        fun register()

    }
}