package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Register created parse model from back4app
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("nX2gIqtlwsPkKw17qP5CiHerG7sG4O42jcDbexGT")
                .clientKey("RPwFTKhOits3kMTqQgY6xFVY6n0Ch5hcqegTBu8F")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}
