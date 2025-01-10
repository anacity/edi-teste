package com.example.leitor.model;

public class ItemPedido {
    private String quantidade;
    private String data;
    private String hora;

    // Construtores, getters e setters
    public ItemPedido(String quantidade, String data, String hora) {
        this.quantidade = quantidade;
        this.data = data;
        this.hora = hora;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "quantidade='" + quantidade + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
