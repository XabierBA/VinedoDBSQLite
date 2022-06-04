package com.example.viedodbsqlite.Front;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.viedodbsqlite.Data.Contracts.AnoContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.Data.Tablas.Ano;
import com.example.viedodbsqlite.R;

import java.util.ArrayList;

public class AnosActivity extends AppCompatActivity {

    ListView listViewAnos;
    ArrayList<String> listaInfo;
    ArrayList<Ano> listaAno;

    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,2);

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anos);

        listViewAnos = (ListView) findViewById(R.id.ListAños);

        consultarListaAnos();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfo):
        listViewAnos.setAdapter(adaptador);

    }

    private void consultarListaAnos() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Ano ano = null;
        listaAno = new ArrayList<Ano>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ AnoContracts.AnoEntry.TABLE_NAME, null);

        while(cursor.moveToNext()){
            ano = new Ano();
            ano.setAno(cursor.getInt(0));
            ano.setID_Ano(cursor.getInt(1));
            ano.setID_Vina(cursor.getInt(2));

            listaAno.add(ano);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaInfo = new ArrayList<String>();

        for(int i = 0; i<listaAno.size(); i++){
            listaInfo.add(listaAno.get(i).getAno()+" - "+listaAno.get(i).getID_Ano()+" - "+listaAno.get(i).getID_Vina());
        }

    }

}
