package com.abukhleif.www.jdci.demo;

import com.abukhleif.www.jdci.injection.CustomAction;
import com.abukhleif.www.jdci.injection.CustomActionBuilder;
import com.abukhleif.www.jdci.injection.Parameter;
import com.abukhleif.www.jdci.node.NodeEngine;
import com.abukhleif.www.jdci.node.NodeProperties;

public class Demo {

    public static void main(String[] args) {
        CustomActionBuilder builder = new CustomActionBuilder();

        CustomAction test1 = builder.newAction("Test 1")
                .withCode("System.out.println(\"Hello from Test1\");")
                .build();

        CustomAction test2 = builder.newAction("Test 2")
                .withCode("int x = 10;\n" +
                        "double y = 5.5;\n" +
                        "System.out.println(x + y);")
                .build();

        CustomAction test3 = builder.newAction("Test 3")
                .withCode("NodeProperties.getNodeProperties().forEach((k, v) -> System.out.println(String.format(\"%s: %s\", k, v)));")
                .build();

        CustomAction test4 = builder.newAction("Test 4")
                .withCode("NodeProperties.setProprty(\"DUMMY_PROPERTY\", \"BlaBlaBla\");\n" +
                        "NodeProperties.setProprty(\"THREADS\", 4);")
                .build();

        CustomAction test5 = builder.newAction("Test 5")
                .withCode("NodeEngine engine = parameters.getEngine();\n" +
                        "engine.makeMagic(\"NYAHAHAHAHA!\");\n" +
                        "engine.runService(\"Searching...\");")
                .withParameter(new Parameter(new NodeEngine("Dummy Engine")))
                .build();

        CustomAction test6 = builder.newAction("Test 6")
                .importing("java.util.HashSet")
                .withCode("HashSet<String> values = new HashSet<>();\n" +
                        "values.add(\"hello\");\n" +
                        "values.add(\"world\");\n" +
                        "System.out.println(\"Your values are...\");\n" +
                        "values.forEach(System.out::println);")
                .build();

        CustomAction test7 = builder.newAction("Test 7")
                .extend("DummySuperClass")
                .importing("com.abukhleif.www.jdci.demo.DummySuperClass")
                .withCode("doSomething();")
                .build();

        startSection("Test 1 - Simple Statement");
        test1.execute();
        startSection("Test 2 - Simple Expression");
        test2.execute();
        startSection("Test 3 - Read System Values");
        test3.execute();

        startSection("Test 4 - Update System Values");
        test4.execute();

        // we modify system properties using a custom code!! =D
        System.out.println("Node Properties Outside our CustomAction:: ");
        NodeProperties.getNodeProperties().forEach((k, v) -> System.out.println(String.format("%s: %s", k, v)));

        startSection("Test 5 - Use Provided Parameters");
        test5.execute();

        startSection("Test 6 - Import External Classes");
        test6.execute();

        startSection("Test 7 - Extend Other Class");
        test7.execute();
    }

    private static void startSection(String title) {
        System.out.printf("==========\nExecuting: %s::\n\n", title);
    }

}
