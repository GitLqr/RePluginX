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

package com.qihoo360.mobilesafe.loader.a;

import android.app.Activity;
import android.os.Bundle;

import com.qihoo360.replugin.helper.LogRelease;

import static com.qihoo360.replugin.helper.LogDebug.PLUGIN_TAG;
import static com.qihoo360.replugin.helper.LogRelease.LOGR;

/**
 * @author RePlugin Team
 */
public class DummyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // INFO dummy activity on create finish me.
        if (LOGR) {
            LogRelease.i(PLUGIN_TAG, "d a o c f m");
        }

        // 之所以传Null，是因为系统会直接解析savedInstanceState
        // 这时如果常驻进程已被杀，这时立即恢复后，由于插件还没有准备好，故会出现崩溃情况
        // 详细见：Crash Hash = 5C863A3E0CACDAEA9DBD05B9A7D353FE
        super.onCreate(null);

        finish();
    }
}
