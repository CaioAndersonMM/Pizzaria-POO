---------- TRIGGERS
---------- Gastos a cada produto adicionado
CREATE OR REPLACE FUNCTION criar_venda_apos_insercao_produto()
RETURNS TRIGGER AS $$
BEGIN
-- Insira uma nova linha na tabela tb_vendas
  INSERT INTO tb_vendas (id_pedido, id_produto, gasto)
  VALUES (NULL, NEW.id, NEW.valor);
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Crie o gatilho que é acionado após a inserção de um novo produto
CREATE TRIGGER trigger_criar_venda_apos_insercao_produto
AFTER INSERT
ON tb_produtos
FOR EACH ROW
EXECUTE FUNCTION criar_venda_apos_insercao_produto();

--------- Lucro a cada pizza adicionada
CREATE OR REPLACE FUNCTION criar_venda_apos_insercao_pizza()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO tb_vendas (id_pedido, lucro)
  VALUES (NEW.id_pedido, NEW.valor);
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Crie o trigger que é acionado após a inserção de uma nova pizza
CREATE TRIGGER trigger_criar_venda_apos_insercao_pizza
AFTER INSERT
ON tb_pizzas
FOR EACH ROW
EXECUTE FUNCTION criar_venda_apos_insercao_pizza();



----------------- FUNCTION --------------------
----------------- Função para calcular o lucro
CREATE OR REPLACE FUNCTION calcular_lucro()
RETURNS NUMERIC(10, 2) AS $$
DECLARE
    total_ganhos NUMERIC(10, 2);
    total_gastos NUMERIC(10, 2);
    lucro NUMERIC(10, 2);
BEGIN
    -- Calcule o total de ganhos
    SELECT SUM(ganhos) INTO total_ganhos FROM tb_vendas;
    
    -- Calcule o total de gastos
    SELECT SUM(gastos) INTO total_gastos FROM tb_vendas;
    
    -- Calcule o lucro subtraindo os gastos dos ganhos
    lucro := total_ganhos - total_gastos;
    
    -- Retorne o valor do lucro
    RETURN lucro;
END;
$$ LANGUAGE plpgsql;


--------------- RULES ----------------
------ Impede a inserção de pedidos com um valor inferior a 10 na tabela
CREATE OR REPLACE RULE valida_valor_pedido AS
ON INSERT TO tb_pedidos
WHERE (NEW.valor < 10)
DO INSTEAD NOTHING;



----------- VIEWS --------------------
-- INTERESSANTE COLOCAR NO PDF QUE SERÁ GERADO
CREATE OR REPLACE VIEW vw_detalhes_pedidos AS
SELECT
  p.id AS id_pedido,
  p.valor,
  c.cpf AS cpf_cliente,
  c.nome AS nome_cliente,
  f.nome AS nome_funcionario
FROM tb_pedidos p
LEFT JOIN tb_clientes c ON p.id_cliente = c.id
LEFT JOIN tb_funcionarios f ON p.id_funcionario = f.id;