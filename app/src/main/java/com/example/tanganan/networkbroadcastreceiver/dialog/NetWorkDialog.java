package com.example.tanganan.networkbroadcastreceiver.dialog;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.tanganan.networkbroadcastreceiver.R;

/**
 * Created by TangAnna on 2018/4/16.
 */

public class NetWorkDialog extends BaseFragmentDialog {

    @Override
    public View getLayoutView(LayoutInflater inflater, Dialog dialog) {
        return inflater.inflate(R.layout.dialog_network, null);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public int getAnimType() {
        return 0;
    }

    @Override
    public int getGravityType() {
        return 2;
    }

    @Override
    public int getlayoutParamsOfwidth() {
        return 0;
    }

    @Override
    public int getlayoutParamsOfheight() {
        return 0;
    }
}
