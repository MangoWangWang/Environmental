package com.mango.know.api


/**
 * Created by FrankZhang on 2017/11/14.
 */

interface WebApis {
    companion object {
        val serverHead = "dist/index.html#"    //域名

        // 资讯详情
        val shopping_details = "$serverHead/shopping/shopping_details"
        //  商城商品点击进
        val AccessNetwork = "$serverHead/home/AccessNetworkList"
        // 关于页面
        val about = "$serverHead/AboutUs"
        // 用户协议
        val Agreement =  "$serverHead/Agreement?isAgreement=true"

    }
}
