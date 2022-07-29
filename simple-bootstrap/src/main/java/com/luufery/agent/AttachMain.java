package com.luufery.agent;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class AttachMain {
    public static void main(String[] args) throws AgentLoadException, IOException, AgentInitializationException, InterruptedException, AttachNotSupportedException {
        System.out.println("running JVM start ");
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            System.out.println(vmd.displayName());
            if (vmd.displayName().endsWith("Test03Application")) {
                System.out.println(vmd.id());
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                virtualMachine.loadAgent("/Users/luufery/workspace/com/luufery/learning-bytebuddy/simple-bootstrap/target/simple-bootstrap-1.0-SNAPSHOT-jar-with-dependencies.jar");
                virtualMachine.detach();
                System.out.println("attach");
            }
        }
        Thread.sleep(10000L);
    }
}