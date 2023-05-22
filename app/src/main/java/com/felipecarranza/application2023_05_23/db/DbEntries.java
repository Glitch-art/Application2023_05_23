package com.felipecarranza.application2023_05_23.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.felipecarranza.application2023_05_23.entities.Entries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DbEntries extends DbHelper {

    Context context;

    public DbEntries(@Nullable Context context) {
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
            values.put("updated_at", updated_at);
            id = db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return id;
    }

    public ArrayList<Entries> indexEntries() {
        ArrayList<Entries> entries = new ArrayList<>();
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] args = {};
            Cursor cursor_entries;
            cursor_entries = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC", args);

            if (cursor_entries != null) {
                if (cursor_entries.moveToFirst()) {
                    do {
                        Entries entry = new Entries();
                        entry.setId(cursor_entries.getInt(0));
                        entry.setTitle(cursor_entries.getString(1));
                        entry.setContent(cursor_entries.getString(2));
                        entry.setCreated_at(cursor_entries.getString(3));
                        entry.setUpdated_at(cursor_entries.getString(4));
                        entries.add(entry);
                    } while (cursor_entries.moveToNext());
                }
            }
            cursor_entries.close();
        } catch (Exception e) {
            e.toString();
        }
        return entries;
    }

    public Entries showEntry(int id) {
        Entries entry = new Entries();
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor_entries;
            cursor_entries = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + id, null);

            if (cursor_entries != null) {
                if (cursor_entries.moveToFirst()) {
                    entry.setId(cursor_entries.getInt(0));
                    entry.setTitle(cursor_entries.getString(1));
                    entry.setContent(cursor_entries.getString(2));
                    entry.setCreated_at(cursor_entries.getString(3));
                    entry.setUpdated_at(cursor_entries.getString(4));
                }
            }
            cursor_entries.close();
        } catch (Exception e) {
            e.toString();
        }
        return entry;
    }

    public boolean editEntry(int id, String title, String content) {

        boolean result = false;

        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_NAME + " " +
                    "SET title = '" + title + "', " +
                    "content = '" + content + "', " +
                    "updated_at = '" + now + "' " +
                    "WHERE id = " + id);
            result = true;
        } catch (Exception e) {
            e.toString();
        } finally {
            db.close();
        }
        return result;
    }
}
