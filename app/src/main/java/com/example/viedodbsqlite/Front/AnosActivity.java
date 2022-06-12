package com.example.viedodbsqlite.Front;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.viedodbsqlite.Data.Contracts.AnoContracts;
import com.example.viedodbsqlite.Data.Contracts.VinaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.Data.Tablas.Ano;
import com.example.viedodbsqlite.Data.Tablas.Vina;
import com.example.viedodbsqlite.Insert.InsertAnos;
import com.example.viedodbsqlite.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class AnosActivity extends AppCompatActivity {

    ListView listViewAnos;
    ArrayList<String> listaInfo;
    ArrayList<Ano> listaAno;



    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,3);


    protected void onCreate(Bundle savedInstanceState){



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anos);

        listViewAnos = (ListView) findViewById(R.id.listAnos);
        FloatingActionButton insertAnos = findViewById(R.id.addAnos);
        consultarlistaAnos();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfo);
        listViewAnos.setAdapter(adaptador);

        listViewAnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id_vina = listaAno.get(i).getID_Vina();
                int id_ano = listaAno.get(i).getID_Ano();

                Intent mesList = new Intent(getApplicationContext(),MesActivity.class);
                mesList.putExtra("id_vina", id_vina);
                mesList.putExtra("id_ano", id_ano);
                startActivity(mesList);
            }
        });


    }

    private void consultarlistaAnos() {

        Intent listVinas = getIntent();
        int id_vina = listVinas.getExtras().getInt("id_vina");

        SQLiteDatabase db = conn.getReadableDatabase();

        Ano ano = null;
        listaAno = new ArrayList<Ano>();



        Cursor cursor = db.rawQuery("SELECT * FROM "+ AnoContracts.AnoEntry.TABLE_NAME+" WHERE "+ AnoContracts.AnoEntry.ID_VINA+" = "+id_vina, null);

        while(cursor.moveToNext()){
            ano = new Ano();
            ano.setID_Ano(cursor.getInt(0));
            ano.setAno(cursor.getInt(1));
            ano.setID_Vina(cursor.getInt(2));

            listaAno.add(ano)
            ;
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaInfo = new ArrayList<String>();

        for(int i = 0; i<listaAno.size(); i++){
            listaInfo.add("|"+listaAno.get(i).getAno()+"|"+"\n");

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
