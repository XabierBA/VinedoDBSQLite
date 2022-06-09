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
import com.example.viedodbsqlite.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TareaActivity extends AppCompatActivity {

    ListView listViewTareas;
    ArrayList<String> listaInfo;
    ArrayList<Tareas> listaTareas;



    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,2);


    protected void onCreate(Bundle savedInstanceState){



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anos);

        listViewTareas = (ListView) findViewById(R.id.listAnos);
        FloatingActionButton insertAnos = findViewById(R.id.addAnos);
        consultarlistaAnos();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfo);
        listViewTareas.setAdapter(adaptador);

        listViewTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id_vina = listaTareas.get(i).getVina();
                int id_ano = listaTareas.get(i).getAno();

                Intent mesList = new Intent(getApplicationContext(),MesActivity.class);
                mesList.putExtra("id_vina", id_vina);
                mesList.putExtra("id_ano", id_ano);
                startActivity(mesList);
            }
        });


    }

    private void consultarlistaAnos() {

        Intent listaTarea = getIntent();
        int id_vina = listaTarea.getExtras().getInt("id_vina");
        int id_ano = listaTarea.getExtras().getInt("id_ano");
        int id_mes = listaTarea.getExtras().getInt("id_mes");

        SQLiteDatabase db = conn.getReadableDatabase();

        Tareas tareas = null;
        listaTareas = new ArrayList<Tareas>();



        Cursor cursor = db.rawQuery("SELECT * FROM "+ TareaContracts.TareaEntry.TABLE_NAME
                +" WHERE "+TareaContracts.TareaEntry.ID_VINA+" = "+id_vina+" and "
                +TareaContracts.TareaEntry.ANO+" = "+id_ano+" and "
                +TareaContracts.TareaEntry.MES+" = "+id_mes,
                null);

        /*while(cursor.moveToNext()){
            tarea = new Tareas();
            tarea.setAno(cursor.getInt(1));


            listaTarea.add(tarea);
        }
        obtenerLista();*/

    }

    private void obtenerLista() {
        listaInfo = new ArrayList<String>();

        for(int i = 0; i<listaTareas.size(); i++){
            listaInfo.add("|"+listaTareas.get(i).getAno()+"|"+"\n");

        }
    }

    public void onClick(View view){
        Intent listVinas = getIntent();
        int id_vina = listVinas.getExtras().getInt("id_vina");

        Intent insertAnos = new Intent(this, InsertAnos.class);
        insertAnos.putExtra("id_vina", id_vina);
        startActivity(insertAnos);
    }
}