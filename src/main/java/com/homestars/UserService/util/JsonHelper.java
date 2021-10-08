package com.homestars.UserService.util;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author nchopra
 *
 */
@Component
public class JsonHelper {
	@Autowired
	private ObjectMapper objectMapper;

	public <T> String toJson(T toMarshal) {
		try {
			return objectMapper.writeValueAsString(toMarshal);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * unmarshal the object from json to java object
	 *
	 * @param json, clazz
	 * @return T
	 */
	public <T> T fromJson(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * unmarshal the object from json to java object
	 *
	 * @param json, valueTypeRef
	 * @return T
	 */
	public <T> T fromJson(String json, TypeReference<T> valueTypeRef) {
		try {
			return objectMapper.readValue(json, valueTypeRef);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * checks for valid json
	 *
	 * @param jsonInString
	 * @return boolean
	 */
	public boolean isJSONValid(String jsonInString) {
		try {
			objectMapper.readTree(jsonInString);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public <T> T convertValue(Object object, Class<T> clazz) {
		try {
			return objectMapper.convertValue(object, clazz);
		} catch (Exception e) {
			return null;
		}
	}
}
