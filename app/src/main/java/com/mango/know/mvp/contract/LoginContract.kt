package com.mango.know.mvp.contract


import com.mango.know.base.IModel
import com.mango.know.base.IPresenter
import com.mango.know.base.IView
import com.mango.know.bean.HttpResult
import com.mango.know.bean.LoginData
import io.reactivex.Observable

/**
 * Created by chenxz on 2018/5/27.
 */
interface LoginContract {

    interface View : IView {

        fun loginSuccess(data: LoginData)

        fun loginFail()

    }

    interface Presenter : IPresenter<View> {

        fun loginAndroid(username: String, password: String)

    }

    interface Model : IModel {

        fun loginAndroid(username: String, password: String): Observable<HttpResult<LoginData>>

    }

}