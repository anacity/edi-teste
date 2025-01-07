package com.example.leitor.bean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.example.leitor.model.Segmento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("segmentoBean")
@SessionScoped
public class SegmentoBean implements Serializable {

    private Segmento segmento;

    public SegmentoBean() {
        segmento = new Segmento("PE1", "Conteúdo do segmento exemplo...");
        segmento.setDataEmissaoProgramaAtual("2025-01-07");
        segmento.setNumeroPedidoCompra("123456789");

        List<String> datas = new ArrayList<>();
        datas.add("2025-02-01");
        datas.add("2025-02-05");
        segmento.setDatasEntregaEmbarque(datas);

        List<String> quantidades = new ArrayList<>();
        quantidades.add("100");
        quantidades.add("200");
        segmento.setQuantidadesEntregaEmbarque(quantidades);
    }

    public Segmento getSegmento() {
        return segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }
}
