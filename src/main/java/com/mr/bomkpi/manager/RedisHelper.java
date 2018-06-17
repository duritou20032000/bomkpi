package com.mr.bomkpi.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

class RedisHelper {
    private static Logger logger = LoggerFactory.getLogger(RedisHelper.class);

    static JedisPool connect(String host, int port, String password ) {
        // Create and set a JedisPoolConfig
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // retrieval method is called
        poolConfig.setTestOnBorrow(false);

        // Tests whether connection is dead when returning a
        // connection to the pool
        poolConfig.setTestOnReturn(false);
        // Number of connections to Redis that just sit there
        // and do nothing
        poolConfig.setMaxIdle(20);
        // Minimum number of idle connections to Redis
        // These can be seen as always open and ready to serve
        poolConfig.setMinIdle(10);
        poolConfig.setMaxTotal(50);
        // Tests whether connections are dead during idle periods
        poolConfig.setTestWhileIdle(true);
        // Maximum number of connections to test in each idle check
        poolConfig.setNumTestsPerEvictionRun(10);
        // Idle connection checking period
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        //最多等待30S拿连接
        poolConfig.setMaxWaitMillis(30 * 1000);
        // Create the jedisPool
        return new JedisPool(poolConfig, host, port, 5000, password);
    }
}
