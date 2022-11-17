## RePluginX

<p align="center">
  <a href="https://github.com/Qihoo360/RePlugin/wiki">
    <img alt="RePluginX Logo" src="https://cdn.jsdelivr.net/gh/FullStackAction/PicBed@resource20210320170901/image/202112101539434.png" width="400"/>
  </a>
</p>

<p align="center">
  <a href="https://github.com/Qihoo360/RePlugin/blob/master/LICENSE">
    <img src="http://img.shields.io/badge/license-Apache2.0-brightgreen.svg?style=flat" alt="license" />
  </a>
</p>

## 特性

- ✅ 支持 AnroidSupport、AndroidX 工程
- ✅ 支持 RePlugin Transform 开关 配置 (`enable`)
- ✅ 支持 坑位 Activity 的屏幕方向 配置 (`screenOrientation`)
- ✅ 支持 移除 class 文件 配置 (`classExcludes`、`classIncludes`)
- ✅ 支持 多版本 AGP `2.x` `3.x` `4.x` （7.x 暂未兼容）
- ...

| AGP   | Gradle Wrapper | Support |
| ----- | -------------- | ------- |
| 2.3.3 | 3.3 / 4.6      | ✔️      |
| 3.2.1 | 4.6            | ✔️      |
| 3.5.3 | 5.4.1          | ✔️      |
| 4.1.1 | 6.5            | ✔️      |
| 7.0.4 | 7.0.2          | ❌      |

> 注：AGP 即 Android Gradle Plugin

## 使用

- **RPX_VERSION**： ![Release Version](https://img.shields.io/github/v/release/GitLqr/RePluginX.svg)
- **gralde Versions**：![](https://img.shields.io/badge/gradle-4.6-green)
- **AGP Versions**：![](https://img.shields.io/badge/android.tools-3.2.1-green)

### 1、集成 jitpack 仓库

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

在宿主 主 Module(app) 目录下 build.gradle 中加入：

```groovy
dependencies {
    ...
    implementation "com.github.GitLqr.RePluginX:replugin-host-library:${RPX_VERSION}"
    // 注意：如果是 AndroidX 宿主工程，必须添加如下依赖 ！！
    // implementation "androidx.localbroadcastmanager:localbroadcastmanager:1.0.0"
}

apply plugin: 'replugin-host-gradle'
repluginHostConfig {
    // enable = true // 是否启用插件功能，默认为true
    screenOrientation = 'landscape' // 坑位 Activity 方向（portrait / landscape）
    ...
}
```

- `enable`：是否启用插件功能，默认为 true，宿主工程一般不会用到！
- `screenOrientation` ：坑位 Activity 的屏幕方向配置，默认不配置即为竖屏 `portrait`，如需横屏可配置为 `landscape`。

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

在插件 主 Module(app) 目录下 build.gradle 中加入：

```groovy
dependencies {
    ...
    implementation "com.github.GitLqr.RePluginX:replugin-plugin-library:${RPX_VERSION}"
}

// 这个plugin需要放在android配置之后，因为需要读取android中的配置项
apply plugin: 'replugin-plugin-gradle'
repluginPluginConfig {
    enable = true // 是否启用插件功能，默认为true
    classExcludes = [] // 移除class文件配置（正则表达式），例如：["androidx.*"]
    classIncludes = [] // 保留class文件配置（正则表达式），例如：["androidx.fragment.app.FragmentActivity"]
    ...
}
```

- `enable`：是否启用插件功能，默认为 true。配置为 false 时，插件工程将失去 RePlugin Transform 功能（Transform 很耗时），这意味着工程打包出来的就只是普通的 apk，但同时也恢复了原有的编译速度，一般只在快速开发工程时配置为 false。记得在正式出包前配置为 true 或将其注释掉。
- `classExcludes`：移除 class 文件配置【正则表达式】，默认为空(不移除任何 class)。配置后，只要 class 文件的全限定名 满足该配置列表中的任意一项正则表达式时，都不会被打包进插件 apk 文件中，从而减少插件 apk 体积。
- `classIncludes`：保留 class 文件配置【正则表达式】，默认为空。该配置主要是为了与 `classExcludes` 配合使用，当指定包路径下除了个别 class 文件外需要保留，而其余 class 文件需要移除时，可以在该配置列表中指定要保留的 class 文件全限定名。

## 技术要点

- [《RePluginX - 兼容 AndroidX 并加入新特性开发纪要》](https://juejin.cn/post/7040733114506674183)

## 官方原文档

- [《RePlugin 官方【英文】文档》](./README_ORIGINAL.md)
- [《RePlugin 官方【中文】文档》](./README_CN.md)
- [《RePlugin 官方 Wiki》](https://github.com/Qihoo360/RePlugin/wiki)

![](https://github.com/LinXunFeng/LinXunFeng/raw/master/static/img/FSAQR.png)
