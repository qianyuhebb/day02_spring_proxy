package com.tc.proxy;


/**
 * 一个生产者
 */
 class Producer implements Iproducer{
    /**
     * 一个销售
     * @param money
     */
    public void saleProduct(float money){
        System.out.println("销售产品，并且拿到钱，"+money);
    }

    /**
     * s售后
     * @param money
     */
    public void afterService(float money){
        System.out.println("提供售后服务，并且拿到钱，"+money);
    }
}
