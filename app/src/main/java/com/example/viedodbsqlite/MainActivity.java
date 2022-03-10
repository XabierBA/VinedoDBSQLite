package com.example.viedodbsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.viedodbsqlite.Data.Contracts.AnoContracts;
import com.example.viedodbsqlite.Data.Contracts.UsuarioContracts;
import com.example.viedodbsqlite.Data.Contracts.VinaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,1);


    }

    public void onClick(View view){
        registrarUsuarios();
    }

    private void registrarUsuarios() {

        ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,1);

        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsuarioContracts.UsuarioEntry.NOMBRE,"Xabier");
        values.put(UsuarioContracts.UsuarioEntry.CONTRASEÑA,"xabiba2002");

        Long idResultante = db.insert(UsuarioContracts.UsuarioEntry.TABLE_NAME, UsuarioContracts.UsuarioEntry.ID_USER,values);
        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante, Toast.LENGTH_SHORT).show();

    }
}