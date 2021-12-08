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

    private final android.support.v4.content.LocalBroadcastManager localBroadcastManager;

    public LocalBroadcastManagerSupport(Context context) {
        localBroadcastManager = android.support.v4.content.LocalBroadcastManager.getInstance(context);
    }

    @Override
    public void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        localBroadcastManager.registerReceiver(receiver, filter);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        localBroadcastManager.unregisterReceiver(receiver);
    }

    @Override
    public boolean sendBroadcast(Intent intent) {
        return localBroadcastManager.sendBroadcast(intent);
    }

    @Override
    public void sendBroadcastSync(Intent intent) {
        localBroadcastManager.sendBroadcastSync(intent);
    }
}
