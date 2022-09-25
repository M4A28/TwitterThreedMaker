package com.mohammed.mosa.eg.twitterthreedmaker2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mohammed.mosa.eg.twitterthreedmaker2.R;
import com.mohammed.mosa.eg.twitterthreedmaker2.utility.Tweet;
import com.mohammed.mosa.eg.twitterthreedmaker2.utility.TweetsRVAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_main;
    EditText et_entry;
    Button btn_maker;
    TweetsRVAdapter tweetsRVAdapter;
    ArrayList<Tweet> tweets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_main = findViewById(R.id.rv_main);
        et_entry = findViewById(R.id.et_text_entry);
        btn_maker = findViewById(R.id.btn_go);
        tweetsRVAdapter = new TweetsRVAdapter(tweets, this);
        rv_main.setAdapter(tweetsRVAdapter);
        rv_main.setLayoutManager(new LinearLayoutManager(this));

        getDataFromIntent(getIntent());

        btn_maker.setOnClickListener(C ->{
            String text = et_entry.getText().toString();
            if(!text.isEmpty()){
                tweets = Tweet.makeThreed(text);
                tweetsRVAdapter.setTweets(tweets);
                Toast.makeText(this, tweets.size()+"", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(this, "text is empty", Toast.LENGTH_SHORT).show();
        });
    }

    public void getDataFromIntent(Intent intent){
        String action = intent.getAction();
        String type = intent.getType();
        if(Intent.ACTION_SEND.equals(action) && type != null && type.equals("text/plain"))
            et_entry.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
    }

    public void clear(View view) {
        et_entry.setText(null);
        tweets = new ArrayList<>();
        tweetsRVAdapter.setTweets(tweets);
    }

}