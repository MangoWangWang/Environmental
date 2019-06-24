//package com.tft591.tft.adapter
//
//import android.content.Context
//import android.graphics.Color
//import android.widget.ImageView
//import android.widget.TextView
//import com.chad.library.adapter.base.BaseQuickAdapter
//import com.chad.library.adapter.base.BaseViewHolder
//import com.tft591.tft.R
//import com.tft591.tft.utils.ImageLoader
//
///**
// * Created by chenxz on 2018/4/22.
// */
//class ActMachineAdapter(private val context: Context?, datas: MutableList<ActPartnerMachineBean.ListBean>) :
//    BaseQuickAdapter<ActPartnerMachineBean.ListBean, BaseViewHolder>(R.layout.item_machine, datas) {
//    override fun convert(helper: BaseViewHolder?, item: ActPartnerMachineBean.ListBean?) {
//        item ?: return
//        helper ?: return
//        ImageLoader.load(item.goods_image, helper.getView(R.id.iv_good_icon))
//        helper.setText(R.id.tv_good_name, item.goods_name + item.goods_serial)
//            .setText(R.id.tv_sn_number, item.sn_code)
//            .setText(R.id.tv_price, "¥" + item.goods_price)
//        if (item.is_activation == 1) {
//            helper.getView<TextView>(R.id.tv_action).text = "满返考核时间："
//            helper.getView<TextView>(R.id.tv_time).text = item.assess_time
//        }
//
//        when (item.assess_state) {
//            "0" -> {
//                helper.getView<TextView>(R.id.tvTimeStatue).text = "安全期"
//                helper.getView<ImageView>(R.id.ivTimeStatue).setBackgroundResource(R.mipmap.green_danger)
//                helper.getView<TextView>(R.id.tvTimeStatue).setTextColor(Color.parseColor("#00692F"))
//            }
//            "1" -> {
//                helper.getView<TextView>(R.id.tvTimeStatue).text = "将到期"
//                helper.getView<ImageView>(R.id.ivTimeStatue).setBackgroundResource(R.mipmap.red_danger)
//                helper.getView<TextView>(R.id.tvTimeStatue).setTextColor(Color.parseColor("#E26955"))
//            }
//            "2" -> {
//                helper.getView<TextView>(R.id.tvTimeStatue).text = "已过期"
//                helper.getView<ImageView>(R.id.ivTimeStatue).setBackgroundResource(R.mipmap.black_danger)
//                helper.getView<TextView>(R.id.tvTimeStatue).setTextColor(Color.parseColor("#898989"))
//            }
//
//        }
//    }
//
//}
