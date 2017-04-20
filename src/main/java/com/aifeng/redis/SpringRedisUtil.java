package com.aifeng.redis;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class SpringRedisUtil {

	@SuppressWarnings("unchecked")
	private static RedisTemplate<String, Serializable> redisTemplate = 
				(RedisTemplate<String, Serializable>) ApplicationContextUtil
						.getBean("redisTemplate");
	
	
	private static RedisClientTemplate  redisClientTemplate =  (RedisClientTemplate) ApplicationContextUtil.getBean("redisClientTemplate");
	
	
	/**
	 * 是否是基础类型
	 * @return
	 */
//	public static boolean isPrimitiveType(Object obj){
//		boolean ret = false;
//		if(obj instanceof java.lang.Integer){
//			ret  = true;
//		}
//		else if(obj instanceof java.lang.String){
//			ret  = true;
//		}
//		else if(obj instanceof java.lang.Double){
//			ret  = true;
//		}
//		else if(obj instanceof java.lang.Float){
//			ret  = true;
//		}
//		else if(obj instanceof java.lang.Long){
//			ret  = true;
//		}
//		else{
//			ret = false;
//		}
//		
//		return ret;
//	}
	
	
	public static void set(final String key, String value){
		redisClientTemplate.set(key, value.toString());
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param timeout   单位s
	 */
	public static void set(final String key, String value, int timeout){
		redisClientTemplate.set(key, value.toString());
		redisClientTemplate.expire(key, timeout);
	}
	
	
	public static void setObject(final String key, Object value) {
		final byte[] vbytes = SerializeUtil.serialize(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key), vbytes);
				return null;
			}
		});
	}
	
	public static void setObject(final String key, Object value ,int timeout) {
			final byte[] vbytes = SerializeUtil.serialize(value);
			redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection)
						throws DataAccessException {
					connection.set(redisTemplate.getStringSerializer().serialize(key), vbytes);
					return null;
				}
			});
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	
	public static String get(final String key){
		return redisClientTemplate.get(key);
	}

	public static <T> T getObject(final String key, Class<T> clazz) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keybytes = redisTemplate.getStringSerializer().serialize(key);
				if (connection.exists(keybytes)) {
					byte[] valuebytes = connection.get(keybytes);
					@SuppressWarnings("unchecked")
					T value = (T) SerializeUtil.unserialize(valuebytes);
					return value;
				}
				return null;
			}
		});
	}
	
	
	
	/**
	 * 
	 * @param key
	 * @param timeout
	 * @param isPrimitiveType			值是否是基础类型 
	 */
	public static void expire(String key, int timeout, boolean isPrimitiveType) {
			 redisClientTemplate.expire(key, timeout);
	}
	
	public static void expireObject(String key, int timeout) {
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	
	
	
	
	
}