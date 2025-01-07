package com.example.leitor.model;

import java.util.ArrayList;
import java.util.List;

public class Segmento {
    private String tipo;
    private String conteudo;
    private String dataEmissaoProgramaAtual;
    private String numeroPedidoCompra;
    
    private List<String> datasEntregaEmbarque;
    private List<String> quantidadesEntregaEmbarque;

    public Segmento(String tipo, String conteudo) {
        this.tipo = tipo;
        this.conteudo = conteudo;
        this.datasEntregaEmbarque = new ArrayList<>();
        this.quantidadesEntregaEmbarque = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    // Aqui está o método extrairTipo
    public static String extrairTipo(String conteudo) {
        if (conteudo.startsWith("ITP")) {
            return "ITP";
        } else if (conteudo.startsWith("PE1")) {
            return "PE1";
        } else if (conteudo.startsWith("PE2")) {
            return "PE2";
        } else if (conteudo.startsWith("PE3")) {
            return "PE3";
        }
        return "Desconhecido";  // Caso o tipo não seja identificado
    }

    // Método para processar o conteúdo do segmento
    public String processarConteudo() {
        StringBuilder resultado = new StringBuilder();
        
        switch (tipo) {
            case "ITP":
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
                break;
            case "PE1":
            	
            	dataEmissaoProgramaAtual = extrairSubstring(15, 21);
            	numeroPedidoCompra = extrairSubstring(96, 108);
            	
            	resultado.append("Identificação do segmento (registro): ").append(extrairSubstring(0, 3)).append("\n")
                .append("Código da fábrica entrega: ").append(extrairSubstring(3, 6)).append("\n")
                .append("Identificação do programa atual: ").append(extrairSubstring(6, 15)).append("\n")
                .append("Data de emissão do programa atual: ").append(dataEmissaoProgramaAtual).append("\n")
                .append("Identificação do programa anterior: ").append(extrairSubstring(21, 30)).append("\n")
                .append("Data do programa anterior: ").append(extrairSubstring(30, 36)).append("\n")
                .append("Código do item do cliente: ").append(extrairSubstring(36, 66)).append("\n")
                .append("Código do item do fornecedor: ").append(extrairSubstring(66, 96)).append("\n")
                .append("Número do pedido de compra: ").append(numeroPedidoCompra).append("\n")
                .append("Código do local de destino: ").append(extrairSubstring(108, 113)).append("\n")
                .append("Identificação para contato: ").append(extrairSubstring(113, 124)).append("\n")
                .append("Código da unidade de medida do item: ").append(extrairSubstring(124, 126)).append("\n")
                .append("Quantidade de casas decimais: ").append(extrairSubstring(126, 127)).append("\n")
                .append("Código tipo de fornecimento: ").append(extrairSubstring(127, 128)).append("\n");
                break;
            case "PE2":
            	resultado.append("Identificação do segmento (registro): ").append(extrairSubstring(0, 3)).append("\n")
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
                break;
            case "PE3":
            
                // Variáveis para o índice inicial e o tamanho do intervalo
                int pos = 3; // Posição inicial no conteúdo
                int dataLength = 6; // Comprimento da data (6 caracteres)
                int quantidadeLength = 9; // Comprimento da quantidade (9 caracteres)

                // Loop alternado para adicionar datas e quantidades
                for (int i = 0; i < 5; i++) { // Vamos iterar 5 vezes como exemplo. Ajuste conforme o número de dados.
                    // Extrai a data
                    String data = extrairSubstring(pos, pos + dataLength);
                    if (!data.isEmpty()) {
                        datasEntregaEmbarque.add(data); // Adiciona a data na lista
                    }
                    pos += dataLength; // Atualiza a posição para a próxima parte

                    // Pula a parte da hora (2 caracteres), já que não é necessária
                    pos += 2; // Ignorando os 2 caracteres para a hora

                    // Extrai a quantidade
                    String quantidade = extrairSubstring(pos, pos + quantidadeLength);
                    if (!quantidade.isEmpty()) {
                        quantidadesEntregaEmbarque.add(quantidade); // Adiciona a quantidade na lista
                    }
                    pos += quantidadeLength; // Atualiza a posição para a próxima parte
                }

                // Exibindo os valores extraídos
                resultado.append("Dados de Entrega/Embarque:\n");
                for (int i = 0; i < datasEntregaEmbarque.size(); i++) {
                    resultado.append("Data de entrega/embarque: ").append(datasEntregaEmbarque.get(i))
                            .append(" | Quantidade: ").append(quantidadesEntregaEmbarque.get(i)).append("\n");
                }

                // Exibindo as outras informações do segmento PE3
                resultado.append("Identificação do segmento (registro): ").append(extrairSubstring(0, 3)).append("\n")
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
                        .append("DATA ENTREGA/EMBARQUE DO ITEM (1): ").append(extrairSubstring(71, 77)).append("\n")
                        .append("HORA PARA ENTREGA/EMBARQUE ITEM (2): ").append(extrairSubstring(77, 79)).append("\n");
                break;

            default:
                resultado.append("Tipo de segmento desconhecido");
            }


        return resultado.toString();
        
    }
    
    public String getDataEmissaoProgramaAtual() {
		return dataEmissaoProgramaAtual;
	}

	public void setDataEmissaoProgramaAtual(String dataEmissaoProgramaAtual) {
		this.dataEmissaoProgramaAtual = dataEmissaoProgramaAtual;
	}

	public String getNumeroPedidoCompra() {
		return numeroPedidoCompra;
	}

	public void setNumeroPedidoCompra(String numeroPedidoCompra) {
		this.numeroPedidoCompra = numeroPedidoCompra;
	}

	public List<String> getDatasEntregaEmbarque() {
		return datasEntregaEmbarque;
	}

	public void setDatasEntregaEmbarque(List<String> datasEntregaEmbarque) {
		this.datasEntregaEmbarque = datasEntregaEmbarque;
	}

	public List<String> getQuantidadesEntregaEmbarque() {
		return quantidadesEntregaEmbarque;
	}

	public void setQuantidadesEntregaEmbarque(List<String> quantidadesEntregaEmbarque) {
		this.quantidadesEntregaEmbarque = quantidadesEntregaEmbarque;
	}

	private String extrairCampo(int inicio, int fim) {
        if (conteudo.length() >= fim) {
            return conteudo.substring(inicio, fim).trim();
        }
        return "";
    }

    private String extrairSubstring(int inicio, int fim) {
        if (conteudo.length() >= fim) {
            return conteudo.substring(inicio, fim).trim();
        }
        return "";
    }
}
