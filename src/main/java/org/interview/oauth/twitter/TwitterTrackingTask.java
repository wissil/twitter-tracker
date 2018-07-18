package org.interview.oauth.twitter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import org.interview.oauth.twitter.config.JacksonMapperConfig;
import org.interview.oauth.twitter.exceptions.TwitterApiException;
import org.interview.oauth.twitter.model.Tweet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;

public class TwitterTrackingTask implements Callable<Void> {
	
	/** Base URL for tracking messages from Twitter. */
	private static final String MESSAGE_STREAM_BASE_URL = "https://stream.twitter.com/1.1/statuses/filter.json?track=";

	/** Pre-configured Jackson mapper instance. */
	private static final ObjectMapper mapper = JacksonMapperConfig.getJacksonMapper();

	private final HttpRequestFactory requestFactory;

	private final PrintStream out;
	
	private final List<Tweet> tweets;
	
	private final String trackKeyword;
	
	private final int messageTreshold;
	
	public TwitterTrackingTask(HttpRequestFactory requestFactory, PrintStream out, int messageTreshold, String trackKeyword) {
		this.requestFactory = Objects.requireNonNull(requestFactory);
		this.out = Objects.requireNonNull(out);
		
		this.tweets = new ArrayList<>();
		this.trackKeyword = trackKeyword;
		this.messageTreshold = messageTreshold;
	}

	@Override
	public Void call() throws Exception {
		final String streamApiUrl = MESSAGE_STREAM_BASE_URL.concat(trackKeyword);		

		out.println(String.format("Calling streaming api @ %s ...", streamApiUrl));
		final HttpResponse resp = callTwitterStreamingApi(streamApiUrl);

		try (InputStream is = resp.getContent()) {
			out.println("Starting to stream messages ...");
			while (true) {
				Tweet tweet = mapper.readValue(is, Tweet.class);
				tweets.add(tweet);

				if (tweets.size() == messageTreshold) {
					out.println(String.format("Message treshold of %d messages is reached.", messageTreshold));
					break;
				}
			}
		} catch (Exception e) {
			throw new TwitterApiException("Error communicating with Twitter API.", e);
		}
		
		return null;
	}
	
	private HttpResponse callTwitterStreamingApi(String streamApiUrl) {
		try {
			final HttpRequest req = requestFactory.buildGetRequest(new GenericUrl(streamApiUrl));
			final HttpResponse resp = req.execute();

			return resp;
		} catch (IOException e) {
			throw new TwitterApiException(
					String.format("Error calling Twitter API at %s.", streamApiUrl), e);
		}
	}
	
	public List<Tweet> getTweets() {
		return Collections.unmodifiableList(tweets);
	}
}
