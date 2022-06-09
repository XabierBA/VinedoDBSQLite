package com.example.viedodbsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viedodbsqlite.Data.Contracts.UsuarioContracts;
import com.example.viedodbsqlite.Data.Contracts.VinaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.Data.Tablas.Usuario;
import com.example.viedodbsqlite.Data.Tablas.Vina;
import com.example.viedodbsqlite.Front.VinasActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,2);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.register:
                registrarUsuarios();
                break;

            case R.id.login:
                loginUsuarios();
                break;

            case R.id.resetDB:
                limpiarDB();
                break;
        }
    }

    private void registrarUsuarios() {

        SQLiteDatabase db = conn.getWritableDatabase();

        EditText nombre = findViewById(R.id.nombre);
        EditText contr = findViewById(R.id.contr);

        ContentValues values = new ContentValues();
        values.clear();
        values.put(UsuarioContracts.UsuarioEntry.NOMBRE,nombre.getText().toString());
        values.put(UsuarioContracts.UsuarioEntry.CONTRASEÑA,contr.getText().toString());

        db.insert(UsuarioContracts.UsuarioEntry.TABLE_NAME, UsuarioContracts.UsuarioEntry.ID_USER,values);
        Toast.makeText(getApplicationContext(),"Registro exitosa", Toast.LENGTH_SHORT).show();
    }


    private void loginUsuarios() {

        EditText nombre = findViewById(R.id.nombre);
        EditText contr = findViewById(R.id.contr);
        SQLiteDatabase db = conn.getReadableDatabase();
        ArrayList<Usuario> listaUsuarios;
        String test;
        boolean flag = false;
        listaUsuarios = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("SELECT "+UsuarioContracts.UsuarioEntry.NOMBRE+", "+ UsuarioContracts.UsuarioEntry.CONTRASEÑA +" FROM "+ UsuarioContracts.UsuarioEntry.TABLE_NAME, null);

        while(cursor.moveToNext() && flag == false){
            if (nombre.getText().toString().equals(cursor.getString(0)) && contr.getText().toString().equals(cursor.getString(1))){
                flag = true;
            }
        }
        if(flag == true){
            Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();
            Intent listaViñas = new Intent(this, VinasActivity.class);
            startActivity(listaViñas);
        }
        else Toast.makeText(this, "Wrong user or password", Toast.LENGTH_SHORT).show();
    }


    private void limpiarDB(){
        SQLiteDatabase db = conn.getWritableDatabase();
        conn.onUpgrade(db, 2, 3);

        int idResultante = db.delete(UsuarioContracts.UsuarioEntry.TABLE_NAME,null,null);
        Toast.makeText(getApplicationContext(),"Id Delete: "+idResultante, Toast.LENGTH_SHORT).show();
    }
}