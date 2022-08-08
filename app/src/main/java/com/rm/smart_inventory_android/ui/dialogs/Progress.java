package com.rm.smart_inventory_android.ui.dialogs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.rm.smart_inventory_android.R;

import java.util.zip.Inflater;

public class Progress {

    public static ProgressBar simpleProgressBar;
    public static ProgressDialog progressDialog;

    public static void showProgressBar(Activity activity){
        simpleProgressBar = new ProgressBar(activity);
        simpleProgressBar = activity.findViewById(R.id.simpleProgressBar);
        simpleProgressBar.setIndeterminate(true);
        simpleProgressBar.setVisibility(View.VISIBLE);
    }

    public static void dismissProgressBar(){
        simpleProgressBar.setVisibility(View.GONE);
    }

    public static void showProgressDialog(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void dismissProgressDialog(Context context){
        progressDialog.dismiss();
    }

}
