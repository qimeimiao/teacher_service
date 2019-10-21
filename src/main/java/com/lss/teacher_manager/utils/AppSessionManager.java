package com.lss.teacher_manager.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class AppSessionManager {
	
	protected final static Logger log = LoggerFactory.getLogger(AppSessionManager.class);

	@Value("${server.session.timeout}")
	private static int  MAX_SESSION_TIME_MINUTES;
	
	private static final Map<String, Object> sessionMap = new HashMap<>();
	private static final Map<String, Date> keyMap = new HashMap<>();
	
	private static final Object obj=new Object();

	public static final String LOGINTIME="loginTime";
	
	
	@Scheduled(fixedRate = 1000)
	private void checkSession() {
		synchronized (obj) {
			Date curDate = new Date();
			List<String> deleteList=new ArrayList<>();
			for (String key : keyMap.keySet()) {
				if (curDate.getTime() > keyMap.get(key).getTime()) {
					deleteList.add(key);
				}
			}
			for (Iterator<String> iterator = deleteList.iterator(); iterator.hasNext();) {
				String key = iterator.next();
				log.debug("EXPIRED,DEL KEY："+key);
				keyMap.remove(key);
				sessionMap.remove(key);
			}
		}
	}
	
	public static void refresh(String key) {
		synchronized (obj) {
			if(!keyMap.containsKey(key)){
				throw new RuntimeException("["+key+"] KEY NOT EXIST");
			}
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.MINUTE, MAX_SESSION_TIME_MINUTES);
			keyMap.put(key, calendar.getTime());
		}
	}
	
	public static void put4forever(String key,Object obj) {
		synchronized (obj) {
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.YEAR, 1);
			putWithEndTime(key,obj,calendar.getTime());
		}
	}
	public static void put(String key,Object obj) {
		synchronized (obj) {
			keyMap.put(key, new Date());
			sessionMap.put(key, obj);
		}
	}
	
	/**
	 * 该缓存指定时间内有效
	 * @param key
	 * @param obj
	 * @param endTime 结束时间
	 */
	public static void putWithEndTime(String key,Object obj,Date endTime) {
		Date curDate = new Date();
		if(endTime.getTime()<=curDate.getTime()){
			throw new RuntimeException("endTime不能小于当前时间");
		}
		synchronized (obj) {
			keyMap.put(key, endTime);
			sessionMap.put(key, obj);
		}
	}


	public static void put(String token,String key,Object valueObj) {
		synchronized (obj) {
			log.debug("登陆token:"+token);
			if(!keyMap.containsKey(token)){
				
				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.MINUTE, MAX_SESSION_TIME_MINUTES);
				keyMap.put(token, calendar.getTime());
				
				Map<String, Object> valueMap=new HashMap<>();
				valueMap.put(key, valueObj);
				sessionMap.put(token, valueMap);
			}else {
				Map<String, Object> valueMap= (Map<String, Object>)sessionMap.get(token);
				if (valueMap == null) {
					valueMap=new HashMap<>();
					sessionMap.put(token, valueMap);
				}
				valueMap.put(key, valueObj);
			}
		}
	}
	public static void put(String key,Object valueObj,int expireTime) {
		synchronized (obj) {
			if(!keyMap.containsKey(key)){

				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.SECOND, expireTime);
				keyMap.put(key, calendar.getTime());

				Map<String, Object> valueMap=new HashMap<>();
				valueMap.put(key, valueObj);
				sessionMap.put(key, valueMap);
			}else {
				Map<String, Object> valueMap= (Map<String, Object>)sessionMap.get(key);
				if (valueMap == null) {
					valueMap=new HashMap<>();
					sessionMap.put(key, valueMap);
				}
				valueMap.put(key, valueObj);
			}
		}
	}
	
	
	public static Object get(String token,String key) {
		Map<String, Object> valueMap= (Map<String, Object>)sessionMap.get(token);
		if(valueMap==null){
			return null;
		}
		return valueMap.get(key);
	}
	
	
	public static Object get(String key) {
		return sessionMap.get(key);
	}
	
	
	public static void remove(String key) {
		synchronized (obj) {
			keyMap.remove(key);
			sessionMap.remove(key);
		}
	}

//	public static UserDto getLoginUser(){
//		UserDto loginUser = (UserDto) AppSessionManager.get("loginUser");
//		return loginUser;
//	}
}
