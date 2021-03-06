package com.example.viedodbsqlite.Insert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.viedodbsqlite.Data.Contracts.VinaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.Front.VinasActivity;
import com.example.viedodbsqlite.R;

public class InsertVinas extends AppCompatActivity {
    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,3);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_vinas);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.insertVina:
                insertVinas();

                Intent listaViñas = new Intent(this, VinasActivity.class);
                startActivity(listaViñas);
                break;

            case R.id.cancel:
                listaViñas = new Intent(this, VinasActivity.class);
                startActivity(listaViñas);
                break;
        }
    }

    public void insertVinas() {
        SQLiteDatabase db = conn.getWritableDatabase();

        EditText name = findViewById(R.id.nombreVina);
        EditText variety = findViewById(R.id.variedadVina);
        EditText ncepas = findViewById(R.id.ncepas);
        EditText extension = findViewById(R.id.extension);

        ContentValues values = new ContentValues();
        values.clear();
        values.put(VinaContracts.VinaEntry.NOMBRE,name.getText().toString());
        values.put(VinaContracts.VinaEntry.VARIEDAD,variety.getText().toString());
        values.put(VinaContracts.VinaEntry.EXTENSION,extension.getText().toString());
        values.put(VinaContracts.VinaEntry.N_CEPAS,ncepas.getText().toString());


        db.insert(VinaContracts.VinaEntry.TABLE_NAME, VinaContracts.VinaEntry.ID_COLUMN,values);
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
    }
}