package com.rm.smart_inventory_android.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.LocationListener;

public class LocationDialog {

    public static void openQuestion(String title, String question, Activity activity, final LocationListener listener) {
        final Dialog dialog= new Dialog(activity);
        View view= activity.getLayoutInflater().inflate(R.layout.location_dialog, null);
        final TextView txtTitle=(TextView) view.findViewById(R.id.txt_title);
        final TextView txtQuestion=(TextView) view.findViewById(R.id.txt_question);
        txtTitle.setText(title);
        txtQuestion.setText(question);
        final EditText comment=(EditText) view.findViewById(R.id.txt_answer);
        final Button yes=(Button) view.findViewById(R.id.yes_button);
        final Button cancel=(Button) view.findViewById(R.id.no_button);
        yes.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            dialog.dismiss();
            listener.onOpenQuestion(1, comment.getText().toString());
        });
        cancel.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            dialog.dismiss();
        });

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations= R.style.dialog_animation;
        dialog.setContentView(view);
        dialog.show();


    }

}
