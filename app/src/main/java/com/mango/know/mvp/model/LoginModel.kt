package com.mango.know.mvp.model


import com.mango.know.base.BaseModel
import com.mango.know.http.RetrofitHelper
import com.mango.know.mvp.contract.LoginContract
import com.mango.know.bean.HttpResult
import com.mango.know.bean.LoginData
import io.reactivex.Observable

/**
 * Created by chenxz on 2018/5/27.
 */
class LoginModel : BaseModel(), LoginContract.Model {

    override fun loginAndroid(username: String, password: String): Observable<HttpResult<LoginData>> {
        return RetrofitHelper.service.loginAndroid(username,password)
    }
}