package com.mohammed.mosa.eg.twitterthreedmaker2.utility;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammed.mosa.eg.twitterthreedmaker2.R;

import java.util.ArrayList;

public class TweetsRVAdapter extends RecyclerView.Adapter<TweetsRVAdapter.TweetViewHolder> {
    private ArrayList<Tweet> tweets;
    private Context context;

    public TweetsRVAdapter(ArrayList<Tweet> tweets, Context context) {
        this.tweets = tweets;
        this.context = context;
    }

    public void setTweets(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
        notifyDataSetChanged();
    }


    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TweetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_layout_view, parent, false);
        return new TweetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public class TweetViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tweet_text;
        TextView tv_tweet_length;
        ImageView iv_tweet;
        ImageView iv_share;
        ImageView iv_copy;

        public TweetViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tweet_text = itemView.findViewById(R.id.tv_tweet_text);
            tv_tweet_length = itemView.findViewById(R.id.tv_tweet_length);
            iv_tweet = itemView.findViewById(R.id.iv_twitter_share);
            iv_share = itemView.findViewById(R.id.iv_all_share);
            iv_copy = itemView.findViewById(R.id.iv_copy);

        }

        public void bind(Tweet tweet){
            tv_tweet_text.setText(tweet.getId() +"- "+ tweet.getTweet());
            tv_tweet_length.setText(String.format("%s char", tweet.getTweetLength()));

            iv_tweet.setOnClickListener(K -> {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setPackage("com.android.tweeter");
                    intent.setType("text/palne");
                    intent.putExtra(Intent.EXTRA_TEXT, tweet.getTweet());
                    context.startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(context, "Twitter not instalde", Toast.LENGTH_SHORT).show();
                }
            });
            iv_share.setOnClickListener(K ->{
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, tweet.getTweet());
                context.startActivity(Intent.createChooser(intent, "share using..."));
            });

            iv_copy.setOnClickListener(K -> {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData =  ClipData.newPlainText("tweet", tweet.getTweet());
                clipboard.setPrimaryClip(clipData);
                Toast.makeText(context, "Copying", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
