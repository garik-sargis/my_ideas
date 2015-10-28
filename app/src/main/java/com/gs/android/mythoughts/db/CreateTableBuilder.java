package com.gs.android.mythoughts.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class CreateTableBuilder {
    private StringBuilder sqlStatement;
    private int columnCount;

    CreateTableBuilder(@NonNull String tableName) {
        sqlStatement = new StringBuilder("CREATE TABLE ").append(tableName).append(" (");
        columnCount = 0;
    }

    public CreateTableBuilder withColumn(@NonNull String name, @NonNull String type, @Nullable String
            constraint) {
        if (columnCount != 0) {
            sqlStatement.append(", ");
        }

        sqlStatement.append(name).append(" ").append(type);

        if (constraint != null) {
            sqlStatement.append(" ").append(constraint);
        }

        columnCount++;

        return this;
    }

    public CreateTableBuilder withColumn(@NonNull String name, @NonNull String type) {
        return withColumn(name, type, null);
    }

    public CreateTableBuilder withRowId(@NonNull String name) {
        return withColumn(name, "INTEGER", "PRIMARY KEY AUTOINCREMENT");
    }

    public String create() {
        sqlStatement.append(");");
        return sqlStatement.toString();
    }
}
