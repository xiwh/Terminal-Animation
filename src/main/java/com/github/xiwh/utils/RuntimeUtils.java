package com.github.xiwh.utils;

import java.lang.management.ManagementFactory;

public class RuntimeUtils {
    public static boolean isRunningFromIntelliJ()
    {
        String classPath = System.getProperty("java.class.path");
        return classPath.contains("idea_rt.jar") || System.getProperty("idea.test.cyclic.buffer.size") != null;
    }

    public static boolean isDebug(){
        boolean isDebug = ManagementFactory.
                getRuntimeMXBean().
                getInputArguments().toString().contains("jdwp");
        return isDebug;
    }

    public static String getPID() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        return name.split("@")[0];
    }
}
