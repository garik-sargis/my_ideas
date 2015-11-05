package com.gs.android.data.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;

import com.gs.android.data.db.IdeasContract.IdeaEntry;
import com.gs.android.mythoughts.domain.Idea;
import com.gs.android.mythoughts.domain.WithId;

import static com.gs.android.data.db.IdeasContract.IdeaEntry.COL_ID;

public final class IdeaSqlConverter {

    private IdeaSqlConverter() {
        // Do not instantiate
    }

    private static ContentValues valuesFromCursor(Cursor cursor, int position) {
        cursor.moveToPosition(position);

        ContentValues values = new ContentValues();

        DatabaseUtils.cursorLongToContentValues(cursor, COL_ID, values);
        DatabaseUtils.cursorStringToContentValues(cursor, IdeaEntry.COL_TEXT, values);

        return values;
    }

    public static WithId<Idea> fromValues(ContentValues values) {
        long id = values.getAsLong(COL_ID);
        String text = values.getAsString(IdeaEntry.COL_TEXT);

        return new WithId<>(id, new Idea(text));
    }

    public static WithId<Idea> fromCursor(Cursor cursor, int position) {
        return fromValues(valuesFromCursor(cursor, position));
    }
}
