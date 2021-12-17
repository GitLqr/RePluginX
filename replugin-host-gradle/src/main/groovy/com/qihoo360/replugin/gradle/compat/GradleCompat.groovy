package com.qihoo360.replugin.gradle.compat

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BasePlugin
import com.android.build.gradle.internal.scope.VariantScope

/**
 * Gradle版本适配
 * 参照：https://github.com/yanbober/RePlugin/commit/64ec05e3a0ecf3db9a49c7668646468b48149810
 * 注意：replugin-plugin-gradle 中也是使用该类（因为它配置了sourceSets）
 *
 * @author LQR
 */
class GradleCompat {
    private GradleCompat() {
    }

    /**
     * 获取当前变体的appId
     */
    static def getAppID(def variant) {
        if (isGeAGP410()) {
            return variant.variantData.variantDslInfo.applicationId.get()
        }
        return variant.generateBuildConfig.appPackageName
    }

    /**
     * 获取当前变体对应自定义任务名
     */
    static def getTaskNameFromVariantScope(def variant, String prefix, String suffix) {
        def taskName = null
        if (isGeAGP410()) {
            def componentProperties = variant.componentProperties
            taskName = componentProperties.computeTaskName(prefix, suffix)
        } else {
            def variantData = variant.variantData
            def scope = variantData.scope
            taskName = scope.getTaskName(prefix, suffix)
        }
        return taskName
    }

    /**
     * 获取当前变体BuildConfig生成目录
     */
    static def getBuildConfigGeneratedDir(def variant) {
        File buildConfigGeneratedDir = null
        if (isGeAGP410()) {
            buildConfigGeneratedDir = variant.componentProperties.paths.buildConfigSourceOutputDir
        } else {
            buildConfigGeneratedDir = variant.getVariantData().getScope().getBuildConfigSourceOutputDir()
        }
        return buildConfigGeneratedDir
    }

    /**
     * 获取当前变体的GlobalScope
     */
    static def getVariantGlobalScope(def variant) {
        def globalScope = null
        if (isGeAGP410()) {
            def componentProperties = variant.componentProperties
            globalScope = componentProperties.globalScope
        } else {
            def variantData = variant.variantData
            VariantScope scope = variantData.scope
            globalScope = scope.globalScope
        }
        return globalScope
    }

    /**
     * 获取当前变体的配置
     */
    static def getVariantConfiguration(def variant) {
        def variantConfiguration = null
        if (isGeAGP410()) {
            variantConfiguration = variant.@componentProperties
        } else {
            variantConfiguration = variant.variantData.variantConfiguration
        }
        return variantConfiguration
    }

    /**
     * 获取 globalScope 的 ArchivesBaseName
     */
    static def getArchivesBaseName(def globalScope) {
        def archivesBaseName = null
        if (isGeAGP410()) {
            archivesBaseName = globalScope.getProjectBaseName()
        } else {
            archivesBaseName = globalScope.getArchivesBaseName()
        }
        return archivesBaseName
    }

    /**
     * 获取当前变体的签名配置
     */
    static def getSigningConfig(def variant) {
        def signingConfig = null
        def variantData = variant.variantData
        if (isGeAGP410()) {
            def variantDslInfo = variantData.variantDslInfo
            signingConfig = variantDslInfo.signingConfig
        } else {
            def variantConfiguration = variantData.variantConfiguration
            signingConfig = variantConfiguration.getSigningConfig()
        }
        return signingConfig
    }

    /**
     * 获取 project 的 GlobalScope
     */
    static def getProjectGlobalScope(def project) {
        def globalScope = null
        if (isGeAGP410()) {
            def androidExtension = project.extensions.getByName("android")
            globalScope = androidExtension.globalScope
        } else {
            def appPlugin = project.plugins.getPlugin(AppPlugin)
            // taskManager 在 2.1.3 中为 protected 访问类型的，在之后的版本为 private 访问类型的，
            // 使用反射访问
            def taskManager = BasePlugin.metaClass.getProperty(appPlugin, "taskManager")
            globalScope = taskManager.globalScope;
        }
        return globalScope
    }

    /**
     * 获取清单文件的输出路径
     */
    static def getManifestOutputFile(def processManifestTask) {
        if (isGeAGP410()) {
            return processManifestTask.multiApkManifestOutputDirectory
        } else {
            return processManifestTask.getManifestOutputDirectory()
        }
    }

    /**
     * 获取 InstantRun 清单文件的输出路径
     */
    static def getInstantRunManifestOutputFile(def processManifestTask) {
        if (isGeAGP410()) {
            return processManifestTask.multiApkManifestOutputDirectory
        } else {
            if (processManifestTask.metaClass.respondsTo(processManifestTask, "getInstantAppManifestOutputDirectory")) {
                // 3.5以上
                return processManifestTask.getInstantAppManifestOutputDirectory()
            } else { // 3.4以下
                return processManifestTask.getInstantRunManifestOutputDirectory()
            }
        }
    }


    /**
     * AGP 是否大于等于 4.1.0
     */
    static def isGeAGP410() {
        return getAGPVersion() >= '4.1.0'
    }

    /**
     * 获取 AGP（Android Gradle Plugin） 版本号
     * https://stackoverflow.com/questions/18098231/how-can-i-check-which-gradle-android-plugin-version-is-used-in-my-project/47757378
     */
    static def getAGPVersion() {
        String version = null
        // 旧版：com.android.builder.Version.ANDROID_GRADLE_PLUGIN_VERSION
        // 新版：com.android.builder.model.Version.ANDROID_GRADLE_PLUGIN_VERSION
        try {
            Class versionModel = Class.forName("com.android.builder.model.Version")
            if (versionModel == null) {
                versionModel = Class.forName("com.android.builder.Version")
            }
            def versionField = versionModel.getDeclaredField("ANDROID_GRADLE_PLUGIN_VERSION")
            versionField.setAccessible(true)
            version = versionField.get(null)
        } catch (Exception e) {
            e.printStackTrace()
        }
        return version
    }
}