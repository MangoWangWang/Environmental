//package com.tft591.tft.ui.activity.login
//
//import android.text.TextUtils
//import android.view.View
//import com.tft591.tft.R
//import com.tft591.tft.base.BaseMvpActivity
//import com.tft591.tft.ext.showToast
//import kotlinx.android.synthetic.main.activity_forget_password.*
//import kotlinx.android.synthetic.main.title_layout.*
//
//class ForgetPasswordActivity : BaseMvpActivity<ForgetPasswordContract.View, ForgetPasswordContract.Presenter>(),
//    ForgetPasswordContract.View {
//
//
//
//    private val onClickListener = View.OnClickListener { view ->
//        when (view.id) {
//            R.id.tv_get_code -> if (validate()) mPresenter?.getCode(input_phone.getInputText(), "3")
//            R.id.tv_forget_success ->submit()
//            R.id.iv_back ->finish()
//        }
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
//    override fun createPresenter(): ForgetPasswordContract.Presenter = ForgetPresenter()
//
//    override fun attachLayoutRes(): Int = R.layout.activity_forget_password
//
//
//    override fun getSuccess(data: Any) {
//        input_code.starCode()
//    }
//
//    override fun reviseSuccess() {
//        showToast("找回成功")
//        finish()
//    }
//
//    override fun initView() {
//        super.initView()
//        tv_title.text = "忘记密码"
//        iv_back.setOnClickListener(onClickListener)
//        input_code.setCodeOnclickListe(onClickListener)
//        tv_forget_success.setOnClickListener(onClickListener)
//    }
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
//     * 点击找回按钮
//     */
//    private fun submit() {
//
//       val mPhone = input_phone.getInputText()
//        if (TextUtils.isEmpty(mPhone)) {
//            showToast("请输入手机号码")
//            return
//        }
//
//        if (mPhone.length != 11) {
//            showToast("请输入11位手机号码")
//            return
//        }
//        val code = input_code.getInputText()
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
//        mPresenter?.revisePassword(mPhone,code,pwd,confirmPwd)
//    }
//
//}
