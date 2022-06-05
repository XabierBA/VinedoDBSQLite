package com.example.viedodbsqlite.Data.Tablas;

public class Mes {

        public int ID_Mes;
        public int mes;
        public int ano;
        public int vina;

        public Mes(int ID,int nMes, int Ano, int ID_Vina){
            ID_Mes = ID;
            vina = ID_Vina;
            ano = Ano;
            mes = nMes;
        }

        public Mes(){

        }

    public int getID_Mes() {
        return ID_Mes;
    }

    public void setID_Mes(int ID_Mes) {
        this.ID_Mes = ID_Mes;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getVina() {
        return vina;
    }

    public void setVina(int vina) {
        this.vina = vina;
    }
}
