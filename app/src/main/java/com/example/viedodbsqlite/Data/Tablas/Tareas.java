package com.example.viedodbsqlite.Data.Tablas;

public class Tareas {

        public int tarea;
        public String titulo;
        public String tipo;
        public String observaciones;
        public int ano;
        public int mes;
        public int vina;

    public Tareas(int ID_Tarea, String Titulo, String type, String obs, int nAno, int nMes, int nvina) {
        tarea = ID_Tarea;
        titulo = Titulo;
        tipo = type;
        observaciones = obs;
        ano = nAno;
        mes = nMes;
        vina = nvina;
    }

    public int getTarea() {
        return tarea;
    }

    public void setTarea(int tarea) {
        this.tarea = tarea;
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
