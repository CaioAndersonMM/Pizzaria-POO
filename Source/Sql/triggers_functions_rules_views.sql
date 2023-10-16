---------- TRIGGERS
---------- Gastos a cada produto adicionado
CREATE OR REPLACE FUNCTION criar_venda_apos_insercao_produto()
RETURNS TRIGGER AS $$
BEGIN
  -- Insira uma nova linha na tabela tb_vendas com o valor multiplicado pela quantidade
INSERT INTO tb_vendas (id_pedido, id_produto, gastos)
VALUES (NULL, NEW.id, NEW.valor * NEW.quantidade);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Crie o gatilho que é acionado após a inserção de um novo produto
CREATE TRIGGER trigger_criar_venda_apos_insercao_produto
    AFTER INSERT
    ON tb_produtos
    FOR EACH ROW
    EXECUTE FUNCTION criar_venda_apos_insercao_produto();



--------- Lucro a cada pedido adicionado
CREATE OR REPLACE FUNCTION atualizar_vendas_apos_insercao_pedido()
RETURNS TRIGGER AS $$
BEGIN

  INSERT INTO tb_vendas (id_pedido, ganhos)
  VALUES (NEW.id, NEW.valor);

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Crie o gatilho que é acionado após a inserção de um novo pedido
CREATE TRIGGER trigger_atualizar_vendas_apos_insercao_pedido
AFTER INSERT
ON tb_pedidos
FOR EACH ROW
EXECUTE FUNCTION atualizar_vendas_apos_insercao_pedido();


---------------- DECREMENTE EM PRODUTOS AO SER ADICIONADA UMA PIZZA --------------

CREATE OR REPLACE FUNCTION atualizar_quantidade_produto()
RETURNS TRIGGER AS $$
DECLARE
    ingredientes_cursor CURSOR FOR
        SELECT tpi.id_ingrediente, tpi.quantidade
        FROM tb_tipos_pizzas_ingredientes as tpi
        WHERE tpi.id_tipo_pizza = NEW.id_tipo_pizza;
    
    ing_id INT;
    ing_quantidade NUMERIC;
BEGIN
    OPEN ingredientes_cursor;
    
    LOOP
        FETCH ingredientes_cursor INTO ing_id, ing_quantidade;
        EXIT WHEN ing_id IS NULL;
        
        -- Atualize a tabela tb_produtos subtraindo a quantidade de ingredientes da tabela tb_tipos_pizzas_ingredientes
        UPDATE tb_produtos
        SET quantidade = quantidade - ing_quantidade
        WHERE id = ing_id;
    END LOOP;
    
    CLOSE ingredientes_cursor;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Crie o trigger para ser acionado após a inserção de uma nova pizza
CREATE TRIGGER trigger_atualizar_quantidade_produto
AFTER INSERT ON tb_pizzas
FOR EACH ROW
EXECUTE FUNCTION atualizar_quantidade_produto();




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

-------------- VIEW DE RELATORIO ----------------

CREATE OR REPLACE VIEW vw_detalhes_pedidos2 AS
SELECT
    p.id AS id_pedido,
    p.valor,
    p.status,
    p.data_criacao AS data,
    c.nome AS nome_cliente,
    tp.nome AS sabor
FROM tb_pedidos p
LEFT JOIN tb_clientes c ON p.id_cliente = c.id
LEFT JOIN tb_pedidos_pizzas pp ON p.id = pp.id_pedido
LEFT JOIN tb_pizzas pz ON pp.id_pizza = pz.id
LEFT JOIN tb_tipos_pizzas tp ON pz.id_tipo_pizza = tp.id;


CREATE OR REPLACE VIEW vw_detalhes_financeiro AS
SELECT
    v.ganhos AS ganhos,
    v.gastos AS gastos
FROM tb_vendas v;
