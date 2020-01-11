package com.tc.cglib;

public interface Iproducer {

    /**
     * 一个销售
     * @param money
     */
    public void saleProduct(float money);

    /**
     * s售后
     * @param money
     */
    public void afterService(float money);
}
