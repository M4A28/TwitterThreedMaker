package com.mohammed.mosa.eg.twitterthreedmaker2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mohammed.mosa.eg.twitterthreedmaker2.utility.Tweet;

public class DBTweets extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DB_NAME = "Tweets.db";
    public static final String TB_Tweets = "tweet";
    public static final String TB_FAV = "fav";
    public static final String CLM_ID = "id";
    public static final String CLM_TWEET = "tweet";
    public static final String CLM_LENGTH = "tweet_length";
    public static final String CLM_DATE = "date_created";
    public static final String CLM_IS_FAV = "isFav";
    public DBTweets(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " +  TB_Tweets +
                " (" + CLM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CLM_TWEET +" TEXT,"
                + CLM_LENGTH + " INTEGER, "
                + CLM_DATE + " TEXT, "
                + CLM_IS_FAV +  "INTEGER NOT NULL DEFAULT 0 " + ")");

        sqLiteDatabase.execSQL("CREATE TABLE " +  TB_FAV +
                " (" + CLM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CLM_TWEET +" TEXT,"
                + CLM_LENGTH + " INTEGER, "
                + CLM_DATE + " TEXT, "
                + CLM_IS_FAV +  "INTEGER NOT NULL DEFAULT 0 " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(Tweet tweet, String table){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CLM_TWEET, tweet.getTweet());
        values.put(CLM_LENGTH, tweet.getTweetLength());
        values.put(CLM_DATE, tweet.getDate());
        values.put(CLM_IS_FAV, tweet.isFava());
        long result = db.insert(table, null, values);
        return  result != -1;
    }
}
