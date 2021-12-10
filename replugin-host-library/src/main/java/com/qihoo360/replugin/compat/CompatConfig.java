package com.qihoo360.replugin.compat;

/**
 * 兼容层配置
 *
 * @author LQR
 * @since 2021/12/8
 */
public final class CompatConfig {

    private static volatile CompatConfig sInstance;

    public static final boolean DEPENDENCY_ANDROIDX;
    public static final boolean DEPENDENCY_SUPPORT;

    static {
        // FIX: 宿主工程（空壳）不一定会依赖 androidx.appcompat，但是一定会依赖 androidx.localbroadcastmanager
        DEPENDENCY_ANDROIDX = findClassByClassName("androidx.localbroadcastmanager.content.LocalBroadcastManager");
        DEPENDENCY_SUPPORT = findClassByClassName("android.support.v4.content.LocalBroadcastManager");
        // DEPENDENCY_ANDROIDX = findClassByClassName("androidx.fragment.app.FragmentActivity");
        // DEPENDENCY_SUPPORT = findClassByClassName("android.support.v4.app.FragmentActivity");
    }

    private CompatConfig() {
    }

    public static CompatConfig getInstance() {
        if (sInstance == null) {
            synchronized (CompatConfig.class) {
                if (sInstance == null) {
                    sInstance = new CompatConfig();
                }
            }
        }
        return sInstance;
    }

    private static boolean findClassByClassName(String className) {
        boolean hasDependency;
        try {
            Class.forName(className);
            hasDependency = true;
        } catch (ClassNotFoundException e) {
            hasDependency = false;
        }
        return hasDependency;
    }
}
