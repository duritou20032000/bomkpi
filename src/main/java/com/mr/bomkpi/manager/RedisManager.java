package com.mr.bomkpi.manager;

import com.mr.bomkpi.config.AppConfig;
import com.mr.bomkpi.util.DateUtil;
import com.mr.bomkpi.util.StackUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

public class RedisManager {

    public static final int REDIS_DEFAULT_DB = 0;
    public static final int REDIS_INV_DB = 1;
    public static final int REDIS_SCAN_SIZE = 1000;

    private static Logger logger = LoggerFactory.getLogger(RedisManager.class);
    private static final RedisManager instance = new RedisManager();
    private static JedisPool pool;
    private static Object lock = new Object();

    private String host;
    private String password = null;
    private int port = 6379;

    private RedisManager() {
        String redis = AppConfig.getInstance().getProperty("redis");
        if (redis != null) {
            String[] g = redis.split(":");
            host = g[0];
            if (g.length > 1) {
                port = Integer.valueOf(g[1]);
            }
        }
        password = AppConfig.getInstance().getProperty("redis.password");
    }

    public static RedisManager getInstance() {
        return instance;
    }

    public boolean isEnabled() {
        return host != null;
    }

    public void release() {
        pool.destroy();
    }

    public Jedis getJedis() {
        return this.getJedis(REDIS_DEFAULT_DB);
    }

    public Jedis getJedis(int db) {
        if (pool == null) {
            synchronized (lock) {
                if (pool == null) {
                    pool = RedisHelper.connect(host, port, password);
                }
            }
        }

        Jedis jedis = JedisProxy.proxy(pool.getResource());

        if (jedis.getDB() != db) {
            jedis.select(db);
        }
        return jedis;
    }

    public static class JedisProxy {

        private static Hashtable conns = new Hashtable();

        public static Collection getHoldingConns() {
            return conns.values();
        }

        public static Jedis proxy(Jedis jedis) {

            conns.put(jedis.hashCode(), DateUtil.getCurrentDate() + " => " + StackUtil.getCaller());

            return (Jedis) Enhancer.create(Jedis.class, (InvocationHandler) (proxy, method, args) -> {
                if ("close".equals(method.getName())) {
                    conns.remove(jedis.hashCode());
                }
                return method.invoke(jedis, args);
            });
        }
    }

    public void returnJedis(Jedis jedis) {
        jedis.close();
    }

    public long getSequence(String seqName) {
        String key = "SEQUENCE_" + seqName;
        Jedis jedis = getJedis();
        long seq = jedis.incr(key);
        jedis.close();
        return seq;
    }

    public void set(String key, String value) {
        set(key, value, REDIS_DEFAULT_DB);
    }

    public void set(String key, Integer value, Integer db) {
        set(key, String.valueOf(value), db);
    }

    public void set(String key, Integer value) {
        set(key, value, REDIS_DEFAULT_DB);
    }

    public void set(String key, String value, Integer db) {
        Jedis jedis = getJedis(db);
        jedis.set(key, value);
        jedis.close();
    }

    public void setex(String key, int seconds, Integer value) {
        setex(key, seconds, String.valueOf(value), REDIS_DEFAULT_DB);
    }

    public void setex(String key, int seconds, Integer value, Integer db) {
        setex(key, seconds, String.valueOf(value), db);
    }

    public void setex(String key, int seconds, String value) {
        setex(key, seconds, value, REDIS_DEFAULT_DB);
    }

    public void setex(String key, int seconds, String value, Integer db) {
        Jedis jedis = getJedis(db);
        jedis.setex(key, seconds, value);
        jedis.close();
    }

    public String get(String key) {
        return get(key, REDIS_DEFAULT_DB);
    }

    public Integer getInteger(String key) {
        return getInteger(key, REDIS_DEFAULT_DB);
    }

    public Integer getInteger(String key, Integer db) {
        return Integer.valueOf(get(key, db));
    }

    public String get(String key, Integer db) {
        Jedis jedis = getJedis(db);
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    public boolean exists(String key) {
        return exists(key, REDIS_DEFAULT_DB);
    }

    public boolean exists(String key, Integer db) {
        Jedis jedis = getJedis(db);
        boolean b = jedis.exists(key);
        jedis.close();
        return b;
    }

    public void del(String key) {
        del(key, REDIS_DEFAULT_DB);
    }

    public void del(String key, Integer db) {
        Jedis jedis = getJedis(db);
        jedis.del(key);
        jedis.close();
    }

    public Set<String> getKeys(String key) {
        return getKeys(key, REDIS_DEFAULT_DB);
    }

    public Set<String> getKeys(String key, Integer db) {
        Jedis jedis = getJedis(db);
        Set<String> keys = jedis.keys(key);
        jedis.close();
        return keys;
    }

    public boolean lockThenRun(String lockKey, Runnable runnable) {
        boolean b = false;
        try (Jedis jedis = RedisManager.getInstance().getJedis()) {
            long ret = jedis.setnx(lockKey, String.valueOf(System.currentTimeMillis()));
            //锁拿到返回1，否则0
            if (ret > 0) {
                jedis.expire(lockKey, 3 * 60);  //3分钟自动释放
                runnable.run();
                jedis.del(lockKey);  //事情做完后删除锁

                b = true;
            }
        }
        return b;
    }

    public String hget(String key, String field) {
        Jedis jedis = getJedis();
        String value = jedis.hget(key, field);
        jedis.close();
        return value;
    }

    public void hdel(String key, String field) {
        Jedis jedis = getJedis();
        jedis.hdel(key, field);
        jedis.close();
    }
}
