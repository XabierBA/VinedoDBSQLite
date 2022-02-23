package com.example.viedodbsqlite.Data.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class ConexiónSQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "db_vinedo";


    final String CREATE_TABLA_VINA="CREATE TABLE vina (ID_Vina PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, " +
            "ncepas INTEGER, " +
            "extension INTEGER, " +
            "variedad TEXT)";

    public ConexiónSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLA_VINA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionAntigua, int versionNueva) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS vina ");
        onCreate(sqLiteDatabase);
    }
}
