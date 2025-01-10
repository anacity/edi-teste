package com.example.leitor;

import com.example.leitor.model.Pedido;
import com.example.leitor.model.Sessao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PE1 extends Sessao {
    private String dataEmissaoProgramaAtual;
    private String nPedidoCompra;
    private String codigoItemCliente;
    private int casasDecimais; // Novo campo para armazenar o número de casas decimais

    public PE1(String conteudo, String nCasaDecimal) {
        super(conteudo);
        // Aqui você deve extrair o número de casas decimais do conteúdo
        // Supondo que o número de casas decimais está em uma posição específica, ex: posição 20 até 21
        this.casasDecimais = Integer.parseInt(conteudo.substring(126, 127).trim());  // Ajuste conforme a posição exata no seu arquivo
    }

    @Override
    public Map<String, Object> processarConteudo() {
        Map<String, Object> resultado = new HashMap<>();

        // Extrair dados específicos do PE1
        dataEmissaoProgramaAtual = extrairSubstring(15, 21);
        codigoItemCliente = extrairSubstring(36, 66);
        nPedidoCompra = extrairSubstring(96, 108);
        
        String dataFormatada = converterData(dataEmissaoProgramaAtual);

        // Criar o objeto Pedido
        Pedido pedido = new Pedido(dataFormatada, codigoItemCliente, nPedidoCompra, new ArrayList<>());
        resultado.put("pedido", pedido);

        return resultado;
    }

    // Método para formatar a quantidade com base no número de casas decimais
    public String formatarQuantidade(String quantidade) {
        if (casasDecimais == 0) {
            // Remove os zeros à esquerda
            return String.valueOf(Integer.parseInt(quantidade.trim())); // Converte para inteiro e remove os zeros à esquerda
        } else if (casasDecimais == 2) {
            // Adiciona vírgula com 2 casas decimais
            double valor = Double.parseDouble(quantidade.trim());
            return String.format("%.2f", valor).replace('.', ','); // Formata para 2 casas decimais e substitui ponto por vírgula
        }
        return quantidade;  // Caso não tenha regra definida, retorna a quantidade original
    }

    // Método para extrair e formatar a data
    public String converterData(String dataOriginal) {
        if (dataOriginal != null && dataOriginal.length() == 6) {
            String ano = dataOriginal.substring(0, 2);
            String mes = dataOriginal.substring(2, 4);
            String dia = dataOriginal.substring(4, 6);
            return dia + "-" + mes + "-" + ano;
        }
        return dataOriginal;
    }

    private String extrairSubstring(int inicio, int fim) {
        if (getConteudo().length() >= fim) {
            return getConteudo().substring(inicio, fim).trim();
        }
        return "";
    }

    public String getDataEmissaoProgramaAtual() {
        return dataEmissaoProgramaAtual;
    }

    public String getCodigoItemCliente() {
        return codigoItemCliente;
    }

    public int getCasasDecimais() {
        return casasDecimais;
    }
}
