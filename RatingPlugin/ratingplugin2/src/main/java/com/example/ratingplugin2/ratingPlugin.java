package com.example.ratingplugin2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class ratingPlugin {
    private static final ratingPlugin ourInstance = new ratingPlugin();

    private static final String LOGTAG = "ratingPlugin";

    public static ratingPlugin getInstance() {
        return ourInstance;
    }

    public static Activity mainActivity;

    public interface AlertViewCallback {
        public void onButtonTapped(int id);
    }
    private long startTime;

    private ratingPlugin() {
        Log.i(LOGTAG,"Created ratingPlugin");
        startTime = System.currentTimeMillis();
    }

    //Function to calculate elapsed time
    public double getElapsedTime()
    {
        return (System.currentTimeMillis()-startTime)/1000.0f;
    }

    //Function to view AlertDialog box
    public void showAlertView(String[] strings, final AlertViewCallback callback)
    {
        if (strings.length<3)
        {
            Log.i(LOGTAG,"Error - expected at least 3 strings, got " + strings.length);
            return;
        }

        //OnClickListener for maybe later button
        DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.dismiss();
                Log.i(LOGTAG, "Tapped: " + id);
                callback.onButtonTapped(id);
            }
        };

        //OnClickListener for rate now button
        DialogInterface.OnClickListener myURLClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                String url = "https://play.google.com/store/apps/details?id=co.sdslabs.mdg.appetizer&hl=en";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                mainActivity.startActivity(i);
                Log.i(LOGTAG, "Tapped: " + id);
                callback.onButtonTapped(id);
            }
        };

        //creating an AlertDialog
        AlertDialog alertDialog = new AlertDialog.Builder(mainActivity)
                .setTitle(strings[0])
                .setIcon(R.drawable.fivestaricon)
                .setMessage(strings[1])
                .setCancelable(false)
                .create();
        //Setting up the onClickListener for neutral Button
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,strings[2],myURLClickListener);

        //Setting up the OnclickListener for Negative Button
        if (strings.length>3)

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,strings[3],myClickListener);
        //Setting up the OnClickListener for Positive Button
        if (strings.length>4)
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,strings[4],myURLClickListener);
        alertDialog.show();
    }
}
