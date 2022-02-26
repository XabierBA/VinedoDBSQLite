package com.example.viedodbsqlite.Data.Tablas;

public class Tareas {

    public int ID_Tarea;
    public String titulo;
    public String tipo;
    public int cantidad;
    public String observaciones;
    public int ano;
    public int mes;
    public int vina;

    public Tareas(int tareas, String title, String type,int amount, String obs, int nAno, int nMes, int nvina) {
        ID_Tarea = tareas;
        titulo = title;
        tipo = type;
        cantidad = amount;
        observaciones = obs;
        ano = nAno;
        mes = nMes;
        vina = nvina;
    }

    public int getID_Tarea() {
        return ID_Tarea;
    }

    public void setID_Tarea(int ID_Tarea) {
        this.ID_Tarea = ID_Tarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getVina() {
        return vina;
    }

    public void setVina(int vina) {
        this.vina = vina;
    }
}