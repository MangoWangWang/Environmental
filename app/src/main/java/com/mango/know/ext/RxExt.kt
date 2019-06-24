package com.mango.know.ext


import com.mango.know.R
import com.mango.know.app.App
import com.mango.know.base.IModel
import com.mango.know.base.IView
import com.mango.know.http.exception.ErrorStatus
import com.mango.know.http.exception.ExceptionHandle
import com.mango.know.http.function.RetryWithDelay
import com.mango.know.bean.BaseBean
import com.mango.know.utils.NetWorkUtil
import com.mango.know.rx.SchedulerUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
/**
 * @author chenxz
 * @date 2018/11/22
 * @desc
 */

fun <T : BaseBean> Observable<T>.ss(
    model: IModel?,
    view: IView?,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit
) {
    this.compose(SchedulerUtils.ioToMain())
        .retryWhen(RetryWithDelay())
        .subscribe(object : Observer<T> {
            override fun onComplete() {
                view?.hideLoading()
            }
            override fun onSubscribe(d: Disposable) {
                if (isShowLoading) {
                    view?.showLoading()
                }
                model?.addDisposable(d)
                if (!NetWorkUtil.isNetworkConnected(App.instance)) {
                    view?.showDefaultMsg(App.instance.resources.getString(R.string.network_tip))
                    onComplete()
                }
            }

            override fun onNext(t: T) {
                when {
                    t.code == ErrorStatus.SUCCESS -> onSuccess.invoke(t)
                    t.code == ErrorStatus.TOKEN_INVAILD -> {
                        // Token 过期，重新登录
                        view?.showDefaultMsg(t.message)
                    }
                    else -> view?.showDefaultMsg(t.message)
                }
            }

            override fun onError(t: Throwable) {
                if (t is ResultException) {
                    //自定义的ResultException
                    view?.hideLoading()
                    view?.showDefaultMsg(t.errorMessage.toString())
                }else
                {
                    loge(t.toString())
                    view?.hideLoading()
                    view?.showError(ExceptionHandle.handleException(t))
                }
            }
        })
}

fun <T : BaseBean> Observable<T>.sss(
    view: IView?,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit
): Disposable {
    if (isShowLoading) {
        view?.showLoading()
    }
    return this.compose(SchedulerUtils.ioToMain())
        .retryWhen(RetryWithDelay())
        .subscribe({
            when {
                it.code == ErrorStatus.SUCCESS -> onSuccess.invoke(it)
                it.code == ErrorStatus.TOKEN_INVAILD -> {
                    // Token 过期，重新登录
                }
                else -> view?.showDefaultMsg(it.message)
            }
            view?.hideLoading()
        }, {
            if (it is ResultException) {
                //自定义的ResultException
                view?.hideLoading()

                view?.showDefaultMsg(it.errorMessage.toString())
            }else
            {
                loge(it.toString())
                view?.hideLoading()
                view?.showError(ExceptionHandle.handleException(it))
            }
        })
}

