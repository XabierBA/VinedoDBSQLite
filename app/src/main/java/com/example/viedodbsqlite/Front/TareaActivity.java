package com.example.viedodbsqlite.Front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.viedodbsqlite.Data.Contracts.AnoContracts;
import com.example.viedodbsqlite.Data.Contracts.MesContracts;
import com.example.viedodbsqlite.Data.Contracts.TareaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.Data.Tablas.Ano;
import com.example.viedodbsqlite.Data.Tablas.Tareas;
import com.example.viedodbsqlite.Insert.InsertAnos;
import com.example.viedodbsqlite.Insert.InsertTareas;
import com.example.viedodbsqlite.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TareaActivity extends AppCompatActivity {

    ListView listViewTareas;
    ArrayList<String> listaInfo;
    ArrayList<Tareas> listaTareas;



    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,3);


    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);

        listViewTareas = (ListView) findViewById(R.id.listTarea);
        FloatingActionButton insertTareas = findViewById(R.id.addTarea);
        consultarlistaTareas();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfo);
        listViewTareas.setAdapter(adaptador);

        listViewTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id_tarea = listaTareas.get(i).getID_Tarea();
                Intent listaTarea = getIntent();
                int id_vina = listaTarea.getExtras().getInt("id_vina");
                int id_ano = listaTarea.getExtras().getInt("id_ano");
                int id_mes = listaTarea.getExtras().getInt("id_mes");

                Intent tareasList = new Intent(getApplicationContext(),TareasInfo.class);
                tareasList.putExtra("id_tareas", id_tarea);
                tareasList.putExtra("id_vina", id_vina);
                tareasList.putExtra("id_ano", id_ano);
                tareasList.putExtra("id_mes", id_mes);
                startActivity(tareasList);
            }
        });
    }

    private void consultarlistaTareas() {

        Intent listaTarea = getIntent();
        int id_vina = listaTarea.getExtras().getInt("id_vina");
        int id_ano = listaTarea.getExtras().getInt("id_ano");
        int id_mes = listaTarea.getExtras().getInt("id_mes");

        SQLiteDatabase db = conn.getReadableDatabase();

        Tareas tareas = null;
        listaTareas = new ArrayList<Tareas>();



        Cursor cursor = db.rawQuery("SELECT * FROM "+ TareaContracts.TareaEntry.TABLE_NAME
                +" WHERE "+TareaContracts.TareaEntry.ID_VINA+" = "+id_vina+" and "
                +TareaContracts.TareaEntry.MES+" = "+id_mes,
                null);

        while(cursor.moveToNext()){
            tareas = new Tareas();
            tareas.setID_Tarea(cursor.getInt(0));
            tareas.setTitulo(cursor.getString(1));
            tareas.setTipo(cursor.getString(2));
            tareas.setCantidad(cursor.getInt(3));
            tareas.setObservaciones(cursor.getString(4));
            tareas.setAno(cursor.getInt(5));
            tareas.setMes(cursor.getInt(6));
            tareas.setVina(cursor.getInt(7));


            listaTareas.add(tareas);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaInfo = new ArrayList<String>();

        for(int i = 0; i<listaTareas.size(); i++){
            listaInfo.add("|"+listaTareas.get(i).getTitulo()+"|"+"\n"+
                        "   Tipo: "+listaTareas.get(i).getTipo());


        }
    }

    public void onClick(View view){ ;
        Intent listaTarea = getIntent();
        int id_vina = listaTarea.getExtras().getInt("id_vina");
        int id_ano = listaTarea.getExtras().getInt("id_ano");
        int id_mes = listaTarea.getExtras().getInt("id_mes");

        Intent insertTareas = new Intent(this, InsertTareas.class);
        insertTareas.putExtra("id_vina", id_vina);
        insertTareas.putExtra("id_ano", id_ano);
        insertTareas.putExtra("id_mes", id_mes);

        startActivity(insertTareas);
    }
}