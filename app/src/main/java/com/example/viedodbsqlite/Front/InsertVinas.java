package com.example.viedodbsqlite.Front;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.viedodbsqlite.Data.Contracts.VinaContracts;
import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;
import com.example.viedodbsqlite.R;


public class InsertVinas extends DialogFragment {

    ConexiónSQLite conn = new ConexiónSQLite(getContext(), "db_vinedo", null,2);


    public InsertVinas() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static InsertVinas newInstance(String title) {
        InsertVinas frag = new InsertVinas();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_insert_vinas, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.insertVina:
                insertVinas(view);
                break;

            case R.id.cancel:
                ;
                break;
        }
    }

    public void insertVinas(View view){

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
        Toast.makeText(getContext(), "Registro exitoso"+id, Toast.LENGTH_SHORT).show();

    }


}