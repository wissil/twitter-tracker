package org.interview.oauth.twitter.comparators;

import java.util.Comparator;

import org.interview.oauth.twitter.model.Tweet;

public class TweetComparator implements Comparator<Tweet> {
	
	private final Comparator<Tweet> comparator;
	
	public TweetComparator() {
		this.comparator = Comparator.comparing((Tweet tweet) -> tweet.getUser().getCreationDate())
				  .thenComparing((Tweet tweet) -> tweet.getUser().getId())
				  .thenComparing((Tweet tweet) -> tweet.getCreationDate());
	}

	@Override
	public int compare(Tweet t1, Tweet t2) {
		return comparator.compare(t1, t2);
	}

}
