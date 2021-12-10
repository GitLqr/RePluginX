
## RePluginX

本 Repo 是对 [Qihoo360 RePlugin](https://github.com/Qihoo360/RePlugin) 的完善、增强。

- Q: 为什么有这个 Repo？
- A: 因工作项目需要 使用 & 定制，但官方已经很久没有维护（难道是太稳定了？），且 issue 也没有怎么处理，提交官方 pr 怕是要等到天荒地老，时间紧、任务重，我还是自己维护一个吧。



## 特性

- 同时支持 AnroidSupport、AndroidX 
- 支持配置 Activity 屏幕方向（待完成）
- ...



## 使用

- 开源协议：[![license](http://img.shields.io/badge/license-Apache2.0-brightgreen.svg?style=flat)](https://github.com/Qihoo360/RePlugin/blob/master/LICENSE) 
- **RPX_VERSION**： ![Release Version](https://img.shields.io/github/v/release/GitLqr/RePluginX.svg)

- **gralde Versions**：![](https://img.shields.io/badge/gradle-4.6-green)
- **gradle-android-tools Versions**：![](https://img.shields.io/badge/android.tools-2.3.3-green) ![](https://img.shields.io/badge/android.tools-3.2.1-green)



### 1、集成  jitpack 仓库

宿主/插件 项目根目录 build.gradle 中加入：

```groovy
buildscript {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```



### 2、配置 宿主(host) 工程

在宿主工程根目录下 build.gradle 中加入：

```groovy
buildscript {
    ...
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3' // AndroidX 工程需升级到 3.2.0 及以上
        classpath "com.github.GitLqr.RePluginX:replugin-host-gradle:${RPX_VERSION}"
    }
}
```

在宿主 主Module(app) 目录下 build.gradle 中加入：

```groovy
dependencies {
    ...
    implementation "com.github.GitLqr.RePluginX:replugin-host-library:${RPX_VERSION}"
    // 注意：如果是 AndroidX 宿主工程，必须添加如下依赖 ！！
    // implementation "androidx.localbroadcastmanager:localbroadcastmanager:1.0.0"
}
```



### 3、配置 插件(plugin) 工程

在插件工程根目录下 build.gradle 中加入：

```groovy
buildscript {
    ...
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3' // AndroidX 工程需升级到 3.2.0 及以上
        classpath "com.github.GitLqr.RePluginX:replugin-plugin-gradle:${RPX_VERSION}"
    }
}
```

在插件 主Module(app) 目录下 build.gradle 中加入：

```groovy
dependencies {
    ...
    implementation "com.github.GitLqr.RePluginX:replugin-plugin-library:${RPX_VERSION}"
}
```




## 官方原文档

- （[RePlugin 官方【英文】文档](./README_ORIGINAL.md)）
- （[RePlugin 官方【中文】文档](./README_CN.md)）
- [（RePlugin 官方 Wiki）](https://github.com/Qihoo360/RePlugin/wiki)
