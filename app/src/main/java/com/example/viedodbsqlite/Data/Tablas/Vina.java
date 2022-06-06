package com.example.viedodbsqlite.Data.Tablas;




public class Vina {
    
    public int ID_Vina;
    
    public String nombre;

    public int n_cepas;

    public int extensión;

    public String variedad;

    public Vina(int Vina,String nom, int ncp, int ext, String var){
        nombre = nom;
        n_cepas = ncp;
        extensión = ext;
        variedad = var;
        ID_Vina = Vina;
    }

    public Vina(){
    }

    public int getID_Vina() {
        return ID_Vina;
    }

    public String getNombre() {
        return nombre;
    }

    public int getN_cepas() {
        return n_cepas;
    }

    public int getExtensión() {
        return extensión;
    }

    public String getVariedad() {
        return variedad;
    }

    public void setID_Vina(int id_vina){
        this.ID_Vina = id_vina;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setN_cepas(int n_cepas) {
        this.n_cepas = n_cepas;
    }

    public void setExtensión(int extensión) {
        this.extensión = extensión;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }
}
