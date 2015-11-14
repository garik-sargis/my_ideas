package com.gs.android.myideas.data.db;

import android.support.annotation.NonNull;

public final class DbUtils {

    // TODO: Consider better names

    private DbUtils() {
        // Do not instantiate
    }

    public static CreateTableBuilder createTable(@NonNull String name) {
        return new CreateTableBuilder(name);
    }

    public static String dropTableIfExists(@NonNull String name) {
        return "DROP TABLE IF EXISTS " + name + ";";
    }
}
