package com.gs.android.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gs.android.data.db.IdeasContract.IdeaEntry;

public class DbHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "ideas.db";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(IdeaEntry.SQL_CREATE_TABLE);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: Implement propper upgrade logic
        db.execSQL(IdeaEntry.SQL_DROP_TABLE);
    }
}
