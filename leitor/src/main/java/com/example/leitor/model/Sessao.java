package com.example.leitor.model;

public abstract class Sessao {
    protected String conteudo;

    public Sessao(String conteudo) {
        this.conteudo = conteudo;
    }
    
    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public abstract String processarConteudo();
    
    @Override
    public abstract String toString();
}
