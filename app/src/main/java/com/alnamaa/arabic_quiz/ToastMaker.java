package com.alnamaa.arabic_quiz;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class ToastMaker {
    private static Context context;
    static ToastMaker toastMaker;
    private static View view;

    private ToastMaker(Context context, View view){
        ToastMaker.context = context;
        ToastMaker.view=view;
    }
    public static void initialize(Context context, View view)
    {
        toastMaker = new ToastMaker(context,view);
        MyLogger.printAndStore("ToastMaker has been initialized");
    }
    public static void showError(int error_number)
    {
        if(context!=null)
            Toast.makeText(context, "Error "+error_number+" please contact the admin.", Toast.LENGTH_LONG).show();
    }

    public static void showMessage(String message) {
        if(context!=null)
        {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();

            if(view!=null) {
                /*
                Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                 */
            }

            MyLogger.printAndStore("ToastMaker.showMessage "+message);
        }else{
            MyLogger.printAndStore("ToastMaker.showMessage no context :("+message);
        }
    }

    public static void showDialog(String title, String content, String okButtonString) {

        final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
        dlgAlert.setMessage(content);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton(okButtonString, null);
        /*dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });*/
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

    }
    public static void showDialog2(){
        /*
        CustomDialogClass cdd = new CustomDialogClass((Activity) context);
        Window window = cdd.getWindow();
        if(window!=null)
        {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        cdd.show();

         */
    }
}
