package org.interview.oauth.twitter.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {

	private final long id;
	
	@JsonFormat(pattern = "EEE MMM dd HH:mm:ss ZZZZZ yyyy")
	private final Date creationDate;
	
	private final String text;
	
	private final User user;

	@JsonCreator
	public Tweet(
			@JsonProperty(value = "id") long id, 
			@JsonProperty(value = "created_at") Date creationDate, 
			@JsonProperty(value = "text") String text, 
			@JsonProperty(value = "user") User user) {
		this.id = id;
		this.creationDate = creationDate;
		this.text = text;
		this.user = user;
	}

	@JsonProperty(value = "id")
	public long getId() {
		return id;
	}

	@JsonProperty(value = "created_at")
	public Date getCreationDate() {
		return creationDate;
	}

	@JsonProperty(value = "text")
	public String getText() {
		return text;
	}

	@JsonProperty(value = "user")
	public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id != other.id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", creationDate=" + creationDate + ", text=" + text + ", author=" + user + "]";
	}
	
}
