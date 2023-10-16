DROP VIEW vw_detalhes_pedidos;
DROP VIEW vw_detalhes_pedidos2;
DROP VIEW vw_detalhes_financeiro;

DROP TABLE tb_pedidos_pizzas;
DROP TABLE tb_pizzas_adicionais;
DROP TABLE tb_tipos_pizzas_ingredientes;
DROP TABLE tb_vendas;
DROP TABLE tb_pizzas;
DROP TABLE tb_pedidos;
DROP TABLE tb_clientes;
DROP TABLE tb_funcionarios;
DROP TABLE tb_tipos_pizzas;
DROP TABLE tb_produtos;


DROP FUNCTION calcular_lucro;
DROP FUNCTION atualizar_vendas_apos_insercao_pedido;
DROP FUNCTION criar_venda_apos_insercao_pizza_pedido;
DROP FUNCTION criar_venda_apos_insercao_produto;