package com.example.tanganan.networkbroadcastreceiver.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.example.tanganan.networkbroadcastreceiver.R;

public class ContentDialog extends Dialog {

    private Window window;

    /**
     * Dialog基类 与BaseDialogFragment配合使用
     */
    public ContentDialog(Context context) {
        super(context);
        window = getWindow();
    }

    /**
     * @param flag 0 从下网上 ;1 从上往下;2 从中间
     */
    protected void setAnim(int flag) {
        if (flag == 0) {
            window.setWindowAnimations(R.style.anim_popup_from_bottom); // 设置窗口弹出动画
        } else if (flag == 1) {
            window.setWindowAnimations(R.style.anim_popup_person_show);
        } else if (flag == 2) {
            window.setWindowAnimations(R.style.anim_popup_from_center);

        }

    }
}

