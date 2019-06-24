//package com.tft591.tft.ui.activity.login
//import android.text.TextUtils
//import android.view.View
//import com.tft591.tft.R
//import com.tft591.tft.api.WebApis
//import com.tft591.tft.app.App
//import com.tft591.tft.base.BaseMvpActivity
//import com.tft591.tft.ext.showToast
//import com.tft591.tft.ui.activity.web.WebViewActivity
//import kotlinx.android.synthetic.main.activity_register.*
//import kotlinx.android.synthetic.main.title_layout.*
//import java.util.HashMap
//
//class RegisterActivity : BaseMvpActivity<RegisterContract.View, RegisterContract.Presenter>(),RegisterContract.View {
//    override fun getSuccess(data: Any) {
//        showToast("获取验证码成功")
//        input_code.starCode()
//    }
//
//    override fun initData() {
//
//    }
//
//    override fun start() {
//
//    }
//
//    override fun registerSuccess() {
//        showToast("注册成功")
//        ((App.instance) as App).finishTopActivity(2)
//    }
//
//    override fun attachLayoutRes(): Int  = R.layout.activity_register
//
//    override fun createPresenter(): RegisterContract.Presenter = RegisterPresenter()
//
//    override fun initView() {
//        super.initView()
//        tv_title.text = "注册"
//        iv_back.setOnClickListener(onClickListener)
//        input_code.setCodeOnclickListe(onClickListener)
//        tv_register.setOnClickListener(onClickListener)
//        tv_register_agree.setOnClickListener(onClickListener)
//    }
//
//
//    private val onClickListener = View.OnClickListener { view ->
//        when (view.id) {
//            R.id.tv_get_code -> if (validate()) mPresenter?.getCode(input_phone.getInputText(), "1")
//            R.id.iv_back ->finish()
//            R.id.tv_register-> register()
//            R.id.tv_register_agree -> WebViewActivity.startActivity(this,WebApis.Agreement)
//        }
//    }
//
//
//    /**
//     * Check UserName and PassWord
//     */
//    fun validate(): Boolean {
//        var valid = true
//        val phone: String = input_phone.getInputText()
//
//        if (phone.isEmpty()) {
//            valid = false
//            showToast("请先输入手机号码")
//        }
//        return valid
//
//    }
//
//    /**
//     * 点击注册按钮
//     */
//    private fun register() {
//        val code = input_code.getInputText()
//       val mPhone = input_phone.getInputText()
//        if (TextUtils.isEmpty(mPhone)) {
//            showToast("请输入手机号码")
//            return
//        }
//        if (TextUtils.isEmpty(code)) {
//            showToast("请输入验证码")
//            return
//        }
//        val pwd = input_password.getInputText()
//        if (TextUtils.isEmpty(pwd)) {
//            showToast("请输入密码")
//            return
//        }
//        if (pwd.length < 6 || pwd.length > 16) {
//            showToast("请输入6-16位长度的密码")
//            return
//        }
//        val confirmPwd = input_comfit_password.getInputText()
//        if (pwd != confirmPwd) {
//            showToast("两次输入密码不一致，请重新输入")
//            return
//        }
//
//        val PayPwd = input_pay_password.getInputText()
//        if (TextUtils.isEmpty(PayPwd)) {
//            showToast("请输入支付密码")
//            return
//        }
//        if (PayPwd.length < 6) {
//            showToast("请输入六位的支付密码")
//            return
//        }
//
//        val PayconfirmPwd = input_comfit_pay_password.getInputText()
//        if (PayPwd != PayconfirmPwd) {
//            showToast("两次输入支付密码不一致，请重新输入")
//            return
//        }
//
//        val inviteCode = input_invite.getInputText()
//        if (TextUtils.isEmpty(inviteCode)) {
//            showToast("请输入邀请码")
//            return
//        }
//
//        val params = HashMap<String, String>()
//        params["phone"] = mPhone
//        params["password"] = pwd
//        params["re_password"] = confirmPwd
//        params["captcha"] = code
//        params["inviter_id"] = inviteCode
//        params["paypwd"] = PayPwd
//        params["re_paypwd"] = PayconfirmPwd
//        params["client"] = "android"
//        mPresenter?.register(params)
//    }
//}
