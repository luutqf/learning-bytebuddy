package com.luufery.bytebuddy.api.plugin.listener;


import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;

public class AgentListener implements AgentBuilder.Listener {


    @Override
    public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

    }

    @Override
    public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
        WeavingClassLog.INSTANCE.log(typeDescription, dynamicType);
//        System.out.println("================");
//        if (classLoader != null)
//            System.out.println(classLoader.getClass().getName());
//        if (module != null)
//            System.out.println(module.getActualName());
//        System.out.println(dynamicType);
//        System.out.println("================");
    }

    @Override
    public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {
//        System.out.println("ignored");

    }

    @Override
    public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
        System.out.printf("Failed to instrument %s %s.", typeName, throwable);
        throwable.printStackTrace();
    }

    @Override
    public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

    }
}
