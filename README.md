# README #

Please read the following instructions carefully and make sure that you fulfill
all requirements listed.

## Task ##

We would like you to write code that will cover the functionality listed below
and provide us with the source as well as the output of an execution:

+ Connect to the [Twitter Streaming
  API](https://developer.twitter.com/en/docs/tweets/filter-realtime/overview)
    * Use the following values:
        + Consumer Key: `vp8qXAMoZzy6jowJdtouPLUUb`
        + Consumer Secret: `IMx3eIRfXXbRimoIz7cNpZCl0dr9dYEdRuDVTr2C4LdResXjN7`
    * The app name will be `interview-test`.
    * You will need to login with Twitter.
+ Filter messages that track on "bieber".
+ Retrieve the incoming messages for 30 seconds or up to 100 messages,
  whichever comes first.
+ For each message, we will need the following:
    * The message ID
    * The creation date of the message
    * The text of the message
    * The author of the message
+ For each author, we will need the following:
    * The user ID
    * The creation date of the user
    * The screen name of the user
+ Your application should return the messages grouped by user (users sorted
  chronologically, ascending).
+ The messages per user should also be sorted chronologically, ascending.
+ Print this information to the command line in a way that you consider
  suitable.

## Provided functionality ##

+ The archive in itself is a [Maven project](https://maven.apache.org/) that
  contains functionality that will provide you with a
  `com.google.api.client.http.HttpRequestFactory` that is authorised to execute
  calls to the Twitter API in the scope of a specific user.
+ You will need to provide your _Consumer Key_ and _Consumer Secret_ and follow
  through the OAuth process (get a temporary token, retrieve the access URL,
  authorise the application, enter the PIN for the authenticated token). With
  the resulting factory you are able to generate and execute all necessary
  requests.
+ Feel free to add/modify dependendencies in `pom.xml`.
+ If you want to, you can also forego the provided classes and create your own
  but **do not use premade libraries** to interact with Twitter.

## Tips ##

+ We value clean, readable and idiomatic Java. Your code will be reviewed by
  other developers, so make sure it is easy to follow and well-structured.
+ Avoid doing manual JSON parsing. It's prone to errors and hard to read.
+ Don't feel the need to over-engineer your solution. We don't expect a you to
  build an entire system that can scale to billions of tweets. Your solution
  should be tailored to the problem statement. We prefer concise and simple
  solutions over lengthy ones.
+ Read the Twitter docs carefully and make sure you use the correct part of
  their API.
