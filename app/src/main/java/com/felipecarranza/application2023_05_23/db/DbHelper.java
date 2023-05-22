package com.felipecarranza.application2023_05_23.db;

// Importar librerías
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    // Atributos
    private static final String DB_NAME = "diary.db";
    private static final int DB_VERSION = 3;
    protected static final String TABLE_NAME = "entries";

    // Sentencia SQL para crear la tabla
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "title TEXT, " +
            "content TEXT, " +
            "created_at TEXT, " +
            "updated_at TEXT" +
            ")";

    // Constructor
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Se ejecuta cuando se crea la base de datos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    // Se ejecuta cuando se actualiza la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
