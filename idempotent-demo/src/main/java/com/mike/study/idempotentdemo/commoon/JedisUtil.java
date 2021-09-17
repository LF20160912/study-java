package com.mike.study.idempotentdemo.commoon;

/*
  Author:Mike
  创建时间：2020/1/31
  描述：
*/

import lombok.extern.slf4j.Slf4j;

@Component

@Slf4j

public classJedisUtil{

@Autowired

private JedisPool jedisPool;

private Jedis getJedis{

        return jedisPool.getResource;

        }

/**
 * 设值
 *
 * @param key
 * @param value
 * @return
 */

public String set(String key,String value){

        Jedis jedis=;

        try{

        jedis=getJedis;

        return jedis.set(key,value);

        }catch(Exception e){

        log.error("set key:{} value:{} error",key,value,e);

        return;

        }finally{

        close(jedis);

        }

        }

/**
 * 设值
 *
 * @param key

 * @param value

 * @param expireTime 过期时间, 单位: s

 * @return

 */

public String set(String key,String value,int expireTime){

        Jedis jedis=;

        try{

        jedis=getJedis;

        return jedis.setex(key,expireTime,value);

        }catch(Exception e){

        log.error("set key:{} value:{} expireTime:{} error",key,value,expireTime,e);

        return;

        }finally{

        close(jedis);

        }

        }

/**
 * 取值
 *
 * @param key

 * @return

 */

public String get(String key){

        Jedis jedis=;

        try{

        jedis=getJedis;

        return jedis.get(key);

        }catch(Exception e){

        log.error("get key:{} error",key,e);

        return;

        }finally{

        close(jedis);

        }

        }

/**
 * 删除key
 *
 * @param key

 * @return

 */

public Long del(String key){

        Jedis jedis=;

        try{

        jedis=getJedis;

        return jedis.del(key.getBytes);

        }catch(Exception e){

        log.error("del key:{} error",key,e);

        return;

        }finally{

        close(jedis);

        }

        }

/**
 * 判断key是否存在
 *
 * @param key

 * @return

 */

public Boolean exists(String key){

        Jedis jedis=;

        try{

        jedis=getJedis;

        return jedis.exists(key.getBytes);

        }catch(Exception e){

        log.error("exists key:{} error",key,e);

        return;

        }finally{

        close(jedis);

        }

        }

/**
 * 设值key过期时间
 *
 * @param key

 * @param expireTime 过期时间, 单位: s

 * @return

 */

public Long expire(String key,int expireTime){

        Jedis jedis=;

        try{

        jedis=getJedis;

        return jedis.expire(key.getBytes,expireTime);

        }catch(Exception e){

        log.error("expire key:{} error",key,e);

        return;

        }finally{

        close(jedis);

        }

        }

/**
 * 获取剩余时间
 *
 * @param key

 * @return

 */

public Long ttl(String key){

        Jedis jedis=;

        try{

        jedis=getJedis;

        return jedis.ttl(key);

        }catch(Exception e){

        log.error("ttl key:{} error",key,e);

        return;

        }finally{

        close(jedis);

        }

        }

        privatevoidclose(Jedis jedis){

        if(!=jedis){

        jedis.close;

        }

        }

        }