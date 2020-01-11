package com.tc.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟一个消费者
 */
public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();
       // producer.saleProduct(1000f);

        /**
         * 动态代理
         *    基于接口的动态代理
         *       jdk提供  proxy为涉及的类   使用newProxyInstance方法
         *       newProxyInstance方法涉及的参数
         *          classloader:类加载器
         *         class[] ,字节码数组，让代理对象与被代理对象有相同的方法
         *         invocationHander:用于提供
         *    基于子类的动态代理
         */

    Iproducer producerProxy =    (Iproducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 执行被代理对象的任何方法都会经过此方法
                     * @param proxy  被代理类的引用
                     * @param method  当前执行的方法
                     * @param args  方法的参数
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        /**
                         * 提供增强的方法
                         */
                        Object retValue =null;
                        Float money = (Float) args[0];
                        if ("saleProduct".equals(method.getName())){

                            retValue = method.invoke(producer,money*0.8f);
                        }
                        return  retValue;
                    }
                });

        producerProxy.saleProduct(10000f);
    }
}
