package org.interview.oauth.twitter.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	private final long id;
	
	@JsonFormat(pattern = "EEE MMM dd HH:mm:ss ZZZZZ yyyy")
	private final Date creationDate;
	
	private final String screenName;

	@JsonCreator
	public User(
			@JsonProperty(value = "id") long id, 
			@JsonProperty(value = "created_at") Date creationDate, 
			@JsonProperty(value = "screen_name") String screenName) {
		this.id = id;
		this.creationDate = creationDate;
		this.screenName = screenName;
	}

	@JsonProperty(value = "id")
	public long getId() {
		return id;
	}

	@JsonProperty(value = "created_at")
	public Date getCreationDate() {
		return creationDate;
	}

	@JsonProperty(value = "screen_name")
	public String getScreenName() {
		return screenName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((screenName == null) ? 0 : screenName.hashCode());
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
		User other = (User) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id != other.id)
			return false;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", creationDate=" + creationDate + ", screenName=" + screenName + "]";
	}
}
