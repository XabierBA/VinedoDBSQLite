package com.example.viedodbsqlite.Data.Contracts;

import android.provider.BaseColumns;

public class AnoContracts {
    private AnoContracts(){
    }
    public static class AnoEntry implements BaseColumns {

        //Campos Constantes
        public static final String TABLE_NAME = "anos";
        public static final String ID_ANO = "ID_Ano";
        public static final String ANO = "ano";
        public static final String ID_VINA = "ID_Vina";

        public final static String CREATE_TABLE_ANO="CREATE TABLE "+ TABLE_NAME + "("+
                ID_ANO + " PRIMARY KEY AUTOINCREMENT, " +
                ANO+ " INTEGER, "+
                ID_VINA+ " INTEGER," +
                "FOREIGN KEY ("+ID_VINA+") REFERENCES Vina("+ID_VINA+"))";

    }
}
