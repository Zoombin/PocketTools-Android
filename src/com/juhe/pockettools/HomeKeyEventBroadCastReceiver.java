package com.juhe.pockettools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HomeKeyEventBroadCastReceiver extends BroadcastReceiver {
	static final String reason = "reason";
	static final String homekey = "homekey";
	static final String recentapps = "recentapps";

	
	public void onReceive(Context context, Intent intent) {
		 String action = intent.getAction();
	        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
	            String reason = intent.getStringExtra( "reason" );
	            if (reason != null) {
//	                if( null != mOnHomeBtnPressListener ){
//	                    if( reason.equals( "homekey" ) ){
//	                        // 按Home按键
//	                        mOnHomeBtnPressListener.onHomeBtnPress( );
//	                    }else if( reason.equals( "recentapps" ) ){
//	                        // 长按Home按键
//	                        mOnHomeBtnPressListener.onHomeBtnLongPress( );
//	                    }
//	                }
	            }
	        }
	}
}
