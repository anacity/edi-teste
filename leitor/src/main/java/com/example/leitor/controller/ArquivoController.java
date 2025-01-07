package com.example.leitor.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.leitor.ITP;
import com.example.leitor.PE1;
import com.example.leitor.PE2;
import com.example.leitor.PE3;
import com.example.leitor.model.Segmento;
import com.example.leitor.model.Sessao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ArquivoController {

    @PostMapping("/api/upload")
    public String uploadArquivo(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Nenhum arquivo foi enviado!";
        }

        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao ler o arquivo!";
        }

        String texto = conteudo.toString();
        List<Sessao> sessoes = new ArrayList<>();
        int inicioSessao = 0; // Começa do início do arquivo

        // Tipos de segmento a serem processados
        String[] tiposDeSegmentos = {"ITP", "PE1", "PE2", "PE3"};

        while (inicioSessao < texto.length()) {
            boolean segmentoEncontrado = false;

            // Para cada tipo de segmento, vamos procurar no texto
            for (String tipo : tiposDeSegmentos) {
                int posTipo = texto.indexOf(tipo, inicioSessao);  // Encontrar o tipo a partir da posição de início

                if (posTipo != -1) {
                    // Segmento encontrado, podemos processar o conteúdo
                    segmentoEncontrado = true;

                    // Limitar o segmento a 128 caracteres, conforme a regra
                    int fimSessao = posTipo + 128;  // A posição do fim do segmento
                    if (fimSessao > texto.length()) {
                        fimSessao = texto.length();  // Evita exceder o fim do texto
                    }

                    // Extrair o conteúdo do segmento
                    String conteudoSegmento = texto.substring(posTipo, fimSessao).trim();
                    String tipoSegmento = Segmento.extrairTipo(conteudoSegmento);

                    // Adicionar o segmento à lista de sessões
                    switch (tipoSegmento) {
                        case "ITP":
                            sessoes.add(new ITP(conteudoSegmento));
                            break;
                        case "PE1":
                            sessoes.add(new PE1(conteudoSegmento));
                            break;
                        case "PE2":
                            sessoes.add(new PE2(conteudoSegmento));
                            break;
                        case "PE3":
                            sessoes.add(new PE3(conteudoSegmento));
                            break;
                        default:
                            // Caso o tipo de segmento não seja reconhecido
                            break;
                    }

                    // Atualizar o ponto de início para continuar a busca após o segmento encontrado
                    inicioSessao = fimSessao;
                    break;  // Para procurar o próximo segmento após encontrar um
                }
            }

            // Se nenhum segmento foi encontrado, saímos do loop
            if (!segmentoEncontrado) {
                break;
            }
        }

        // Processar os conteúdos de todas as sessões
        StringBuilder resultado = new StringBuilder();
        for (Sessao sessao : sessoes) {
            String resultadoSessao = sessao.processarConteudo();
            resultado.append(resultadoSessao).append("\n");
        }

        return resultado.toString();
    }
}