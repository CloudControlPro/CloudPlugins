//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.cloud.plugin.sdk;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Keep;

import java.util.HashMap;

public abstract class PluginRegistry {
    private static final String NAME_DEFAULT = "::default";
    static final int VERSION = 2;
    private static final String LOG_TAG = "Plugin";
    private static HashMap<String, PluginLoader> sPluginLoaders = new HashMap();

    public PluginRegistry() {
    }

    public void onAttached() {
    }

    public void onDetached() {
    }

    @Keep
    public static Plugin load(String name, Context context, Context selfContext, Object runtime, Object topLevelScope) {
        PluginLoader pluginLoader = (PluginLoader)sPluginLoaders.get(name);
        Log.i("Plugin", "load: name = " + name + ", loader = " + pluginLoader);
        return pluginLoader == null ? null : pluginLoader.load(context, selfContext, runtime, topLevelScope);
    }

    @Keep
    public static Plugin loadDefault(Context context, Context selfContext, Object runtime, Object topLevelScope) {
        return load("::default", context, selfContext, runtime, topLevelScope);
    }

    public static void registerPlugin(String name, PluginLoader loader) {
        Log.i("Plugin", "registerPlugin: name = " + name + ", loader = " + loader);
        sPluginLoaders.put(name, loader);
    }

    public static void registerDefaultPlugin(PluginLoader loader) {
        registerPlugin("::default", loader);
    }
}
