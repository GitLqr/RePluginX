package com.qihoo360.replugin.compat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * LocalBroadcastManager 兼容层
 *
 * @author LQR
 * @since 2021/12/8
 */
public abstract class LocalBroadcastManager {

    public static LocalBroadcastManager getInstance(Context context) {
        if (CompatConfig.DEPENDENCY_ANDROIDX) {
            return new LocalBroadcastManagerAndroidX(context);
        } else if (CompatConfig.DEPENDENCY_SUPPORT) {
            return new LocalBroadcastManagerSupport(context);
        }
        return null;
    }

    public abstract void registerReceiver(BroadcastReceiver receiver, IntentFilter filter);

    public abstract void unregisterReceiver(BroadcastReceiver receiver);

    public abstract boolean sendBroadcast(Intent intent);

    public abstract void sendBroadcastSync(Intent intent);

}
