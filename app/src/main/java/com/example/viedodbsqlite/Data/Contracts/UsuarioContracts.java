package com.example.viedodbsqlite.Data.Contracts;

import android.provider.BaseColumns;

public class UsuarioContracts {
    private UsuarioContracts(){
    }
    public static class UsuarioEntry implements BaseColumns {

        public static final String ENCRIPTKEY = "viñedokey4f813m908m0cr7S1u42a8p2";
        //Campos Constantes

        public static final String TABLE_NAME = "usuarios";
        public static final String ID_USER = "ID_User";
        public static final String NOMBRE = "user";
        public static final String CONTRASEÑA = "passwd";

        public final static String CREATE_TABLE_USER = "CREATE TABLE "+ TABLE_NAME + "("+
                ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOMBRE+ " TEXT, "+
                CONTRASEÑA+ " TEXT)";

    }
}
