package com.example.viedodbsqlite.Data.Tablas;

public class Ano {

    public int ID_Ano;

    public int Ano;

    public int vina;

    public Ano(int ID,int NAno, int ID_Vina){
        ID_Ano = ID;
        vina = ID_Vina;
        Ano = NAno;
    }

    public int getID_Ano() {
        return ID_Ano;
    }

    public void setID_Ano(int ID_Ano) {
        this.ID_Ano = ID_Ano;
    }

    public int getID_Vina() {
        return vina;
    }

    public void setID_Vina(int ID_Vina) {
        this.vina = ID_Vina;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int ano) {
        Ano = ano;
    }
}
