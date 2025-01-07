package com.example.leitor.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.UploadedFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@ManagedBean(name = "uploadBean")
@SessionScoped
public class UploadBean {

    private UploadedFile file;
    private String message;

    // Getter e Setter para o arquivo
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void uploadArquivo() {
        if (file != null && !file.getFileName().isEmpty()) {
            try {
                // Convertendo o UploadedFile em MultipartFile
                MultipartFile multipartFile = convertToMultipartFile(file);

                // Usando o RestTemplate para chamar o endpoint do Spring Boot
                RestTemplate restTemplate = new RestTemplate();
                String url = "http://localhost:8080/api/upload";  // URL do seu controller

                // Criando a requisição com o arquivo
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);

                HttpEntity<MultipartFile> entity = new HttpEntity<>(multipartFile, headers);
                String response = restTemplate.postForObject(url, entity, String.class);

                // Exibindo a resposta
                message = response;

            } catch (Exception e) {
                message = "Erro ao enviar o arquivo!";
                e.printStackTrace();
            }
        } else {
            message = "Por favor, selecione um arquivo para enviar!";
        }
    }

    // Método para converter UploadedFile para MultipartFile
    private MultipartFile convertToMultipartFile(UploadedFile file) throws IOException {
        byte[] bytes = new byte[(int) file.getSize()];
        InputStream inputStream = file.getInputstream();
        inputStream.read(bytes);
        inputStream.close();

        return new MultipartFile() {
            @Override
            public String getName() {
                return file.getFileName();
            }

            @Override
            public String getOriginalFilename() {
                return file.getFileName();
            }

            @Override
            public long getSize() {
                return file.getSize();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return bytes;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(bytes);
            }

            @Override
            public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
                throw new UnsupportedOperationException();
            }

			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
        };
    }
}
