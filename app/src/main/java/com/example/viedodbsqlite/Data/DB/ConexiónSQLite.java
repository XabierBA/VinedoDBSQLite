package com.example.viedodbsqlite.Data.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.viedodbsqlite.Data.Contracts.AnoContracts;
import com.example.viedodbsqlite.Data.Contracts.MesContracts;
import com.example.viedodbsqlite.Data.Contracts.TareaContracts;
import com.example.viedodbsqlite.Data.Contracts.UsuarioContracts;
import com.example.viedodbsqlite.Data.Contracts.VinaContracts;
import com.example.viedodbsqlite.Data.Tablas.Usuario;

public class ConexiónSQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "db_vinedo";

    public ConexiónSQLite(@Nullable Context context){
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    public ConexiónSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(VinaContracts.VinaEntry.CREATE_TABLA_VINA);
        sqLiteDatabase.execSQL(AnoContracts.AnoEntry.CREATE_TABLE_ANO);
        sqLiteDatabase.execSQL(MesContracts.MesEntry.CREATE_TABLA_MES);
        sqLiteDatabase.execSQL(TareaContracts.TareaEntry.CREATE_TABLA_TAREAS);
        sqLiteDatabase.execSQL(UsuarioContracts.UsuarioEntry.CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionAntigua, int versionNueva) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+VinaContracts.VinaEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ AnoContracts.AnoEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ MesContracts.MesEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TareaContracts.TareaEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ UsuarioContracts.UsuarioEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void RESTART_TABLE_USERS(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS usuarios");
        sqLiteDatabase.execSQL(UsuarioContracts.UsuarioEntry.CREATE_TABLE_USER);
    }


}
