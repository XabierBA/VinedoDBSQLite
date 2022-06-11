package com.example.viedodbsqlite.Insert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.viedodbsqlite.Data.Contracts.AnoContracts;
import com.example.viedodbsqlite.Data.Contracts.MesContracts;
import com.example.viedodbsqlite.Data.Contracts.TareaContracts;
import com.example.viedodbsqlite.Data.Contracts.VinaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.Front.AnosActivity;
import com.example.viedodbsqlite.Front.TareaActivity;
import com.example.viedodbsqlite.Front.VinasActivity;
import com.example.viedodbsqlite.R;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InsertTareas extends AppCompatActivity {
    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_tareas);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.insertTareas:
                insertTareas();

                Intent listaTarea = getIntent();
                int id_vina = listaTarea.getExtras().getInt("id_vina");
                int id_ano = listaTarea.getExtras().getInt("id_ano");
                int id_mes = listaTarea.getExtras().getInt("id_mes");

                Intent listaTareas = new Intent(this, TareaActivity.class);
                listaTareas.putExtra("id_vina", id_vina);
                listaTareas.putExtra("id_ano", id_ano);
                listaTareas.putExtra("id_mes", id_mes);
                startActivity(listaTareas);
                break;

            case R.id.cancel:
                listaTarea = getIntent();
                id_vina = listaTarea.getExtras().getInt("id_vina");
                id_ano = listaTarea.getExtras().getInt("id_ano");
                id_mes = listaTarea.getExtras().getInt("id_mes");

                listaTareas = new Intent(this, TareaActivity.class);
                listaTareas.putExtra("id_vina", id_vina);
                listaTareas.putExtra("id_ano", id_ano);
                listaTareas.putExtra("id_mes", id_mes);
                startActivity(listaTareas);
                break;
        }
    }

    public void insertTareas() {
        Intent listTareas = getIntent();
        int id_vina = listTareas.getExtras().getInt("id_vina");
        int ano = listTareas.getExtras().getInt("id_ano");
        int mes = listTareas.getExtras().getInt("id_mes");

        SQLiteDatabase db = conn.getWritableDatabase();

        EditText titulo = findViewById(R.id.titular);
        EditText tipo = findViewById(R.id.tipo);
        EditText cantidad = findViewById(R.id.cantidad);
        EditText observaciones = findViewById(R.id.observaciones);

        ContentValues values = new ContentValues();
        values.clear();
        values.put(TareaContracts.TareaEntry.TITLE,titulo.getText().toString());
        values.put(TareaContracts.TareaEntry.TIPO,tipo.getText().toString());
        values.put(TareaContracts.TareaEntry.CANTIDAD,cantidad.getText().toString());
        values.put(TareaContracts.TareaEntry.OBSERV,observaciones.getText().toString());
        values.put(TareaContracts.TareaEntry.ANO,ano);
        values.put(TareaContracts.TareaEntry.ID_VINA,id_vina);
        values.put(TareaContracts.TareaEntry.MES,mes);


        db.insert(TareaContracts.TareaEntry.TABLE_NAME, TareaContracts.TareaEntry.ID_COLUMN,values);
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
    }
}