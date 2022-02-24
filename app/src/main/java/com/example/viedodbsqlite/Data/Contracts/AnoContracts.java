package com.example.viedodbsqlite.Data.Contracts;

import android.provider.BaseColumns;

public class AnoContracts {
    private AnoContracts(){
    }
    public static class AnoEntry implements BaseColumns {
        public final static String CREATE_TABLE_ANO="CREATE TABLE "+ AnoContracts.AnoEntry.TABLE_NAME + "(ID_Ano  PRIMARY KEY AUTOINCREMENT, " +
                "Ano INTEGER, "+
                "ID_Vina INTEGER," +
                "FOREIGN KEY (ID_Vina) REFERENCES Vina(ID_Vina))";
        public static final String TABLE_NAME = "anos";
        public static final String ID_COLUMN = "ano";
        public static final String VINA_COLUMN = "vina";

    }
}
