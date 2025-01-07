package com.example.leitor;

import com.example.leitor.model.Sessao;

public class PE1 extends Sessao {

    public PE1(String conteudo) {
        super(conteudo);
    }

    @Override
    public String processarConteudo() {
        StringBuilder resultado = new StringBuilder();
        
        resultado.append("Identificação do segmento (registro): ").append(extrairSubstring(0, 3)).append("\n")
                .append("Código da fábrica entrega: ").append(extrairSubstring(3, 6)).append("\n")
                .append("Identificação do programa atual: ").append(extrairSubstring(6, 15)).append("\n")
                .append("Data de emissão do programa atual: ").append(extrairSubstring(15, 21)).append("\n")
                .append("Identificação do programa anterior: ").append(extrairSubstring(21, 30)).append("\n")
                .append("Data do programa anterior: ").append(extrairSubstring(30, 36)).append("\n")
                .append("Código do item do cliente: ").append(extrairSubstring(36, 66)).append("\n")
                .append("Código do item do fornecedor: ").append(extrairSubstring(66, 96)).append("\n")
                .append("Número do pedido de compra: ").append(extrairSubstring(96, 108)).append("\n")
                .append("Código do local de destino: ").append(extrairSubstring(108, 113)).append("\n")
                .append("Identificação para contato: ").append(extrairSubstring(113, 124)).append("\n")
                .append("Código da unidade de medida do item: ").append(extrairSubstring(124, 126)).append("\n")
                .append("Quantidade de casas decimais: ").append(extrairSubstring(126, 127)).append("\n")
                .append("Código tipo de fornecimento: ").append(extrairSubstring(127, 128)).append("\n");

        return resultado.toString();  
    }

    private String extrairSubstring(int inicio, int fim) {
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
