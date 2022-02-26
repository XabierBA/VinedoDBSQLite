package com.example.viedodbsqlite.Data.Tablas;

public class Usuario {

    private String usuario;

    private String contraseña;

    public Usuario(String user, String paswd){
        contraseña = paswd;
        usuario = user;
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
