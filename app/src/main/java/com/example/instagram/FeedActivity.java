package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    protected PostsAdapter adapter;
    protected List<Post> allPosts;
    public RecyclerView rvPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvPosts = findViewById(R.id.rvPosts);
        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(this, allPosts);

        rvPosts.setAdapter(adapter);
        // set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        // query posts from Parstagram
        queryPosts();
    }
    private void queryPosts() {
        // specify what type of data we want to query - Post.class
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
     
        query.include(Post.KEY_USER);
     
        query.setLimit(20);
       
        query.addDescendingOrder("createdAt");
        
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                // check for errors
                if (e != null) {
                    Log.e("posts", "Issue with getting posts", e);
                    return;
                }

                // for debugging purposes let's print every post description to logcat
                for (Post post : posts) {
                    Log.i("posts", "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }

                // save received posts to list and notify adapter of new data
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }


}
