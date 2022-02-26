package com.example.viedodbsqlite.Data.Contracts;

import android.provider.BaseColumns;

public class TareaContracts {
    private TareaContracts(){    }
    public static class TareaEntry implements BaseColumns {

        //Campos constantes
        public static final String TABLE_NAME = "tareas";
        public static final String ID_COLUMN = "ID_Tareas";
        public static final String TITLE = "titulo";
        public static final String TIPO = "tipo";
        public static final String CANTIDAD = "cantidad";
        public static final String OBSERV = "observaciones";
        public static final String ANO = "ID_Ano";
        public static final String MES = "ID_Mes";
        public static final String ID_VINA = "ID_Vina";

        //Código de creación tabla
        public final static String CREATE_TABLA_TAREAS="CREATE TABLE "+ TABLE_NAME + " ("+
                ID_COLUMN+" PRIMARY KEY AUTOINCREMENT, " +
                TITLE+" TEXT "+
                TIPO+" TEXT "+
                CANTIDAD+" INTEGER "+
                OBSERV+" TEXT "+
                ANO+" INTEGER, "+
                MES+" INTEGER, "+
                ID_VINA+" INTEGER, " +
                "FOREIGN KEY ("+ANO+") REFERENCES Ano("+ANO+")"+
                "FOREIGN KEY ("+MES+") REFERENCES Mes("+MES+")"+
                "FOREIGN KEY ("+ID_VINA+") REFERENCES Vina("+ID_VINA+")";

    }
}
