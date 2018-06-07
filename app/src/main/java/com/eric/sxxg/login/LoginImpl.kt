package com.eric.sxxg.login

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.text.TextUtils
import com.eric.sxxg.R.id.edt_name
import com.eric.sxxg.R.id.edt_password
import com.eric.sxxg.http.HttpHelper
import com.eric.sxxg.http.MySubscriber
import com.eric.sxxg.http.RxHelper
import com.eric.sxxg.util.JSONUtil
import com.eric.sxxg.util.LogUtils
import com.orhanobut.logger.Logger.init
import io.reactivex.disposables.CompositeDisposable
import student.eric.com.dialogutil.DialogUtil

/**
 * Created by Eric on 2018/6/7.
 */
class LoginImpl(var mContext: Context,var mDisposables: CompositeDisposable, val loginView: LoginPresenter.View) :LoginPresenter.Presenter{

    init {
            loginView.presenter=this
    }
    override fun start() {
    }
    override fun login(userName: String, passWorld: String) {
        if (TextUtils.isEmpty(userName)){
            loginView.showDialog("用户名不能为空")
            return
        }else if(TextUtils.isEmpty(passWorld)||passWorld.length<6){
            loginView.showDialog("密码不能小于6位数")
            return
        }else{
            loginView.showDialog("登陆成功")
           /* HttpHelper.getInstance().login(userName,passWorld).compose(RxHelper().handleResult()).subscribe(object : MySubscriber<String>(mContext, mDisposables) {
                 override fun _onError(msg: String) {
                }
                 override fun _onNext(s: String) {
//                     loginView.goMainActivity()
                     loginView.showDialog("登陆成功")
                }
                override fun onComplete() {
                }
            });*/

        }
    }
    override fun forgetPasswd() {
    }
    override fun visitorLogin() {
    }
    override fun tripartite() {
    }
    override fun register() {
    }
}