package com.carwel.webmagic.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedisUtil {

    @Autowired
    JedisPool jedisPool;

    // 获取jedis
    public Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    /**
     * 获取值
     * 
     * @param
     * @param key
     * @param clazz
     * @return
     */
    public <T> T get( String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnPool(jedis);
        }

    }

    /**
     * 设置值
     * 
     * @param
     * @param key
     * @param value
     * @return
     */
    public <T> boolean set( String key, T value,int time) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() < 0) {
                return false;
            }
           jedis.setex(key,time,str);
            return true;
        } finally {
            returnPool(jedis);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }

        if (clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T)str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T)Long.valueOf(str);
        } else {
            return (T) JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    public <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String)value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * 判断某个值是否存在
     * 
     * @param key
     * @param key
     * @return
     */
    public <T> boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            returnPool(jedis);
        }
    }

    /**
     * 删除
     * 
     * @param key
     * @param key
     * @return
     */
    public boolean delete( String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long ret = jedis.del(key);
            return ret > 0;
        } finally {
            returnPool(jedis);
        }
    }

    /**
     * 增加
     * 
     * @param
     * @param key
     */
    public <T> Long incr( String key,int num) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incrBy(key,num);
        } finally {
            returnPool(jedis);
        }
    }

    /**
     * 增加
     * 
     * @param num
     * @param key
     */
    public <T> Long decr( String key, int num ) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decrBy(key,num);
        } finally {
            returnPool(jedis);
        }
    }

   /* public boolean delete(String key) {
        if (key == null) {
            return false;
        }
        List<String> keys = scanKeys(key.getPrefix());
        if (keys == null || keys.size() <= 0) {
            return true;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(keys.toArray(new String[0]));
            return true;
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
*/
    public List<String> scanKeys(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> keys = new ArrayList<String>();
            String cursor = "0";
            ScanParams sp = new ScanParams();
            sp.match("*" + key + "*");
            sp.count(100);
            do {
                ScanResult<String> ret = jedis.scan(cursor, sp);
                List<String> result = ret.getResult();
                if (result != null && result.size() > 0) {
                    keys.addAll(result);
                }
                // 再处理cursor
                cursor = ret.getStringCursor();
            } while (!cursor.equals("0"));
            return keys;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void returnPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }

    }

}
