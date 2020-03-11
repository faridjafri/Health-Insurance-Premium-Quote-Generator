package com.emids.hipqg.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {

	public static Object parsePayload(String input, Class<?> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(input, clazz);
	}

}
