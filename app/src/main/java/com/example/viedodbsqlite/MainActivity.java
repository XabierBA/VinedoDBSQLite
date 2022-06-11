package com.example.viedodbsqlite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,2);

    private String key = "viñedokey4f813m908m0cr7S1u42a8p2";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.register:
                registrarUsuarios();
                break;

            case R.id.login:
                loginUsuarios();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void registrarUsuarios() {

        SQLiteDatabase db = conn.getWritableDatabase();

        boolean flag = false;

        EditText nombre = findViewById(R.id.nombre);
        EditText contr = findViewById(R.id.contr);


        Cursor cursor = db.rawQuery("SELECT "+UsuarioContracts.UsuarioEntry.NOMBRE+", "+ UsuarioContracts.UsuarioEntry.CONTRASEÑA +" FROM "+ UsuarioContracts.UsuarioEntry.TABLE_NAME, null);

        while(cursor.moveToNext() && flag == false){
            if (nombre.getText().toString().equals(cursor.getString(0))){
                flag = true;
            }
        }

        if(flag == true){
            Toast.makeText(this, "El nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
        }
        else{
            String paswdEncripted = new String();
            try {
                paswdEncripted = encriptar(contr.getText().toString(), key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ContentValues values = new ContentValues();
            values.clear();
            values.put(UsuarioContracts.UsuarioEntry.NOMBRE,nombre.getText().toString());
            values.put(UsuarioContracts.UsuarioEntry.CONTRASEÑA,paswdEncripted);

            db.insert(UsuarioContracts.UsuarioEntry.TABLE_NAME, UsuarioContracts.UsuarioEntry.ID_USER,values);
            Toast.makeText(getApplicationContext(),"Registro exitosa", Toast.LENGTH_SHORT).show();
        }
    }


    private void loginUsuarios() {

        EditText nombre = findViewById(R.id.nombre);
        EditText contr = findViewById(R.id.contr);
        SQLiteDatabase db = conn.getReadableDatabase();
        boolean flag = false;

        Cursor cursor = db.rawQuery("SELECT "+UsuarioContracts.UsuarioEntry.NOMBRE+", "+ UsuarioContracts.UsuarioEntry.CONTRASEÑA +" FROM "+ UsuarioContracts.UsuarioEntry.TABLE_NAME, null);



        while(cursor.moveToNext() && flag == false){
            String paswdDesencripted = new String();
            try {
                paswdDesencripted = desencriptar(cursor.getString(1), key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (nombre.getText().toString().equals(cursor.getString(0)) && contr.getText().toString().equals(paswdDesencripted)){
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

    private String desencriptar(String datos, String password) throws Exception{
        SecretKeySpec secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] datosDesencriptados = Base64.decode(datos, Base64.DEFAULT);
        byte[] datosDesencriptadosByte = cipher.doFinal(datosDesencriptados);
        String datosDesencriptadosString = new String(datosDesencriptadosByte);
        return datosDesencriptadosString;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String encriptar(String datos, String password) throws Exception{
        SecretKey secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());
        String datosEncriptadosString = Base64.encodeToString(datosEncriptadosBytes, android.util.Base64.DEFAULT);
        return datosEncriptadosString;
    }

    private SecretKeySpec generateKey(String password) throws Exception{
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }
}