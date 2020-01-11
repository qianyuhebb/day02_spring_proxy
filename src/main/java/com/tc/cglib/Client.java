package com.tc.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

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
         *    基于子类的动态代理
         *       涉及的类：enhancer
         *       提供者：第三方类库cglib
         *    如何创建代理对象
         *       enhancer.create
         *    创建代理对象的要求
         *       被代理类不能是最终类
         *
         *    create方法的蚕食
         *      class : 被代理对象的字节码
         *      callback:用于提供增强的方法
         *
         *
         */

       Producer  proxyProducer =  (Producer)Enhancer.create(producer.getClass(), new MethodInterceptor() {

            /**
             * 被代理对象的任何方法都会经过此方法
             * @param proxy
             * @param method
             * @param args
             * 以上三个参数和基于接口的动态代理中的invoke方法的参数是一样的
             * @param methodProxy  当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
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
        proxyProducer.saleProduct(10000f);
    }
}
