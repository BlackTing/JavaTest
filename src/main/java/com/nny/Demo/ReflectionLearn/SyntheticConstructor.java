package com.nny.Demo.ReflectionLearn;

/**
 *反射
 * 构造方法
 * 演示合成的构造方法
 */
public class SyntheticConstructor {

    private SyntheticConstructor() {}

    class Inner {
        // Compiler will generate a synthetic constructor since
        // SyntheticConstructor() is private.
        Inner() { new SyntheticConstructor(); }
    }
}
