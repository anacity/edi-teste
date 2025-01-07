package com.example.leitor;

import com.example.leitor.model.Sessao;

public class PE3 extends Sessao{

	public PE3(String conteudo) {
		super(conteudo);
		
	}

	@Override
	public String processarConteudo() {
		StringBuilder resultado = new StringBuilder();
		
		resultado.append("Identificação do segmento (registro)").append(extrairSubstring(0, 3)).append("\n")
			.append("Data de entrega/embarque do item: ").append(extrairSubstring(3, 9)).append("\n")
			.append("Hora de entrega/embarque do item: ").append(extrairSubstring(9, 11)).append("\n")
			.append("Quantidade do item a ser entregue ou embarcada: ").append(extrairSubstring(11, 20)).append("\n")
			.append("Data entrega/embarque do item (1): ").append(extrairSubstring(20, 26)).append("\n")
			.append("Hora Para Entrega/embarque Item (2): ").append(extrairSubstring(26, 28)).append("\n")
			.append("Quantidade Entrega/embarque Do Item (3): ").append(extrairSubstring(28, 37)).append("\n")
			.append("Data Entrega/embarque Do Item (1): ").append(extrairSubstring(37, 43)).append("\n")
			.append("Hora Para Entrega/embarque Item (2): ").append(extrairSubstring(43, 45)).append("\n")
			.append("Quantidade Entrega/embarque Do Item (3): ").append(extrairSubstring(45, 54)).append("\n")
			.append("Data Entrega/embarque Do Item (1): ").append(extrairSubstring(54, 60)).append("\n")
			.append("Hora Para Entrega/embarque Item (2): ").append(extrairSubstring(60, 62)).append("\n")
			.append("Quantidade Entrega/embarque Do Item (3): ").append(extrairSubstring(62, 71)).append("\n")
			.append(" DATA ENTREGA/EMBARQUE DO ITEM (1): ").append(extrairSubstring(71, 77)).append("\n")
			.append("HORA PARA ENTREGA/EMBARQUE ITEM (2): ").append(extrairSubstring(77, 79)).append("\n")
			.append("QUANTIDADE ENTREGA/EMBARQUE DO ITEM (3): ").append(extrairSubstring(79, 88)).append("\n")
			.append("DATA ENTREGA/EMBARQUE DO ITEM (1): ").append(extrairSubstring(88, 94)).append("\n")
			.append("HORA PARA ENTREGA/EMBARQUE ITEM (2): ").append(extrairSubstring(94, 96)).append("\n")
			.append("QUANTIDADE ENTREGA/EMBARQUE DO ITEM (3): ").append(extrairSubstring(96, 105)).append("\n")
			.append("DATA ENTREGA/EMBARQUE DO ITEM (1): ").append(extrairSubstring(105, 111)).append("\n")
			.append("HORA PARA ENTREGA/EMBARQUE ITEM (2): ").append(extrairSubstring(111, 113)).append("\n")
			.append("QUANTIDADE ENTREGA/EMBARQUE DO ITEM (3): ").append(extrairSubstring(113, 122)).append("\n")
			.append("ESPAÇOS: ").append(extrairSubstring(122, 128)).append("\n");
		
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
