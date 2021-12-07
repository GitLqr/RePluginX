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

package com.qihoo360.replugin.gradle.host.creator;

/**
 * @author RePlugin Team
 */
public interface IFileCreator {

    /**
     * 要生成的文件所在目录
     */
    File getFileDir()

    /**
     * 要生成的文件的名称
     */
    String getFileName()

    /**
     * 要生成的文件内容
     */
    String getFileContent()
}
