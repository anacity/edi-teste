package com.example.leitor;

import com.example.leitor.model.ItemPedido;
import com.example.leitor.model.Pedido;
import com.example.leitor.model.Sessao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PE3 extends Sessao {
    private List<String> datasEntrega = new ArrayList<>();
    private List<String> horasEntrega = new ArrayList<>();
    private List<String> quantidadesEntrega = new ArrayList<>();

    private PE1 pe1;  // Referência para o PE1, para acessar a quantidade formatada

    public PE3(String conteudo, PE1 pe1) {
        super(conteudo);
        this.pe1 = pe1; // Passa o PE1 para poder acessar o número de casas decimais
        if (conteudo.startsWith("PE3")) {
            extrairDados(conteudo.substring(3));
        }
    }

    private void extrairDados(String conteudo) {
        int posicao = 0;
        while (posicao + 6 <= conteudo.length()) {
            String data = conteudo.substring(posicao, posicao + 6).trim();
            if (!data.isEmpty()) {
                datasEntrega.add(data);
            }

            String hora = conteudo.substring(posicao + 6, posicao + 8).trim();
            if (!hora.isEmpty()) {
                horasEntrega.add(hora);
            }

            String quantidade = conteudo.substring(posicao + 8, posicao + 17).trim();
            if (!quantidade.isEmpty()) {
                // Usa o método formatarQuantidade da PE1 para formatar a quantidade
                quantidadesEntrega.add(pe1.formatarQuantidade(quantidade));
            }

            posicao += 17;
        }

        // Log the number of items extracted
        System.out.println("Extracted " + datasEntrega.size() + " delivery dates, " +
                           horasEntrega.size() + " delivery hours, and " +
                           quantidadesEntrega.size() + " quantities from PE3.");
    }

    @Override
    public Map<String, Object> processarConteudo() {
        // No need to create a new Pedido here
        // Just return the items
        List<ItemPedido> itens = new ArrayList<>();
        for (int i = 0; i < datasEntrega.size(); i++) {
            String dataFormatada = converterData(datasEntrega.get(i));
            ItemPedido item = new ItemPedido(quantidadesEntrega.get(i), dataFormatada, horasEntrega.get(i));
            itens.add(item);
        }

        // Return the items instead of creating a new Pedido
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("itens", itens);
        return resultado;
    }

    // Converter a data para o formato DD-MM-YY
    public String converterData(String dataOriginal) {
        if (dataOriginal != null && dataOriginal.length() == 6) {
            String ano = dataOriginal.substring(0, 2);
            String mes = dataOriginal.substring(2, 4);
            String dia = dataOriginal.substring(4, 6);
            return dia + "-" + mes + "-" + ano;
        }
        return dataOriginal;
    }
}
