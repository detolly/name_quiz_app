package com.example.thenamequiz;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context s_context;
    private static Database s_database;

    @Override
    public void onCreate()
    {
        super.onCreate();
        s_context = this;
        s_database = new Database();
    }

    public static Context context() {
        return s_context;
    }

    public static Database database() {
        return s_database;
    }


}
