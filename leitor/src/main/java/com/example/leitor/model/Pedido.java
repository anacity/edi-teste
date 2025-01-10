package com.example.leitor.model;

import java.util.List;

public class Pedido {
    private String dataEmissaoProgramaAtual;
    private String codigoItemCliente;
    private String nPedidoCompra;
    private List<ItemPedido> itens;

    // Construtores, getters e setters
    public Pedido(String dataEmissaoProgramaAtual, String codigoItemCliente, String nPedidoCompras, List<ItemPedido> itens) {
        this.dataEmissaoProgramaAtual = dataEmissaoProgramaAtual;
        this.codigoItemCliente = codigoItemCliente;
        this.nPedidoCompra = nPedidoCompras;
        this.itens = itens;
    }

    public Pedido() {
		// TODO Auto-generated constructor stub
	}
    
    public String getnPedidoCompra() {
        return nPedidoCompra;
    }

    public void setnPedidoCompra(String nPedidoCompra) {
        this.nPedidoCompra = nPedidoCompra;
    }

	public String getDataEmissaoProgramaAtual() {
        return dataEmissaoProgramaAtual;
    }

    public void setDataEmissaoProgramaAtual(String dataEmissaoProgramaAtual) {
        this.dataEmissaoProgramaAtual = dataEmissaoProgramaAtual;
    }

    public String getCodigoItemCliente() {
        return codigoItemCliente;
    }

    public void setCodigoItemCliente(String codigoItemCliente) {
        this.codigoItemCliente = codigoItemCliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "dataEmissaoProgramaAtual='" + dataEmissaoProgramaAtual + '\'' +
                ", codigoItemCliente='" + codigoItemCliente + '\'' +
                ", nPedidoCompra='" + nPedidoCompra + '\'' +  
                ", itens=" + itens +
                '}'; // formatação para o JSON
    }

}
