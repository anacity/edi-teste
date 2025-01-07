package com.example.leitor;

import com.example.leitor.model.Sessao;

public class ITP extends Sessao {

    public ITP(String conteudo) {
        super(conteudo);
    }

    @Override
    public String processarConteudo() {
        StringBuilder resultado = new StringBuilder();
        
        resultado.append("Identificação do segmento (registro): ").append(extrairCampo(0, 3)).append("\n")
                .append("Identificação do processo: ").append(extrairCampo(3, 6)).append("\n")
                .append("Número da versão da transação: ").append(extrairCampo(6, 8)).append("\n")
                .append("Número de controle transmissão: ").append(extrairCampo(8, 13)).append("\n")
                .append("Identificação da geração do movimento: ").append(extrairCampo(13, 25)).append("\n")
                .append("Identificação do transmissor na comunicação: ").append(extrairCampo(25, 39)).append("\n")
                .append("Identificação do receptor na comunicação: ").append(extrairCampo(39, 53)).append("\n")
                .append("Código interno do transmissor: ").append(extrairCampo(53, 61)).append("\n")
                .append("Código interno do receptor: ").append(extrairCampo(61, 69)).append("\n")
                .append("Nome do transmissor: ").append(extrairCampo(69, 94)).append("\n")
                .append("Nome do receptor: ").append(extrairCampo(94, 119)).append("\n")
                .append("Espaços: ").append(extrairCampo(119, 128)).append("\n");
        
		return resultado.toString();
    }

    private String extrairCampo(int inicio, int fim) {
        if (conteudo.length() >= fim) {
            return conteudo.substring(inicio, fim).trim();
        }
        return ""; 
    }

    @Override
    public String toString() {
        return conteudo.replace("\n", "<br/>");
    }

}
