package com.mango.know.ui.activity.web;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.mango.know.constant.Constant;

public class JavaScriptInterface {

    private Handler handler;

    public JavaScriptInterface(Handler handler) {
        this.handler = handler;
    }

    @JavascriptInterface
    public void back() {
        Message message = new Message();
        message.what = Constant.BACK;
        handler.sendMessage(message);
    }

    @JavascriptInterface
    public void pay_order(String payType ,String order_sn){ //支付类型  支付宝（alipay） 微信（weChat）
        Log.e("mango","payType=="+payType);
        Message message = new Message();
        message.what = Constant.PAY;
        message.obj = payType+","+ order_sn;
        handler.sendMessage(message);
    }


    @JavascriptInterface
    public void GoToLogin(){
        Message message = new Message();
        message.what = Constant.LOGIN;
        handler.sendMessage(message);
    }

    @JavascriptInterface
    public void GoToCamera(){
        Message message = new Message();
        message.what = Constant.CAMERA;
        handler.sendMessage(message);
    }

}
