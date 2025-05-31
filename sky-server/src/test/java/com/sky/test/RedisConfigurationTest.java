//package com.sky.test;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@Slf4j
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class RedisConfigurationTest {
//
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Test
//    public void testRedis() {
//        log.info("redisTemplate:{}", redisTemplate);
//    }
//
//
//    @Test
//    public void testRedis2() {
//        redisTemplate.opsForValue()
//                     .set("name", "amy");
//        String name = (String) redisTemplate.opsForValue()
//                                            .get("name");
//        System.out.println(name);
//    }
//}