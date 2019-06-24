package com.mango.know.mvp.presenter

import com.mango.know.base.BasePresenter
import com.mango.know.ext.ss
import com.mango.know.mvp.contract.LoginContract
import com.mango.know.mvp.model.LoginModel


/**
 * Created by chenxz on 2018/5/27.
 */
class LoginPresenter : BasePresenter<LoginContract.Model, LoginContract.View>(), LoginContract.Presenter {

    override fun createModel(): LoginContract.Model? = LoginModel()
    override fun loginAndroid(username: String, password: String) {
        mModel?.loginAndroid(username, password)?.ss(mModel, mView) {
            mView?.loginSuccess(it.result)
        }
    }

}