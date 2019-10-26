package com.nny.Demo;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zxdong on 2018/4/16.
 */
public class DemoTest {

    Logger logger = LoggerFactory.getLogger(DemoTest.class);

    @Test
    public void test1() throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] bytes = digest.digest("user".getBytes("gbk"));
        String encode = HexBin.encode(bytes);
        System.out.println(encode);
    }

    @Test
    public void test2() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void log() {
        String username = "user";
        logger.debug("用户名 {} 不存在", username);
        logger.info("info");
        logger.warn("warn");
    }
}
