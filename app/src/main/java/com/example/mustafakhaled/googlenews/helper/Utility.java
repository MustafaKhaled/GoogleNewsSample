package com.example.mustafakhaled.googlenews.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/*
 * Created by Mustafa Khaled on 9/16/2018.
 *
 */ public class Utility  {
     public static ProgressDialog mProgressDialog;
     public static SharedPreferences.Editor sharedpref ;
     public static void showProgressBar(Context context,String message){
         if(mProgressDialog==null||!mProgressDialog.isShowing()){

             mProgressDialog = ProgressDialog.show(context, "", message);
             mProgressDialog.setCancelable(false);
             Log.i("++showProgressDialog",mProgressDialog.isShowing()+"--");
         }else {
             Log.i("++showProgressDialog","already exist--");
         }
     }
     public static void removeProgressDialog(){

         Log.i("++before remove",mProgressDialog+"--");

         try {
             if (mProgressDialog != null) {
                 if (mProgressDialog.isShowing()) {
                     mProgressDialog.dismiss();
                     mProgressDialog = null;
                     Log.i("++remove","dialog!=null so removed--");
                 }
                 Log.i("++after remove",mProgressDialog+"--");
             }
         } catch (IllegalArgumentException ie) {
             ie.printStackTrace();

         } catch (RuntimeException re) {
             re.printStackTrace();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}
