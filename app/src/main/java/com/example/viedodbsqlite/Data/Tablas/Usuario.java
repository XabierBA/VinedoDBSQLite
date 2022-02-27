package com.example.viedodbsqlite.Data.Tablas;

public class Usuario {

    private int ID_User;

    private String usuario;

    private String contraseña;

    public Usuario(int ID, String user, String paswd){
        ID_User = ID;
        contraseña = paswd;
        usuario = user;
    }

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
