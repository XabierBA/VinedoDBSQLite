package com.example.viedodbsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.viedodbsqlite.Data.Contracts.AnoContracts;
import com.example.viedodbsqlite.Data.Contracts.VinaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;

public class MainActivity extends AppCompatActivity {
    ConexiónSQLite conn = new ConexiónSQLite(getApplicationContext());
    SQLiteDatabase db = conn.getWritableDatabase();
    ContentValues values = new ContentValues();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*values.put(AnoContracts.AnoEntry.ID_COLUMN, "2021");
        values.put(AnoContracts.AnoEntry.VINA_COLUMN, "Viña Grande");

        Long idResultante = db.insert(AnoContracts.AnoEntry.TABLE_NAME, AnoContracts.AnoEntry.ID_COLUMN, values);

        */


    }
}