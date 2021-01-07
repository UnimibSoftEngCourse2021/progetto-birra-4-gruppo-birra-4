package com.example.brewdayapplication;

import android.content.Context;

public class DatabaseManager {
    private DatabaseHelper databaseHelper;

    public DatabaseManager(Context ctx) {
        databaseHelper = new DatabaseHelper(ctx);
    }

}