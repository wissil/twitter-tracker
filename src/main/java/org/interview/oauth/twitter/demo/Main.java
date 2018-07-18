package org.interview.oauth.twitter.demo;

import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.interview.oauth.twitter.TwitterAuthenticator;
import org.interview.oauth.twitter.TwitterMessageTracker;
import org.interview.oauth.twitter.comparators.TweetComparator;
import org.interview.oauth.twitter.exceptions.TwitterAuthenticationException;
import org.interview.oauth.twitter.model.Tweet;

import com.google.api.client.http.HttpRequestFactory;

public class Main {

	private static final PrintStream OUT = System.out;

	private static final String CONSUMER_KEY = "vp8qXAMoZzy6jowJdtouPLUUb";

	private static final String CONSUMER_SECRET = "IMx3eIRfXXbRimoIz7cNpZCl0dr9dYEdRuDVTr2C4LdResXjN7";
	
	/** Maximum allowed number of received messages. */
	private static final int MESSAGE_TRESHOLD = 100;
	
	/** Amount of time after which we close the messages stream. */
	private static final int TIMEOUT_MILLIS = 10_000;
	
	/** Keyword being tracked from the Twitter streaming API. */
	private static final String TRACK_KEYWORD = "croatia";

	public static void main(String[] args) throws TwitterAuthenticationException, InterruptedException, ExecutionException {
		// authenticate
		TwitterAuthenticator auth = 
				new TwitterAuthenticator(OUT, CONSUMER_KEY, CONSUMER_SECRET);

		HttpRequestFactory factory = auth.getAuthorizedHttpRequestFactory();
		
		// run the tracker
		List<Tweet> tweets = new TwitterMessageTracker(factory, OUT)
				.trackMessageStream(TIMEOUT_MILLIS, MESSAGE_TRESHOLD, TRACK_KEYWORD);
		
		// sort
		tweets.sort(new TweetComparator());
		
		// output
		tweets.stream().forEach(t -> OUT.println(t));
	}
}