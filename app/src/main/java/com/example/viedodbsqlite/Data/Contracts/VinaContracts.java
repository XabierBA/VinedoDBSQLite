package com.example.viedodbsqlite.Data.Contracts;

import android.content.Context;
import android.provider.BaseColumns;

import com.example.viedodbsqlite.Data.DB.ConexiónSQLite;

public class VinaContracts {
    private VinaContracts(){    }
    public static class VinaEntry implements BaseColumns{

        //Campos constantes
        public static final String TABLE_NAME = "vinas";
        public static final String ID_COLUMN = "ID_Vina";
        public static final String NOMBRE = "nombre";
        public static final String N_CEPAS = "n_cepas";
        public static final String EXTENSION = "extensión";
        public static final String VARIEDAD = "variedad";

        //Código de creación tabla
        public final static String CREATE_TABLA_VINA="CREATE TABLE "+ TABLE_NAME + " ("+
                ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOMBRE+" TEXT, " +
                N_CEPAS+" INTEGER, " +
                EXTENSION+" INTEGER, " +
                VARIEDAD+" TEXT)";

    }


}
