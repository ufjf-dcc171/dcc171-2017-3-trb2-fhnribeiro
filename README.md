## Fabio Henrique Neves Reis Ribeiro - 201135013 -Ciência da computação
## Cenário
Restaurante ou bar que seja possível ter mais de uma pedido aberto, por mesa. Sendo que o pedido pode ser editado enquanto estiver aberto, após o mesmo ser fechado, não é possivel sua edição.
## Modelo de dados
Foram utilizados 3 classes principais
  * Mesa
    * Lista de pedidos
    * descricao
  * Pedido
    * id
    * Lista de produtos
    * dataInicio
    * dataFinal
    * status
  * Produto
    * id
    * nome
    * descricao
    * preco
## Campos necessários para a construção das telas
  O sistema possui 3 telas
  * Mesas
    * Possui 4 botões novo pedido, remover pedido, editar pedido e fechar pedido.<br/>
    Além disso possui duas lista, uma lista de mesas e uma de pedidos, a lista de pedidos é preenchida após a seleção de uma mesa. Ambos possuem um list model próprio.
  * Pedido
    * Possui duas listas uma com todos os produtos ofertados pelo estabelecimento e outro com os produtos adicionados no pedido.<br>
    Possui dois botões adicionar e remover.
  * Nota Fiscal
    * Possui uma tabela com todos os items do pedido, o valor total do pedido.<br/>Possui dois labels com a data de abertura e do fechamento do pedido.
## Pontos importantes do funcionamento da interface
  Os botões de edição, fechamento e remoção do pedido só são habilitados após a seleção do mesmo na lista de pedidos.<br/>
  Se o pedido já estiver fechado, somento o botão de visualizar pedido é habilitado.
## Pontos de maior dificuldade de implementação
  O mais complicado da implementação do sistema, é realizar o controle das múltiplas janelas, para que a edição em uma janela não interfira na outra.
## Melhorias futuras
  * Persistência de dados.
  * Ser possível editar multiplos pedidos ao mesmo tempo.
  * Melhorias visuais.
