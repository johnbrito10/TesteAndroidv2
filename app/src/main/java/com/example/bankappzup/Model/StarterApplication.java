package com.example.bankappzup.Model;


import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Habilite armazenamento local.
        Parse.enableLocalDatastore(this);

        // Codigo de configuração do App
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("b90c97e1-4261-4a34-a348-a0604f0264a7")
                .clientKey(null)
                .server("https://bank-app-test.herokuapp.com/api/login")
                .build()
        );


        ParseACL defaultACL = new ParseACL();

        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);


        ParseACL.setDefaultACL(defaultACL, true);
    }
}
