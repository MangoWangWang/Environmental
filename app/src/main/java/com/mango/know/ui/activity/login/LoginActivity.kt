package com.mango.know.ui.activity.login
import android.os.Handler
import android.os.Process
import android.support.design.widget.Snackbar
import android.view.View
import com.mango.know.R
import com.mango.know.app.App
import com.mango.know.base.BaseMvpActivity
import com.mango.know.bean.LoginData
import com.mango.know.constant.Constant
import com.mango.know.ext.showToast
import com.mango.know.mvp.contract.LoginContract
import com.mango.know.mvp.presenter.LoginPresenter
import com.mango.know.utils.DialogUtil
import com.mango.know.utils.DoubleClickExit
import com.mango.know.utils.Preference
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMvpActivity<LoginContract.View, LoginContract.Presenter>(), LoginContract.View {


    /**
     * local username
     */
    private var user: String by Preference(Constant.USERNAME_KEY, "")

    /**
     * local password
     */
    private var pwd: String by Preference(Constant.PASSWORD_KEY, "")

    /**
     * push_id
     */
    private var push_id: String by Preference(Constant.PUSH_ID, "")


    override fun createPresenter(): LoginContract.Presenter = LoginPresenter()

    override fun attachLayoutRes(): Int = R.layout.activity_login

    private val onClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.tv_login_login -> login()
//            R.id.tv_login_register -> startActivity(RegisterTypeActivity::class.java)
//            R.id.tv_login_forget_password -> startActivity(ForgetPasswordActivity::class.java)
        }
    }

    private val mDialog by lazy {
        DialogUtil.getWaitDialog(this, getString(R.string.login_ing))
    }

    override fun showLoading() {
        mDialog.show()
    }

    override fun hideLoading() {
        mDialog.dismiss()
    }

    override fun useEventBus(): Boolean = false

    override fun enableNetworkTip(): Boolean = false

    private fun login() {
        if (validate()) {
            mPresenter?.loginAndroid(et_login_phone.text.toString(),et_login_password.text.toString())
        }
    }


    override fun initData() {

    }

    override fun start() {

    }


    override fun initView() {
        super.initView()
        tv_login_login.setOnClickListener(onClickListener)
        tv_login_register.setOnClickListener(onClickListener)
        tv_login_forget_password.setOnClickListener(onClickListener)
    }


    override fun loginSuccess(data: LoginData) {
        showToast(getString(R.string.login_success))
        isLogin = true
        user = data.username
        pwd = et_login_password.text.toString()
        token = data.key
        push_id = data.push_id
//        startActivity(MainActivity::class.java)
        finish()
    }

    override fun loginFail() {

    }


    /**
     * Check UserName and PassWord
     */
    private fun validate(): Boolean {
        var valid = true
        val username: String = et_login_phone.text.toString()
        val password: String = et_login_password.text.toString()

        if (username.isEmpty()) {
            valid = false
        }
        if (password.isEmpty()) {
            valid = false
        }
        return valid

    }

    override fun onBackPressed() {
        if (!DoubleClickExit.check()) {
            Snackbar.make(
                this.window.decorView.findViewById(android.R.id.content),
                "再按一次退出 App!",
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            super.onBackPressed()
            val app = App.instance as App
            app.finishAllActivity()
            Handler().postDelayed({
                Process.killProcess(Process.myPid())
                System.exit(0)
                System.gc()
            }, 500)
        }
    }


}
