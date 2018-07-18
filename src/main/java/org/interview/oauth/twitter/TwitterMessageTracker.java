package org.interview.oauth.twitter;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.interview.oauth.twitter.model.Tweet;

import com.google.api.client.http.HttpRequestFactory;

public class TwitterMessageTracker {

	private static final int NUMBER_OF_THREADS = 4;

	private final HttpRequestFactory requestFactory;

	private final PrintStream out;

	private final ExecutorService executor;

	public TwitterMessageTracker(HttpRequestFactory requestFactory, PrintStream out) {
		this.requestFactory = Objects.requireNonNull(requestFactory);
		this.out = Objects.requireNonNull(out);

		this.executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
	}
	
	public List<Tweet> trackMessageStream(int timeoutMS, int messageTreshold, String trackKeyword) throws InterruptedException {		
		TwitterTrackingTask task = new TwitterTrackingTask(requestFactory, out, messageTreshold, trackKeyword);
		
		executor.invokeAll(Arrays.asList(task), timeoutMS, TimeUnit.MILLISECONDS);
		executor.shutdown();
		
		return task.getTweets();
	}
}
