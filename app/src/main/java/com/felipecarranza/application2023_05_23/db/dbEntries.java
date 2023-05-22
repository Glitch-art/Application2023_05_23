package com.felipecarranza.application2023_05_23.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class dbEntries extends DbHelper {

    Context context;

    public dbEntries(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertEntry(String title, String content, String created_at, String updated_at) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("title", title);
            values.put("content", content);
            values.put("created_at", created_at);
            id = db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return id;
    }
}
