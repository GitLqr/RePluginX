/*
 * Copyright (C) 2005-2017 Qihoo 360 Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.qihoo360.replugin.sample.demo1.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.qihoo360.replugin.sample.demo1.support.LogX;

/**
 * @author RePlugin Team
 */
public class PluginDemoService1 extends Service {

    public static final String TAG = "demo.service";

    @Override
    public void onCreate() {
        super.onCreate();

        LogX.logDebug(TAG, "PluginDemoService1 onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        Toast.makeText(this, "PluginDemoService1.action = " + action, Toast.LENGTH_SHORT).show();
        LogX.logDebug(TAG, "PluginDemoService1 onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogX.logDebug(TAG, "onDestroy()");
    }
}
