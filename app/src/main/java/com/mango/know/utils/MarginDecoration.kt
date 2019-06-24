package com.mango.know.utils


import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by AndroidIntexh1 on 2018/9/11.
 * 用于设计RecycleViewItem之间的边距
 */

class MarginDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val marginRight: Int = DensityUtils.dip2px(context, 8f)
    private val marginleft: Int = DensityUtils.dip2px(context, 16f)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        //由于每行都只有2个，所以第一个都是2的倍数，把左边距设为0
        when {
            parent.getChildLayoutPosition(view) == 0 -> outRect.set(0, 0, 0, 0)
            parent.getChildLayoutPosition(view) % 2 == 0 -> outRect.set(marginRight, 0, marginleft, 0)
            else -> outRect.set(marginleft, 0, marginRight, 0)
        }

    }
}
