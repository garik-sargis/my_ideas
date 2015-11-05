package com.gs.android.data.db;

import android.provider.BaseColumns;

class IdeasContract {
    public static class IdeaEntry {
        public static final String TABLE_NAME = "Idea";
        public static final String COL_ID = BaseColumns._ID;
        public static final String COL_TEXT = "Text";


        public static final String SQL_CREATE_TABLE = DbUtils.createTable(TABLE_NAME)
                .withRowId(COL_ID)
                .withColumn(COL_TEXT, "TEXT", "NOT NULL")
                .create();

        public static final String SQL_DROP_TABLE = DbUtils.dropTableIfExists(TABLE_NAME);
    }
}
