package com.example.leitor.model;

import java.util.Map;

public abstract class Sessao {
    private String conteudo;

    public Sessao(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public abstract Map<String, Object> processarConteudo();
    
    public static String extrairTipo(String conteudo) {
      if (conteudo.startsWith("PE1")) {
          return "PE1";
      } else if (conteudo.startsWith("PE3")) {
          return "PE3";
      }
      return "DESCONHECIDO"; 
  }
}

