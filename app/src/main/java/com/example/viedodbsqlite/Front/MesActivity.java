package com.example.viedodbsqlite.Front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.viedodbsqlite.Data.Contracts.AnoContracts;
import com.example.viedodbsqlite.Data.Contracts.MesContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.Data.Tablas.Mes;
import com.example.viedodbsqlite.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class MesActivity extends AppCompatActivity {

    ListView listViewMes;
    ArrayList<String> listaInfo;
    ArrayList<Mes> listaMes;

    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes);

        listViewMes = (ListView) findViewById(R.id.listMes);
        consultarlistaMeses();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfo);
        listViewMes.setAdapter(adaptador);

        listViewMes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id_vina = listaMes.get(i).getVina();
                int id_ano = listaMes.get(i).getAno();
                int id_mes = listaMes.get(i).getID_Mes();

                Intent mesList = new Intent(getApplicationContext(),MesActivity.class);
                mesList.putExtra("id_vina", id_vina);
                mesList.putExtra("id_ano", id_ano);
                mesList.putExtra("id_mes", id_mes);
                startActivity(mesList);
            }
        });
    }

    private void consultarlistaMeses() {

        Intent listaMeses = getIntent();
        int id_vina = listaMeses.getExtras().getInt("id_vina");
        int id_ano = listaMeses.getExtras().getInt("id_ano");

        SQLiteDatabase db = conn.getReadableDatabase();

        Mes mes = null;
        listaMes = new ArrayList<Mes>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ MesContracts.MesEntry.TABLE_NAME+
                " WHERE "+ MesContracts.MesEntry.ID_VINA+" = "+id_vina+
                " and " + MesContracts.MesEntry.ANO+" = "+"(SELECT "+ AnoContracts.AnoEntry.ANO +" FROM "+ AnoContracts.AnoEntry.TABLE_NAME+" WHERE "+ AnoContracts.AnoEntry.ID_ANO +" = "+ id_ano+")", null);

        while(cursor.moveToNext()){
            mes = new Mes();
            mes.setID_Mes(cursor.getInt(0));
            mes.setMes(cursor.getString(1));
            mes.setAno(cursor.getInt(2));
            mes.setVina(cursor.getInt(3));


            listaMes.add(mes);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaInfo = new ArrayList<String>();

        for(int i = 0; i<listaMes.size(); i++){
            listaInfo.add("|"+listaMes.get(i).getMes()+"|"+"\n");

        }
    }
}
