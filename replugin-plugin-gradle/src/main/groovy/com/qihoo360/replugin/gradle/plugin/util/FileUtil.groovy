package com.qihoo360.replugin.gradle.plugin.util;

/**
 * 文件工具类
 * @author LQR*
 * @since 2022/9/25
 */
class FileUtil {

    /**
     * 获取文件夹下所有文件
     * @param dir 文件夹
     * @param targetList 存放文件的列表
     */
    static void getFileListDeep(File dir, List<File> targetList) {
        def files = dir.listFiles()
        for (File file : files) {
            if (file.isDirectory()) {
                getFileListDeep(file, targetList)
            } else {
                targetList.add(file)
            }
        }
    }
}
