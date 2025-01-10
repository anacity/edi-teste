package com.example.leitor.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.leitor.PE1;
import com.example.leitor.PE3;
import com.example.leitor.model.ItemPedido;
import com.example.leitor.model.Pedido;
import com.example.leitor.model.Sessao;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ArquivoController {

	@PostMapping("/api/upload")
	public ResponseEntity<String> uploadArquivo(@RequestParam("file") MultipartFile file) {
	    if (file.isEmpty()) {
	        return ResponseEntity.badRequest().body("Nenhum arquivo foi enviado!");
	    }

	    StringBuilder conteudo = new StringBuilder();

	    try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
	        String linha;
	        while ((linha = br.readLine()) != null) {
	            conteudo.append(linha).append("\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Erro ao ler o arquivo!");
	    }

	    String texto = conteudo.toString();
	    List<Pedido> pedidos = new ArrayList<>();  // Lista para armazenar os pedidos completos
	    int inicioSessao = 0;

	    while (inicioSessao < texto.length()) {
	        // Procura por um PE1
	        int posPE1 = texto.indexOf("PE1", inicioSessao);
	        if (posPE1 == -1) {
	            break; // Não encontrou mais PE1, então finaliza o loop
	        }

	        // Processa o PE1
	        int fimSessaoPE1 = posPE1 + 128;  // Segmento acaba após 128 caracteres
	        if (fimSessaoPE1 > texto.length()) fimSessaoPE1 = texto.length();
	        String conteudoPE1 = texto.substring(posPE1, fimSessaoPE1).trim();
	        PE1 pe1 = new PE1(conteudoPE1, "PE1");
	        Map<String, Object> dadosPE1 = pe1.processarConteudo();
	        Pedido pedidoAtual = (Pedido) dadosPE1.get("pedido");
	        List<ItemPedido> itensTemp = new ArrayList<>();  // Lista temporária para os itens do pedido

	        // Agora procuramos o PE3 correspondente a esse pedido, imediatamente após o PE1
	        int posPE3 = texto.indexOf("PE3", fimSessaoPE1);
	        if (posPE3 != -1) {
	            int fimSessaoPE3 = posPE3 + 128;  // Segmento acaba após 128 caracteres
	            if (fimSessaoPE3 > texto.length()) fimSessaoPE3 = texto.length();
	            String conteudoPE3 = texto.substring(posPE3, fimSessaoPE3).trim();
	            PE3 pe3 = new PE3(conteudoPE3, pe1);
	            Map<String, Object> dadosPE3 = pe3.processarConteudo();
	            if (dadosPE3.containsKey("itens")) {
	                List<ItemPedido> itensPE3 = (List<ItemPedido>) dadosPE3.get("itens");
	                itensTemp.addAll(itensPE3);  // Adiciona os itens ao pedido
	            }
	        }

	        // Adiciona os itens ao pedido
	        pedidoAtual.setItens(itensTemp);
	        pedidos.add(pedidoAtual);  // Adiciona o pedido completo à lista de pedidos

	        // Avança para o próximo segmento PE1
	        inicioSessao = fimSessaoPE1;
	    }

	    // Converte a lista de pedidos para JSON
	    ObjectMapper objectMapper = new ObjectMapper();
	    String resultadoJson;

	    try {
	        resultadoJson = objectMapper.writeValueAsString(pedidos);
	    } catch (IOException e) {
	        return ResponseEntity.status(500).body("Erro ao converter os dados para JSON!");
	    }

	    // Retorna a resposta com o JSON
	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pedidos.json")
	            .contentType(MediaType.APPLICATION_JSON)
	            .body(resultadoJson);
	}

}
