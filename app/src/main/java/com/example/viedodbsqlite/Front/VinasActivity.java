package com.example.viedodbsqlite.Front;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.viedodbsqlite.Data.Contracts.VinaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.Data.Tablas.Vina;
import com.example.viedodbsqlite.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class VinasActivity extends AppCompatActivity {

    ListView listViewVinas;
    ArrayList<String> listaInfo;
    ArrayList<Vina> listaVina;


    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,2);

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vinas);

        listViewVinas = (ListView) findViewById(R.id.ListVinas);
        FloatingActionButton insertVinas = findViewById(R.id.addVinas);
        consultarlistaVinas();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfo);
        listViewVinas.setAdapter(adaptador);

    }

    private void consultarlistaVinas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Vina vina = null;
        listaVina = new ArrayList<Vina>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ VinaContracts.VinaEntry.TABLE_NAME, null);

        while(cursor.moveToNext()){
            vina = new Vina();
            vina.setNombre(cursor.getString(1));
            vina.setExtensión(cursor.getInt(2));
            vina.setN_cepas(cursor.getInt(3));
            vina.setVariedad(cursor.getString(4));

            listaVina.add(vina);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaInfo = new ArrayList<String>();

        for(int i = 0; i<listaVina.size(); i++){
            listaInfo.add("|"+listaVina.get(i).getNombre().toUpperCase(Locale.ROOT)+"|"+"\n"
                    +" - Variedad: "+listaVina.get(i).getVariedad()+"\n"
                    +" - Extensión: "+listaVina.get(i).getExtensión()+" m "+"\n"
                    +" - Número de cepas: "+listaVina.get(i).getN_cepas() + "\n");
        }

    }

    public void onClick(View view){

        Intent insertVinas = new Intent(this, InsertVinas.class);
        startActivity(insertVinas);

    }

}
