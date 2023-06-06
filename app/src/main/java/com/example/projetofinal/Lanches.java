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
        return "Lanche{" + "idLanches=" + idLanches + ", nomeLanches='" + nomeLanches + '\'' + ", valorLanches=" + valorLanches + '}';

    }






}

