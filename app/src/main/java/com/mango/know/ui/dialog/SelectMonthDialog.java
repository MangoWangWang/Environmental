package com.mango.know.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.mango.know.R;
import com.mango.know.utils.DateUtil;
import com.ruffian.library.widget.RTextView;
import com.ycuwq.datepicker.date.MonthPicker;
import com.ycuwq.datepicker.date.YearPicker;
import kale.ui.view.dialog.BaseCustomDialog;
import kale.ui.view.dialog.BaseEasyDialog;

/**
 * Created by AndroidIntexh1 on 2018/12/26.
 */

public class SelectMonthDialog extends BaseCustomDialog {

    public OnDialogClickListener dialogListener;
    public MonthPicker monthPicker;
    public YearPicker yearPicker;
    private RTextView tvSure;
    private RTextView tvCancle;

    /**
     * 自定义builder来增加一些参数，记得要继承自BaseEasyDialog.Builder
     */
    public static class Builder extends BaseEasyDialog.Builder<SelectMonthDialog.Builder> {
        private Bundle bundle = new Bundle();
        private SelectMonthDialog.OnDialogClickListener listener;

        public Builder(@NonNull Context context) {
            super(context);
        }


        public SelectMonthDialog.Builder setOnDialogClickListener(SelectMonthDialog.OnDialogClickListener lis) {
            listener = lis;
            return this;
        }

        @NonNull
        @Override
        protected SelectMonthDialog createDialog() {
            SelectMonthDialog dialog = new SelectMonthDialog();
            dialog.setArguments(bundle);
            dialog.dialogListener = listener;
            return dialog;
        }
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_select_month;
    }

    @Override
    protected void bindViews(View view) {
        tvSure = findView(R.id.tv_sure);
        tvCancle = findView(R.id.tv_cancel);
        monthPicker = findView(R.id.month_picker);
        yearPicker = findView(R.id.year_picker);
        setListener();

    }

    private void setListener() {
        tvSure.setOnClickListener(view -> {
            int year = yearPicker.getSelectedYear();
            int month = monthPicker.getSelectedMonth();
            int todayYear = Integer.valueOf(DateUtil.INSTANCE.getYear(System.currentTimeMillis()));
            int todayMonth = Integer.valueOf(DateUtil.INSTANCE.getMonth(System.currentTimeMillis()));
            if (year>todayYear || (year == todayYear && month > todayMonth))
            {
                Toast.makeText(getContext(), "选择月份大于当前月份", Toast.LENGTH_SHORT).show();
            }else
            {
                dialogListener.onClick(view, String.valueOf(year)+ String.format("%02d", month));
                dismiss();
            }
        });
        tvCancle.setOnClickListener(view -> dismiss());
    }

    @Override
    protected void setViews() {
        setBackground();
        setLayout();
    }

    /**
     * 强制取消背景，保持有透明
     */
    private void setBackground() {
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable()); // 去除dialog的背景，即透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0xffffffff)); // 设置白色背景
//        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_shop_jion_bg); // 设置背景
    }


    /**
     * 也可通过setLayout来设置：
     * getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
     */
    private void setLayout() {
        Window window = getDialog().getWindow();
        final WindowManager.LayoutParams lp = window.getAttributes();

        // 强制宽高
        int padding = getResources().getDimensionPixelOffset(R.dimen.dialog_padding);
        lp.width = getScreenWidth(getActivity()) - (padding * 2);
//        lp.height = getResources().getDimensionPixelOffset(R.dimen.dialog_height);

        lp.gravity = Gravity.CENTER; // 设置展示的位置
//        lp.y = getResources().getDimensionPixelOffset(R.dimen.join_shop_dialog_margin);
        window.setAttributes(lp);
    }

    public static int getScreenWidth(Activity activity) {
        final DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 监听输入框内容
     */
    public interface OnDialogClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onClick(View v, String month);
    }


}
