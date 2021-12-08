package com.qihoo360.replugin.compat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * AndroidSupport 的 LocalBroadcastManager
 *
 * @author LQR
 * @since 2021/12/8
 */
public class LocalBroadcastManagerSupport extends LocalBroadcastManager {

    public LocalBroadcastManagerSupport(Context context) {

    }

    @Override
    public void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {

    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {

    }

    @Override
    public boolean sendBroadcast(Intent intent) {
        return false;
    }

    @Override
    public void sendBroadcastSync(Intent intent) {

    }
}
