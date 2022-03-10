package com.example.viedodbsqlite.Data.Contracts;

import android.provider.BaseColumns;

public class MesContracts {
    private MesContracts(){    }
    public static class MesEntry implements BaseColumns{

        //Campos constantes
        public static final String TABLE_NAME = "meses";
        public static final String ID_COLUMN = "ID_Mes";
        public static final String MES = "mes";
        public static final String ANO = "ID_Ano";
        public static final String ID_VINA = "ID_Vina";

        //Código de creación tabla
        public final static String CREATE_TABLA_MES="CREATE TABLE "+ TABLE_NAME + " ("+
                ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MES+" INTEGER, " +
                ANO+" INTEGER, " +
                ID_VINA+" INTEGER, "+
                "FOREIGN KEY ("+ID_VINA+") REFERENCES Vina("+ID_VINA+"),"+
                " FOREIGN KEY ("+ANO+") REFERENCES Ano("+ANO+"))";

    }


}
