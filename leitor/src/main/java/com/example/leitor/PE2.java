package com.example.leitor;

import com.example.leitor.model.Sessao;

public class PE2 extends Sessao{

	public PE2(String conteudo) {
		super(conteudo);
	}
	
	@Override
    public String processarConteudo() {
        StringBuilder resultado = new StringBuilder();
     
        resultado.append("Identificação do segmento (registro): ").append(extrairSubstring(0,3)).append("\n")
        	.append("Data da última entrega: ").append(extrairSubstring(3, 9)).append("\n")
        	.append("Número da nota fiscal: ").append(extrairSubstring(9, 15)).append("\n")
        	.append("Série da última nota fiscal: ").append(extrairSubstring(15, 19)).append("\n")
        	.append("Data da última nota fiscal: ").append(extrairSubstring(19, 25)).append("\n")
        	.append("Quantidade da última entrega: ").append(extrairSubstring(25, 37)).append("\n")
        	.append("Quantidade entrega acumulada: ").append(extrairSubstring(37, 51)).append("\n")
        	.append("Quantidade necessária acumulada: ").append(extrairSubstring(51, 65)).append("\n")
        	.append("Quantidade lote mínimo: ").append(extrairSubstring(65, 77)).append("\n")
        	.append("Código frequência de fornecimento: ").append(extrairSubstring(77, 80)).append("\n")
        	.append("Data liberação para produção: ").append(extrairSubstring(80, 84)).append("\n")
        	.append("Data liberação matéria prima: ").append(extrairSubstring(84, 88)).append("\n")
        	.append("Código local de descarga: ").append(extrairSubstring(88, 95)).append("\n")
        	.append("Período de entrega/embarque: ").append(extrairSubstring(95, 99)).append("\n")
        	.append("Códigog situação do item: ").append(extrairSubstring(99, 101)).append("\n")
        	.append("Identificação do tipo de programação: ").append(extrairSubstring(101, 102)).append("\n")
        	.append("Pedido da revenda: ").append(extrairSubstring(102, 115)).append("\n")
        	.append("Qualificação da programação: ").append(extrairSubstring(115, 116)).append("\n")
        	.append("Tipo de pedido da revenda: ").append(extrairSubstring(116, 118)).append("\n")
        	.append("Código da via de transporte: ").append(extrairSubstring(118, 121)).append("\n")
        	.append("Espaços: ").append(extrairSubstring(121, 128)).append("\n");
        
        System.out.println(resultado.toString());
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
