package com.mango.know.api

import com.mango.know.bean.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*


/**
 * Created by Mango
 */
interface ApiService {


    /**
     * 登录
     * http://www.wanandroid.com/user/login
     * @param username
     * @param password
     */
    @POST("index.php/mobile/login/index")
    @FormUrlEncoded
    fun loginAndroid(
        @Field("username") username: String,
        @Field("password") password: String, @Field("client") client: String = "android"
    ): Observable<HttpResult<LoginData>>


    /**
     * 获取验证码
     */
    @POST("index.php/mobile/connect/get_sms_captcha")
    @FormUrlEncoded
    fun GetCode(@Field("phone") phone: String, @Field("type") client: String): Observable<HttpResult<Any>>

    /**
     * 忘记密码
     */
    @POST("index.php/mobile/connect/find_password")
    @FormUrlEncoded
    fun revisePassword(
        @Field("phone") phone: String, @Field("captcha") captcha: String, @Field("password") password: String, @Field(
            "re_password"
        ) re_password: String, @Field("client") client: String = "android"
    ): Observable<HttpResult<Any>>


    /**
     * 注册接口
     */
    @POST("index.php/mobile/connect/sms_register")
    @FormUrlEncoded
    fun register(@FieldMap map: MutableMap<String, String>): Observable<HttpResult<Any>>

    /**
     * 获取轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("index.php/mobile/index/index")
    fun getBanners(): Observable<HttpResult<Banner>>


    /**
     * 获取个人信息
     *
     */
    @POST("index.php/mobile/member/get_member_info")
    @FormUrlEncoded
    fun getUserInfo(@Field("key") token: String): Observable<HttpResult<UserInfo>>


    /**
     * 获取资讯
     *
     */
    @GET("index.php/mobile/index/get_inforbg_list")
    fun getZhiXun(): Observable<HttpResult<ZhiXunBg>>


    /**
     * 修改密码
     *
     */
    @POST("index.php/mobile/member/update_loginpwd")
    @FormUrlEncoded
    fun changeLoginPw(@FieldMap map: MutableMap<String, String>): Observable<HttpResult<Any>>

    /**
     * 意见反馈
     *
     */
    @POST("index.php/mobile/memberfeedback/feedback_add")
    @FormUrlEncoded
    fun feedback_add(@FieldMap map: MutableMap<String, String>): Observable<HttpResult<Any>>

    /**
     *  获取手机验证码
    String getMobileCode = serverHead + "mobile/connect/get_sms_captcha";
     *
     */
    @POST("index.php/mobile/connect/get_sms_captcha")
    @FormUrlEncoded
    fun get_sms_captcha(@FieldMap map: MutableMap<String, String>): Observable<HttpResult<Any>>


    /**
     *  校验手机验证码 "mobile/connect/get_sms_captcha";
     *
     */
    @POST("index.php/mobile/connect/check_sms_captcha")
    @FormUrlEncoded
    fun check_sms_captcha(@FieldMap map: MutableMap<String, String>): Observable<HttpResult<Any>>

    /**
     *  修改支付密码 "mobile/member/update_paypwd";
     *
     */
    @POST("index.php/mobile/member/update_paypwd")
    @FormUrlEncoded
    fun update_paypwd(@FieldMap map: MutableMap<String, String>): Observable<HttpResult<Any>>





    //单张图片上传
    @Multipart
    @POST("index.php/mobile/member/img_upload")
    fun updateImage(@QueryMap map: MutableMap<String, String>, @Part file: MultipartBody.Part): Observable<HttpResult<Any>>


    //单张图片上传
    @POST("index.php/mobile/member/edit_member_info")
    @FormUrlEncoded
    fun edit_member_info(@FieldMap map: MutableMap<String, String>): Observable<HttpResult<Any>>

//    /**
//     *  修改支付密码 "mobile/member/update_paypwd";
//     *
//     */
//    @POST("index.php/mobile/member_bank/index")
//    @FormUrlEncoded
//    fun get_member_bank(@FieldMap map: MutableMap<String, String>): Observable<HttpResult<List<BankCardBean>>>
}

