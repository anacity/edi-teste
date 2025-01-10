
# Leitor de Arquivo

A aplicação foi desenvolvida em Java utilizando o framework Spring Boot para processar arquivos contendo pedidos (em formato de texto), extrair informações estruturadas e retornar esses dados em formato JSON. A aplicação lê os dados de pedidos de compra e entrega, processando as informações de cada pedido e seus itens.

### Estrutura de Pacotes

- **com.example.leitor.controller**: Controladores que lidam com as requisições HTTP.
- **com.example.leitor.model**: Modelos de dados utilizados na aplicação.
- **com.example.leitor**: Classes responsáveis por processar os arquivos PE1 e PE3.
### Principais Componentes

### 1. **ArquivoController**

Responsável por gerenciar o upload de arquivos e processar os dados dos pedidos contidos no arquivo. Através do método `uploadArquivo`, o controlador recebe um arquivo de entrada e realiza as seguintes etapas:

- Lê o conteúdo do arquivo enviado.
- Procura por blocos de dados identificados por "PE1" e "PE3", extraindo e processando as informações de cada pedido.
- Converte os pedidos processados para o formato JSON.
- Retorna o JSON como resposta na requisição HTTP.

### 2. **Sessao**

Classe abstrata que representa uma sessão de dados (PE1 ou PE3). Possui o método `processarConteudo` que deve ser implementado pelas classes concretas (PE1, PE3) para processar o conteúdo da sessão de maneira apropriada.

### 3. **PE1**

Classe que estende a classe `Sessao`, representando uma sessão de dados do tipo PE1. Ela contém informações sobre o pedido, como:

- Data de emissão do programa atual.
- Código do item do cliente.
- Número do pedido de compra.

Além disso, o PE1 lida com a formatação da quantidade com base no número de casas decimais especificado.

Métodos principais:

- `formatarQuantidade`: Formata a quantidade conforme as casas decimais especificadas.
- `processarConteudo`: Processa o conteúdo do PE1 e cria um objeto `Pedido`.

### 4. **PE3**

Classe que estende a classe `Sessao`, representando uma sessão de dados do tipo PE3. Ela contém informações sobre as entregas associadas ao pedido, como:

- Datas de entrega.
- Horas de entrega.
- Quantidades entregues.

Ela também usa o método `formatarQuantidade` da classe `PE1` para formatar as quantidades.

Métodos principais:

- `processarConteudo`: Processa as informações de entrega e cria uma lista de objetos `ItemPedido`.

### 5. **Pedido**

Representa um pedido de compra, contendo informações sobre a data de emissão, código do item e número do pedido. Também contém uma lista de itens (`ItemPedido`), que são as entregas associadas ao pedido.

### 6. **ItemPedido**

Representa um item dentro de um pedido, contendo:

- Quantidade.
- Data da entrega.
- Hora da entrega.

---

### Fluxo da Aplicação

1. **Upload do Arquivo:**
    - O usuário envia um arquivo através de uma requisição HTTP POST para o endpoint `/api/upload`.
    - O controlador `ArquivoController` processa o arquivo e o lê linha por linha.
2. **Processamento do Conteúdo:**
    - O conteúdo do arquivo é lido e procurado por segmentos de dados identificados por "PE1" e "PE3".
    - Para cada "PE1" encontrado, são extraídas informações sobre o pedido (como data de emissão, código do item e número do pedido).
    - Para cada "PE3" encontrado, são extraídas as entregas associadas ao pedido, incluindo datas, horas e quantidades entregues.
3. **Formatação de Quantidade:**
    - No PE1, o número de casas decimais é verificado. Se for zero, a quantidade é formatada para remover zeros à esquerda. Se for dois, a quantidade é formatada com duas casas decimais e vírgula como separador.
4. **Resposta JSON:**
    - Após processar todos os pedidos, a lista de pedidos (e seus itens) é convertida para JSON utilizando a biblioteca Jackson (`ObjectMapper`).
    - O JSON gerado é retornado como resposta na requisição HTTP.
