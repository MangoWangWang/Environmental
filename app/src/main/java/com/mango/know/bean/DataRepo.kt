package com.mango.know.bean

import com.squareup.moshi.Json

/**
 * Created by chenxz on 2018/4/21.
 */
//data class HttpResult<T>(@Json(name = "data") val data: T,
//                         @Json(name = "errorCode") val errorCode: Int,
//                         @Json(name = "errorMsg") val errorMsg: String)

data class HttpResult<T>(
    @Json(name = "result") val result: T
) : BaseBean()


// 首页轮播图
data class Banner(
    val adv_list: List<Adv>
)

data class Adv(
    val adv_code: String,
    val adv_id: String,
    val adv_title: String,
    val article_id: String,
    val type: String
)


// 登录数据
data class LoginData(
    @Json(name = "key") val key: String,
    @Json(name = "push_id") val push_id: String,
    @Json(name = "chat_id") val chat_id: String,
    @Json(name = "chat_pwd") val chat_pwd: String,
    @Json(name = "username") val username: String
)

// 首页收益
data class TodayEarnings(
    val today_info: TodayInfo,
    val top_info: TopInfo
)

data class TodayInfo(
    val activation_count: String,
    val earnings_amount: String,
    val trading_amount: String
)

data class TopInfo(
    val activation: Int,
    val trading: Int
)

// 用户个人信息
data class UserInfo(
    val evolution_address: String,
    val evolution_city: String,
    val evolution_city_id: Int,
    val idcard: String,
    val is_approve: Int,
    val is_bank: Int,
    val is_inviter: Int,
    val member_avatar: String,
    val member_id: Int,
    val member_level: Int,
    val member_mobile: String,
    val member_name: String,
    val push_id: String,
    val truename: String,
    val sex: String,
    val age: String
)

// 二维码信息
data class QrInfoBean(
    val img_bg_list: List<ImgBg>,
    val info: Info
)

data class Info(
    val download_url: String,
    val html_url: String,
    val inviter_code: String,
    val member_avatar: String,
    val member_id: Int,
    val member_name: String,
    val qrcode_url: String
)

data class ImgBg(
    val adv_code: String,
    val adv_id: String,
    val adv_title: String,
    val article_id: String,
    val type: String
)

data class ZhiXunBg(
    val adv_code: String,
    val adv_id: String,
    val adv_title: String
)

data class EaringDetailBean(
    val income: String,
    val spending: String
)

data class EarningDetailInfo(
    val lg_addtime: String,
    val lg_av_amount: String,
    val lg_desc: String,
    val lg_id: Int,
    val lg_type: String
)

data class TodayInfoBean(
    val group: String,
    val personal: String,
    val total: String
)

data class ProgressBean(
    val progress: Int,
    val name: String,
    val number: String,
    val position:Int

)


data class LowerBean(
    val member_avatar: String,
    val member_id: String,
    val member_mobile: String,
    val member_name: String
)
data class RateInfo(
    val admin_D0: String,
    val admin_T0: String,
    val admin_T1: String,
    val admin_top_share: String,
    val admin_top_share_add: String,
    val bankCard_admin_share: String,
    val bankCard_rate_share: String,
    val checkOut_d0_rate_add: Any,
    val checkOut_t0_rate_add: Any,
    val checkOut_t1_rate_add: Any,
    val cloud_admin_share: String,
    val cloud_rate_share: String,
    val gc_id: Int,
    val gc_name: String,
    val goods_id: Int,
    val goods_name: String,
    val goods_serial: String,
    val id: Int,
    val lineCard_admin_share: String,
    val lineCard_rate_share: String,
    val member_id: Int,
    val quickPay_admin_share: String,
    val quickPay_rate_share: String,
    val scaveCode_admin_share: String,
    val scaveCode_rate_share: String,
    val sn_code: String,
    val lineFull_refund_open : Int,
    val show_refund_open : Int,
    val activate_rewards : Int
)


data class ShopBanner(
    val adv_bgcolor: Any,
    val adv_clicknum: Int,
    val adv_code: String,
    val adv_enabled: Int,
    val adv_enddate: Int,
    val adv_id: Int,
    val adv_infor_id: Int,
    val adv_link: String,
    val adv_sort: Int,
    val adv_startdate: Int,
    val adv_title: String,
    val ap_height: Int,
    val ap_id: Int,
    val ap_intro: String,
    val ap_isuse: Int,
    val ap_name: String,
    val ap_width: Int
)

data class GongGaoBean(
    val content: String,
    val created_time: String,
    val id: Int,
    val title: String
)

data class WechatpayBean(
    val appid: String,
    val mch_id: String,
    val nonce_str: String,
    val `package`: String,
    val prepay_id: String,
    val result_code: String,
    val return_code: String,
    val return_msg: String,
    val sign: String,
    val timestamp: Int,
    val trade_type: String
)

data class AlipayBean(
    val content: String
)






