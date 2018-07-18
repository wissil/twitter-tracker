package org.interview.oauth.twitter.test.model;

import java.io.IOException;

import org.interview.oauth.twitter.config.JacksonMapperConfig;
import org.interview.oauth.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;

public class TweetTest {
	
	private JsonNode json;
	
	private ObjectMapper mapper;
	
	@Before
	public void setup() throws IOException {
		this.mapper = JacksonMapperConfig.getJacksonMapper();
		this.json = JsonLoader.fromResource("/json/example_tweet.json");
	}

	@Test
	public void testFromJsonCreation() throws JsonProcessingException {
		/*
		 * Shouldn't throw!
		 */
		mapper.treeToValue(json, Tweet.class);
	}
}
