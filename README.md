## Fabio Henrique Neves Reis Ribeiro - 201135013 -Ciência da computação

## Modelo de persistência 

Foram utilizados 4 arquivos para guardar os dados, os dados foram separados utilizando uma tabulação.

As datas foram guardadas em milissegundos.

Os campos boolean foram guardados na forma de 0 ou 1.

  * Mesas(mesas.txt)
    * ID
    * Descricao
  * Produtos(produtos.txt)
    * ID
    * Descrição
    * Preço
  * Pedidos(pedidos.txt)
    * ID da Mesa
    * ID do pedido
    * Data do pedido
    * Data de fechamento(se possuir)
    * Status do pedido
  * Produtos do pedido(produtosPedido.txt)
    * ID da Mesa
    * ID do pedido
    * ID do produto
## Melhorias futuras
É necessário fazer otimizações no sistema, no carregamento do arquivo, mudando o estilo das listas utilizando um hash, para evitar de varrer a lista, quando for adicionar um produto em um pedido.

Melhorar a maneira como os dados são salvos, por a cada edição o arquivo é rescrito.
