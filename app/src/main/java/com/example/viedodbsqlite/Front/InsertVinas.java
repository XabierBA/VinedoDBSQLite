package com.example.viedodbsqlite.Front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.viedodbsqlite.Data.Contracts.VinaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.R;

public class InsertVinas extends AppCompatActivity {
    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_vinas);
    }
    Button insert = findViewById(R.id.insertVina);

    public void onClick(View view) {
        SQLiteDatabase db = conn.getWritableDatabase();

        EditText name = view.findViewById(R.id.nombreVina);
        EditText variety = view.findViewById(R.id.variedadVina);
        EditText ncepas = view.findViewById(R.id.ncepas);
        EditText extension = view.findViewById(R.id.extension);

        ContentValues values = new ContentValues();
        values.clear();
        values.put(VinaContracts.VinaEntry.NOMBRE,name.getText().toString());
        values.put(VinaContracts.VinaEntry.VARIEDAD,variety.getText().toString());
        values.put(VinaContracts.VinaEntry.EXTENSION,extension.getText().toString());
        values.put(VinaContracts.VinaEntry.N_CEPAS,ncepas.getText().toString());


        long id = db.insert(VinaContracts.VinaEntry.TABLE_NAME, VinaContracts.VinaEntry.ID_COLUMN,values);
        Toast.makeText(this, "Registro exitoso"+id, Toast.LENGTH_SHORT).show();
    }
}