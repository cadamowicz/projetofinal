package com.example.projetofinal;

public class Bebidas {
    private int idBebidas;
    private String nomeBebidas;
    private int valorBebidas;

    public Bebidas(){

    }

    public Bebidas(int idBebidas, String nomeBebidas, int valorBebidas) {
        this.setIdBebidas(idBebidas);
        this.setNomeBebidas(nomeBebidas);
        this.setValorBebidas(valorBebidas);
    }

    public Bebidas(String nomeBebidas, int valorBebidas) {
        this.nomeBebidas = nomeBebidas;
        this.valorBebidas = valorBebidas;
    }

    @Override
    public String toString() {
        return "Bebida{" + "idBebidas=" + idBebidas + ", nomeBebidas='" + nomeBebidas + '\'' + ", valorBebidas=" + valorBebidas + '}';

    }

    protected int getIdBebidas() {
        return idBebidas;
    }

    protected void setIdBebidas(int idBebidas) {
        this.idBebidas = idBebidas;
    }

    protected String getNomeBebidas() {
        return nomeBebidas;
    }

    protected void setNomeBebidas(String nomeBebidas) {
        this.nomeBebidas = nomeBebidas;
    }

    protected int getValorBebidas() {
        return valorBebidas;
    }

    protected void setValorBebidas(int valorBebidas) {
        this.valorBebidas = valorBebidas;
    }
}
