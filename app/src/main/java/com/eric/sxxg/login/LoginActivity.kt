package com.eric.sxxg.login

import android.text.TextUtils
import android.view.View
import com.eric.sxxg.BActivity
import com.eric.sxxg.R
import com.eric.sxxg.R.id.*
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.functions.Consumer
import student.eric.com.dialogutil.DialogUtil

import kotlinx.android.synthetic.main.activity_login.*
/**
 * Created by Eric on 2018/6/6.
 */
class LoginActivity : BActivity(),LoginPresenter.View,View.OnClickListener{


    override lateinit var presenter: LoginPresenter.Presenter
    lateinit var  loginImpl: LoginImpl

    override fun initView() {
        super.initView()
        setContentView(R.layout.activity_login)
    }

    override fun setData() {
        super.setData()
        loginImpl= LoginImpl(mContext, this!!.mDisposables!!,this)
    }
    override fun doLinstener() {
        super.doLinstener()
        tv_login.setOnClickListener(this)
        tv_tripartite_switch.setOnClickListener(this)
        iv_delete.setOnClickListener(this)
        mDisposables!!.add(RxTextView.textChanges(edt_password).subscribe {charSequence->
            if(charSequence.length>0){
                iv_delete.visibility= View.VISIBLE
            }else{
                iv_delete.visibility= View.GONE
            }
        })
    }
    override fun onClick(p0: View?) {
      when(p0!!.id){
       R.id.tv_login ->{
           presenter.login(edt_name.text.toString(),edt_password.text.toString())
       }
       R.id.tv_tripartite_switch ->{
           ly_tripartite_login.visibility=View.VISIBLE
       }
       R.id.iv_delete ->{
           edt_password.text.clear()
       }
       R.id.tv_register ->{}
       R.id.tv_visitor ->{}
       R.id.iv_qq ->{}
       R.id.iv_sina ->{}
       R.id.iv_weixin ->{}
      }
    }


    override fun clearPasswd() {
        edt_password.text.clear()
    }
    override fun showDialog(msg:String) {
        DialogUtil.showConfirmDialog(mContext,msg)
    }
    override fun hideDialog() {
        DialogUtil.dismisLodingDialog()
    }
    override fun goMainActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goRegisterActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goForgetPasswdActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}