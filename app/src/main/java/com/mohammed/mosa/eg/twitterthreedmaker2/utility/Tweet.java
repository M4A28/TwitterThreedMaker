package com.mohammed.mosa.eg.twitterthreedmaker2.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Tweet {

    private int id;
    private String tweet;
    private int tweetLength;
    private String date;
    private boolean isFava;
    public static int tweetCount = 0;

    public Tweet() {
    }


    public Tweet(int id, String tweet, int tweetLength, String date, boolean isFava) {
        this.id = id;
        this.tweet = tweet;
        this.tweetLength = tweetLength;
        this.date = date;
        this.isFava = isFava;
        tweetCount++;
    }

    public Tweet(int id, String tweet, int tweetLength, String date) {
        this.id = id;
        this.tweet = tweet;
        this.tweetLength = tweetLength;
        this.date = date;
        this.isFava = false;
        tweetCount++;
    }

    public Tweet(String tweet, int tweetLength, String date) {
        this.tweet = tweet;
        this.tweetLength = tweetLength;
        this.date = date;
        tweetCount++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public int getTweetLength() {
        return tweetLength;
    }

    public void setTweetLength(int tweetLength) {
        this.tweetLength = tweetLength;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFava() {
        return isFava;
    }

    public void setFava(boolean fava) {
        isFava = fava;
    }

    public static ArrayList<Tweet> makeThreed(String text) {
        int tweetID = 0;
        int last_step = 0;
        String[] words =  text.split("[\\n\\t\\s+]");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ArrayList<Tweet> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(String word: words){
            if((stringBuilder.length() + word.length() ) < 280 )
                stringBuilder.append(word).append(" ");
            else  {
                tweetID++;
                last_step += stringBuilder.length();
                result.add(new Tweet(tweetID, stringBuilder.toString().trim(), stringBuilder.length(), dateFormat.format(new Date())));
                stringBuilder = new StringBuilder();
                stringBuilder.append(word).append(" ");
            }
        }
        String last_t = text.substring(last_step);
        tweetID++;

        result.add(new Tweet(tweetID, last_t, last_t.length(), dateFormat.format(new Date())));
        return result;
    }

}
