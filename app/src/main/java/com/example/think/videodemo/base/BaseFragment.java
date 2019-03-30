package com.example.think.videodemo.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 *
 *  Created by Boomerr 19/2/26
 *
 *
 * */

public abstract class BaseFragment extends Fragment {

    protected ProgressDialog progressDialog;

    protected Context mContext;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return initView();
    }
    protected abstract View initView();

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void initData() {

    }

    public void toast(String toastString){
        Toast.makeText(getActivity(),toastString,Toast.LENGTH_SHORT).show();
    }

    private void createPresenter(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
        }
    }

    public void showLoading(){
        createPresenter();
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    public void hideLoading(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
