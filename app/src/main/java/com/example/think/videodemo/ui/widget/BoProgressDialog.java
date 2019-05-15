package com.example.think.videodemo.ui.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

public class BoProgressDialog extends ProgressDialog {

    public BoProgressDialog(Context context) {
        super(context);
    }

    public BoProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



}
