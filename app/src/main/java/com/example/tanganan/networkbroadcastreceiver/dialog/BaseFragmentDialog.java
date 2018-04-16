package com.example.tanganan.networkbroadcastreceiver.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseFragmentDialog extends DialogFragment {

    private ContentDialog baseDialog;

    @Override
    public void onStart() {
        super.onStart();
        initGravityType();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        baseDialog = new ContentDialog(getActivity());
        baseDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        baseDialog.setAnim(getAnimType());

        View view = getLayoutView(inflater, baseDialog);

        baseDialog.setContentView(view);
        initView(view);
        initListener();
        return baseDialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * 处理子类需要显示的位置
     */
    private void initGravityType() {
        //设置 LayoutParams.MATCH_PARENT去掉缝隙
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0xff000000));
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        // 设置宽
        if (getlayoutParamsOfwidth() == 0) {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            layoutParams.width = getlayoutParamsOfwidth();
        }
        // 设置高
        if (getlayoutParamsOfheight() == 0) {
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            layoutParams.width = getlayoutParamsOfwidth();
        }
        //设置位置
        if (getGravityType() == 0) {
            layoutParams.gravity = Gravity.BOTTOM;
        } else if (getGravityType() == 1) {
            layoutParams.gravity = Gravity.TOP;
        } else if (getGravityType() == 2) {
            layoutParams.gravity = Gravity.CENTER;
        }
        getDialog().getWindow().setAttributes(layoutParams);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDialogDismissListener != null) {
            onDialogDismissListener.dismiss();
        }
    }

    public void setOnDialogDismissListener(OnDialogDismissListener onDialogDismissListener) {
        this.onDialogDismissListener = onDialogDismissListener;
    }

    private OnDialogDismissListener onDialogDismissListener;

    /**
     * @param inflater 子类布局
     * @param dialog   子类Dialog
     * @return
     */
    public abstract View getLayoutView(LayoutInflater inflater, Dialog dialog);

    /**
     * 子类布局初始化控件
     *
     * @param view
     */
    public abstract void initView(View view);

    /**
     * 加载数据
     */
    public abstract void initData();

    /**
     * 监听
     */
    public abstract void initListener();

    /**
     * 动画效果
     *
     * @return 0: 从下网上 ; 1:从上往下; 2:中间
     */
    public abstract int getAnimType();

    /**
     * 显示位置
     *
     * @return 0: 底部 ; 1:上部;2:在中间
     */
    public abstract int getGravityType();

    /**
     * width显示大小
     * return 0 时LayoutParams.MATCH_PARENT
     *
     * @return layout_width
     */
    public abstract int getlayoutParamsOfwidth();

    /**
     * height显示大小
     * return 0 时LayoutParams.WRAP_CONTENT;
     *
     * @return layout_height
     */
    public abstract int getlayoutParamsOfheight();
}

