package org.interview.oauth.twitter.config;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMapperConfig {

	/** Configure mapper here. */
	private static final ObjectMapper mapper = new ObjectMapper()
			.configure(Feature.AUTO_CLOSE_SOURCE, false)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	public static ObjectMapper getJacksonMapper() {
		return mapper;
	}
}
