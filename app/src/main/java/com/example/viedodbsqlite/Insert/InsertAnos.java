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
import com.example.viedodbsqlite.Data.Contracts.VinaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.Front.AnosActivity;
import com.example.viedodbsqlite.Front.VinasActivity;
import com.example.viedodbsqlite.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

public class InsertAnos extends AppCompatActivity {
    ConexiónSQLite conn = new ConexiónSQLite(this, "db_vinedo", null,2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_anos);

        ArrayList<String> years = new ArrayList<String>();

        int thisYear = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = thisYear - 10; i <= thisYear + 200; i++) {
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        Spinner spinAnos= (Spinner)findViewById(R.id.spinnerAnos);
        spinAnos.setAdapter(adapter);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.insertAnos:
                insertAnos();
                break;

            case R.id.cancel:
                Intent listaAnos = new Intent(this, AnosActivity.class);
                startActivity(listaAnos);
                break;
        }
    }

    public void insertAnos() {
        Intent listVinas = getIntent();
        int id_vina = listVinas.getExtras().getInt("id_vina");

        SQLiteDatabase db = conn.getWritableDatabase();

        Spinner ano = findViewById(R.id.spinnerAnos);

        ContentValues values = new ContentValues();
        values.clear();
        values.put(AnoContracts.AnoEntry.ID_VINA, id_vina);
        values.put(AnoContracts.AnoEntry.ANO, ano.getSelectedItem().toString());


        db.insert(AnoContracts.AnoEntry.TABLE_NAME, AnoContracts.AnoEntry.ID_ANO ,values);
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
    }
}