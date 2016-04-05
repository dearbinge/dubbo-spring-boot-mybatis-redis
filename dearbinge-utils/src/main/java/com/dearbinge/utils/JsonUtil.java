package com.dearbinge.utils;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {
	private static ObjectMapper mapper = null;
	private static Boolean singleInstance = true;

	/**
	 * @author neo
	 * @param singleinstance
	 *            是否为单例模式
	 * @return
	 */
	private static void getObjectMapperInstance() {
		if (singleInstance) {
			if (mapper == null) {
				synchronized (JsonUtil.class) {
					if (mapper == null) {
						mapper = new ObjectMapper();
					}
				}
			}
		} else {
			mapper = new ObjectMapper();
		}
	}

	public static String convertObject2String(Object arg0) throws JsonProcessingException {
		getObjectMapperInstance();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true); // 序列化默认以类名为根元素
		return mapper.writeValueAsString(arg0);
	}

	public static <T> T convertString2Object(String content, Class<T> arg0)
			throws JsonParseException, JsonMappingException, IOException {
		getObjectMapperInstance();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true); // 序列化默认以类名为根元素
		T t = mapper.readValue(content, arg0);
		return t;
	}

	public static JsonNode getJsonNodeByKey(String content, String key) throws JsonProcessingException, IOException {
		getObjectMapperInstance();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);// 序列化默认以类名为根元素
		JsonNode jsonNode = mapper.readTree(content);
		return jsonNode.findValue(key);
	}
}
