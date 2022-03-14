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
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.Front.VinasAtivity;

public class MainActivity extends AppCompatActivity {
    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,2);




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnregist:
                registrarUsuarios();
                break;
            case R.id.btninit:
                consultarUsuarios();
                break;
        }
    }

    private void registrarUsuarios() {

        SQLiteDatabase db = conn.getWritableDatabase();

        EditText nombre = findViewById(R.id.etnombre);
        EditText contr = findViewById(R.id.etcontr);

        ContentValues values = new ContentValues();
        values.clear();
        values.put(UsuarioContracts.UsuarioEntry.NOMBRE,nombre.getText().toString());
        values.put(UsuarioContracts.UsuarioEntry.CONTRASEÑA,contr.getText().toString());

        Long idResultante = db.insert(UsuarioContracts.UsuarioEntry.TABLE_NAME, UsuarioContracts.UsuarioEntry.ID_USER,values);
        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante, Toast.LENGTH_SHORT).show();

    }
    private void consultarUsuarios() {

        SQLiteDatabase db = conn.getReadableDatabase();

        //TextView salida = findViewById(R.id.salida);
        //salida.setText("");


        EditText nombre = findViewById(R.id.etnombre);
        EditText contr = findViewById(R.id.etcontr);

        String id = UsuarioContracts.UsuarioEntry.ID_USER;
        String name = UsuarioContracts.UsuarioEntry.NOMBRE;
        String paswd = UsuarioContracts.UsuarioEntry.CONTRASEÑA;

        String[] campos = {id,name,paswd};
        Cursor cursor = db.query(UsuarioContracts.UsuarioEntry.TABLE_NAME,campos,null,null,null,null,null);

        boolean checkname = false;
        boolean checkpaswd = false;

        while(cursor.moveToNext() && (!checkpaswd && !checkname)){

            if(nombre.getText().toString().equals(cursor.getString(1)) ){
                checkname = true;
            }

            if(contr.getText().toString().equals(cursor.getString(2))){
                checkpaswd = true;
            }

            //salida.append("\n" + "ID: " + cursor.getString(0) + " ||  NOMBRE: " + cursor.getString(1) + " ||  CONTRASEÑA: " + cursor.getString(2));
        };

        if(checkname && checkpaswd){

            Intent vinasactv = new Intent(this, VinasAtivity.class);
            startActivity(vinasactv);

        }






    }
    /*private void updateUsuarios(){

        SQLiteDatabase db = conn.getWritableDatabase();

        EditText nombre = findViewById(R.id.nombre);
        EditText contr = findViewById(R.id.contr);
        EditText id_update = findViewById(R.id.id_update);

        String[] parametros = {id_update.getText().toString()};

        ContentValues values = new ContentValues();
        values.clear();
        values.put(UsuarioContracts.UsuarioEntry.NOMBRE,nombre.getText().toString());
        values.put(UsuarioContracts.UsuarioEntry.CONTRASEÑA,contr.getText().toString());

        db.update(UsuarioContracts.UsuarioEntry.TABLE_NAME,values,UsuarioContracts.UsuarioEntry.ID_USER+" = ?",parametros);

    }

    private void limpiarUsuarios(){
        SQLiteDatabase db = conn.getWritableDatabase();
        conn.RESTART_TABLE_USERS(db);

        int idResultante = db.delete(UsuarioContracts.UsuarioEntry.TABLE_NAME,null,null);
        Toast.makeText(getApplicationContext(),"Id Delete: "+idResultante, Toast.LENGTH_SHORT).show();
    }*/
}