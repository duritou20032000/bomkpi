package com.mr.bomkpi.manager;

public class CrossAppContext {

    private static CrossAppContext instance = null;
    private static Object lock = new Object();

    private CrossAppContext() {
    }

    public static CrossAppContext getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new CrossAppContext();
                }
            }
        }
        return instance;
    }

    public boolean isEnabled() {
        return RedisManager.getInstance().isEnabled();
    }

    public void setString(String key, String value) {
        RedisManager.getInstance().set(key, (String) value);
    }

    public String getString(String key) {
        return RedisManager.getInstance().get(key);
    }

    public void delete(String key) {
        RedisManager.getInstance().del(key);
    }

    public void setString(String key, int exp, String value) {
        RedisManager.getInstance().setex(key, exp, (String) value);
    }


}
