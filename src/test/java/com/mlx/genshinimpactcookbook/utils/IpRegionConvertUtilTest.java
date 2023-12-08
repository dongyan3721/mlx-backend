package com.mlx.genshinimpactcookbook.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class IpRegionConvertUtilTest {

    @Test
    void ipConvertToAddress() {
        System.out.println(IpRegionConvertUtil.ipConvertToAddress("121.40.131.167"));
        System.out.println(IpRegionConvertUtil.ipConvertToAddress("10.19.192.167"));
    }
}