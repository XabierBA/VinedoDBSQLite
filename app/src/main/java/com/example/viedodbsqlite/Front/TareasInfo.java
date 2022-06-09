package com.example.viedodbsqlite.Front;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.viedodbsqlite.Data.Contracts.TareaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.Data.Tablas.Tareas;
import com.example.viedodbsqlite.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TareasInfo extends AppCompatActivity {

    ArrayList<Tareas> listaTareas;
    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,2);

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas_info);

        EditText titulo = findViewById(R.id.titular);
        EditText tipo = findViewById(R.id.tipo);
        EditText cantidad = findViewById(R.id.cantidad);
        EditText observ = findViewById(R.id.observaciones);


        Intent listaTarea = getIntent();
        int id_tarea = listaTarea.getExtras().getInt("id_tareas");

        SQLiteDatabase db = conn.getReadableDatabase();

        Tareas tareas = null;
        listaTareas = new ArrayList<Tareas>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ TareaContracts.TareaEntry.TABLE_NAME
                        +" WHERE "+TareaContracts.TareaEntry.ID_COLUMN+" = "+id_tarea,
                null);

        System.out.println(cursor);

        while(cursor.moveToNext()) {

            tareas = new Tareas();
            tareas.setTitulo(cursor.getString(1));
            tareas.setTipo(cursor.getString(2));
            tareas.setCantidad(cursor.getInt(3));
            tareas.setObservaciones(cursor.getString(4));
            tareas.setAno(cursor.getInt(5));
            tareas.setMes(cursor.getInt(6));
            tareas.setVina(cursor.getInt(7));


            listaTareas.add(tareas);
        }
        titulo.setText(tareas.getTitulo());
        tipo.setText(tareas.getTipo());
        //cantidad.setText(tareas.getCantidad());
        observ.setText(tareas.getObservaciones());
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.updateTareas:
                updateTareas();
                break;


            case R.id.cancel:
                Intent listaTarea = getIntent();
                int id_tarea = listaTarea.getExtras().getInt("id_tareas");
                int id_vina = listaTarea.getExtras().getInt("id_vina");
                int id_ano = listaTarea.getExtras().getInt("id_ano");
                int id_mes = listaTarea.getExtras().getInt("id_mes");

                Intent infoTareas = new Intent(this, TareaActivity.class);
                infoTareas.putExtra("id_vina", id_vina);
                infoTareas.putExtra("id_ano", id_ano);
                infoTareas.putExtra("id_mes", id_mes);
                infoTareas.putExtra("id_tareas", id_tarea);
                startActivity(infoTareas);
                break;
        }
    }

    public void updateTareas() {
        Intent listTareas = getIntent();
        int id_tarea = listTareas.getExtras().getInt("id_tareas");

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

        db.update(TareaContracts.TareaEntry.TABLE_NAME,values, TareaContracts.TareaEntry.ID_COLUMN+" = "+id_tarea,null);

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
    }
}