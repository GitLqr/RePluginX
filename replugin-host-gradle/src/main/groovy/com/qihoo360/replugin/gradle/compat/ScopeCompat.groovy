package com.qihoo360.replugin.gradle.compat

import com.android.sdklib.IAndroidTarget
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.provider.Provider

/**
 * @author hyongbai
 */
class ScopeCompat {
    static def getAdbExecutable(def scope) {
        final MetaClass scopeClz = scope.metaClass
        if (scopeClz.hasProperty(scope, "androidBuilder")) {
            return scope.androidBuilder.sdkInfo.adb
        }
        if (scopeClz.hasProperty(scope, "sdkComponents")) {
            def sdkComponents = scope.sdkComponents
            if (sdkComponents instanceof Provider) sdkComponents = sdkComponents.get()
            def adbExeFile = sdkComponents.adbExecutableProvider.get()
            // 4.1.0 拿到的类型是：org.gradle.api.internal.file.DefaultFilePropertyFactory$FixedFile
            if (adbExeFile instanceof FileSystemLocation) adbExeFile = adbExeFile.getAsFile()
            return adbExeFile
        }
    }

    // TODO: getBuilderTarget
//    static def getBuilderTarget(def scope, def target){
//        final MetaClass scopeClz = scope.metaClass
//
//        if (scopeClz.hasProperty(scope, "androidBuilder")) {
//            return scope.getAndroidBuilder().getTarget().getPath(target) //IAndroidTarget.ANDROID_JAR
//        }
//
//        return globalScope.getAndroidBuilder().getTarget().getPath(IAndroidTarget.ANDROID_JAR)
//    }

    static def getAndroidJar(def scope) {
        final MetaClass scopeClz = scope.metaClass

        if (scopeClz.hasProperty(scope, "androidBuilder")) {
            return scope.getAndroidBuilder().getTarget().getPath(IAndroidTarget.ANDROID_JAR)
        }
        if (scopeClz.hasProperty(scope, "sdkComponents")) {
            def sdkComponents = scope.sdkComponents
            if (sdkComponents instanceof Provider) sdkComponents = sdkComponents.get()
            return sdkComponents.androidJarProvider.get().getAbsolutePath()
        }
    }
}