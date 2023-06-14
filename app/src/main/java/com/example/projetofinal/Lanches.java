package com.example.projetofinal;

public class Lanches {
    private int idLanches;
    private String nomeLanches;
    private int valorLanches;

    public Lanches(){

    }

    public Lanches(int idLanches, String nomeLanches, int valorLanches) {
        this.setIdLanches(idLanches);
        this.setNomeLanches(nomeLanches);
        this.setValorLanches(valorLanches);
    }

    public Lanches(String nomeLanches, int valorLanches) {
        this.nomeLanches = nomeLanches;
        this.valorLanches = valorLanches;
    }

    @Override
    public String toString() {
        return "Lanche{" + "idLanche=" + idLanches + ", nomeLanche='" + nomeLanches + '\'' + ", valorLanche=" + valorLanches + '}';

    }

    protected int getIdLanches() {
        return idLanches;
    }

    protected void setIdLanches(int idLanches) {
        this.idLanches = idLanches;
    }

    protected String getNomeLanches() {
        return nomeLanches;
    }

    protected void setNomeLanches(String nomeLanches) {
        this.nomeLanches = nomeLanches;
    }

    protected int getValorLanches() {
        return valorLanches;
    }

    protected void setValorLanches(int valorLanches) {
        this.valorLanches = valorLanches;
    }


}

