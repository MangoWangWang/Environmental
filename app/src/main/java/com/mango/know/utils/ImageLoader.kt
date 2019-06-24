package com.mango.know.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.mango.know.R
import com.mango.know.app.App

/**
 * Created by chenxz on 2018/6/12.
 */
object ImageLoader {

//    // 1.开启无图模式 2.非WiFi环境 不加载图片
//    private val isLoadImage = !SettingUtil.getIsNoPhotoMode() || NetWorkUtil.isWifi(App.context)

    /**
     * 加载图片
     * @param context
     * @param url
     * @param iv
     */
    fun load( url: String?, iv: ImageView?) {
        iv?.apply {
            Glide.with(App.context).clear(iv)
            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.mipmap.rectangle_default_image)
            Glide.with(context!!)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(1500))
                .apply(options)
                .into(iv)
        }
    }


    /**
     * 加载url图片
     *
     * @param imageView
     * @param url
     */
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(App.context)
            .load(url)
            .apply(RequestOptions().error(R.mipmap.rectangle_default_image))
            .transition(DrawableTransitionOptions.withCrossFade(1500)) // 4.0 淡入淡出效果
            .into(imageView)
    }


    /**
     * 加载url图片
     *
     * @param imageView
     * @param url
     */
    fun loadFitXYImage(imageView: ImageView, url: String) {
        Glide.with(App.context)
            .load(url)
            .apply(RequestOptions().error(R.mipmap.rectangle_default_image).fitCenter())
            .transition(DrawableTransitionOptions.withCrossFade(1500)) // 4.0 淡入淡出效果
            .into(imageView)
    }


    //备用 后台头像地址不改变（只做本人头像显示）
    fun loadAvatar(url: String,imageView: ImageView) {
        val options = RequestOptions()
            .placeholder(R.mipmap.oval_default_image)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .circleCrop()   //4.0版本圆形
        Glide.with(App.context)
            .load(url)
            .apply(options)
            .into(imageView)
    }


}