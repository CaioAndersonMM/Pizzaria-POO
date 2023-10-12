-- Inserir tipos de pizzas fictícios
INSERT INTO tb_tipos_pizzas (nome, valor_p, valor_m, valor_g)
VALUES
  ('Margherita', 25.00, 35.00, 45.00),
  ('Pepperoni', 28.00, 38.00, 48.00),
  ('Vegetariana', 30.00, 40.00, 50.00),
  ('Frango com Catupiry', 30.00, 40.00, 50.00),
  ('Calabresa', 28.00, 38.00, 48.00),
  ('Quatro Queijos', 32.00, 42.00, 52.00),
  ('Portuguesa', 31.00, 41.00, 51.00);

-- Inserir 9 ingredientes fictícios
INSERT INTO tb_produtos (nome, quantidade, valor)
VALUES
  ('Molho de Tomate', 0.2, 2.50),
  ('Queijo Mussarela', 0.3, 3.00),
  ('Pepperoni', 0.1, 4.00),
  ('Cogumelos', 0.15, 2.00),
  ('Pimentão', 0.1, 1.50),
  ('Cebola', 0.1, 1.50),
  ('Azeitonas', 0.05, 2.00),
  ('Tomate', 0.1, 1.50),
  ('Manjericão', 0.05, 1.00),
  ('Frango Desfiado', 0.15, 4.00),
  ('Catupiry', 0.15, 5.00),
  ('Calabresa', 0.2, 3.50),
  ('Muçarela', 0.2, 3.00),
  ('Gorgonzola', 0.1, 4.50),
  ('Parmesão', 0.1, 4.00),
  ('Presunto', 0.15, 3.00),
  ('Ovos', 0.1, 2.50),
  ('Azeitonas Verdes', 0.1, 2.50),
  ('Ervilhas', 0.1, 2.00);

-- Frango com Catupiry
INSERT INTO tb_tipos_pizzas_ingredientes (id_tipo_pizza, id_ingrediente, quantidade)
VALUES
  (4, 1, 1.0),
  (4, 2, 1.0);

-- Calabresa
INSERT INTO tb_tipos_pizzas_ingredientes (id_tipo_pizza, id_ingrediente, quantidade)
VALUES
  (5, 3, 1.0),
  (5, 4, 1.0);

-- Quatro Queijos
INSERT INTO tb_tipos_pizzas_ingredientes (id_tipo_pizza, id_ingrediente, quantidade)
VALUES
  (6, 2, 1.0),
  (6, 5, 0.5),
  (6, 6, 0.5);

-- Portuguesa
INSERT INTO tb_tipos_pizzas_ingredientes (id_tipo_pizza, id_ingrediente, quantidade)
VALUES
  (7, 2, 1.0),
  (7, 7, 0.5),
  (7, 8, 0.5),
  (7, 9, 0.5),
  (7, 10, 0.5);

-- Margherita
INSERT INTO tb_tipos_pizzas_ingredientes (id_tipo_pizza, id_ingrediente, quantidade)
VALUES
  (1, 1, 1.0),
  (1, 2, 1.0),
  (1, 8, 0.5);

-- Pepperoni
INSERT INTO tb_tipos_pizzas_ingredientes (id_tipo_pizza, id_ingrediente, quantidade)
VALUES
  (2, 1, 1.0),
  (2, 2, 1.0),
  (2, 3, 0.5);

-- Vegetariana
INSERT INTO tb_tipos_pizzas_ingredientes (id_tipo_pizza, id_ingrediente, quantidade)
VALUES
  (3, 1, 1.0),
  (3, 2, 1.0),
  (3, 4, 0.5),
  (3, 5, 0.5),
  (3, 6, 0.5),
  (3, 7, 0.5),
  (3, 8, 0.5);

-- Inserir 20 clientes fictícios
INSERT INTO tb_clientes (cpf, nome, endereco)
VALUES
  ('11111111111', 'Maria Silva', 'Rua A, 123'),
  ('22222222222', 'João Oliveira', 'Avenida B, 456'),
  ('33333333333', 'Ana Santos', 'Rua C, 789'),
  ('44444444444', 'Pedro Souza', 'Avenida D, 1011'),
  ('55555555555', 'Julia Pereira', 'Rua E, 1314'),
  ('66666666666', 'Lucas Rodrigues', 'Avenida F, 1517'),
  ('77777777777', 'Isabela Martins', 'Rua G, 1819'),
  ('88888888888', 'Matheus Ferreira', 'Avenida H, 2122'),
  ('99999999999', 'Gabriela Almeida', 'Rua I, 2425'),
  ('10101010101', 'Rafael Carvalho', 'Avenida J, 2728'),
  ('11111111112', 'Larissa Ribeiro', 'Rua K, 3031'),
  ('12121212121', 'Carlos Lopes', 'Avenida L, 3334'),
  ('13131313131', 'Mariana Gomes', 'Rua M, 3637'),
  ('14141414141', 'Eduardo Nascimento', 'Avenida N, 3940'),
  ('15151515151', 'Camila Barbosa', 'Rua O, 4243'),
  ('16161616161', 'Gustavo Duarte', 'Avenida P, 4546'),
  ('17171717171', 'Sofia Rocha', 'Rua Q, 4849'),
  ('18181818181', 'Felipe Lima', 'Avenida R, 5152'),
  ('19191919191', 'Lara Fernandes', 'Rua S, 5455'),
  ('20202020202', 'Henrique Vieira', 'Avenida T, 5758');

-- Inserir 8 funcionários fictícios
INSERT INTO tb_funcionarios (nome, cpf, senha, id_admin)
VALUES
  ('Maria Silva', '11111111112', 'senha1', true),
  ('João Oliveira', '22222222223', 'senha2', false),
  ('Ana Santos', '33333333334', 'senha3', false),
  ('Pedro Souza', '44444444445', 'senha4', false),
  ('Julia Pereira', '55555555556', 'senha5', false),
  ('Lucas Rodrigues', '66666666667', 'senha6', false),
  ('Isabela Martins', '77777777778', 'senha7', false),
  ('Matheus Ferreira', '88888888889', 'senha8', false);

-- Inserir 40 pedidos fictícios
-- Suponha que cada cliente faça 2 pedidos, e os funcionários atendam 5 pedidos cada.
-- Vamos gerar pedidos aleatórios, vinculando aleatoriamente clientes e funcionários.
-- Os valores dos pedidos são fictícios.

-- Criação de pedidos fictícios
WITH pedidos_ficticios AS (
  SELECT
    generate_series(1, 40) AS id,
    floor(random() * 20) + 1 AS id_cliente,
    floor(random() * 8) + 1 AS id_funcionario,
    random() * 50 + 10 AS valor
  FROM generate_series(1, 40)
)

-- Inserção de pedidos fictícios na tabela tb_pedidos
INSERT INTO tb_pedidos (id_cliente, id_funcionario, valor)
SELECT id_cliente, id_funcionario, valor
FROM pedidos_ficticios;

-- Inserir 10 pedidos fictícios
-- Certifique-se de que o id_cliente e id_funcionario existam na tabela tb_pedidos.
INSERT INTO tb_pedidos (id_cliente, id_funcionario, valor, status)
VALUES
  (1, 1, 0, true),
  (2, 2, 0, true),
  (3, 3, 0, true),
  (4, 4, 0, true),
  (5, 5, 0, true),
  (6, 6, 0, true),
  (7, 7, 0, true),
  (8, 8, 0, true),
  (9, 1, 0, true),
  (10, 2, 0, true);

-- Inserir 50 pizzas fictícias para os pedidos
-- Certifique-se de que os IDs de pizzas correspondam corretamente.
-- Aqui, as pizzas são escolhidas manualmente.
-- Cada pedido terá 5 pizzas.
-- Certifique-se de ajustar os valores conforme necessário.

-- Pedido 1
INSERT INTO tb_pizzas (id_tipo_pizza, id_pedido, valor, tamanho)
VALUES
  (1, 1, 25.00, 'm'),
  (2, 1, 28.00, 'p'),
  (3, 1, 32.00, 'g'),
  (4, 1, 30.00, 'm'),
  (5, 1, 31.00, 'g');

-- Pedido 2
INSERT INTO tb_pizzas (id_tipo_pizza, id_pedido, valor, tamanho)
VALUES
  (2, 2, 28.00, 'p'),
  (3, 2, 30.00, 'm'),
  (4, 2, 31.00, 'g'),
  (5, 2, 25.00, 'm'),
  (6, 2, 28.00, 'g');

-- Pedido 3
INSERT INTO tb_pizzas (id_tipo_pizza, id_pedido, valor, tamanho)
VALUES
  (1, 3, 25.00, 'm'),
  (2, 3, 28.00, 'p'),
  (2, 3, 32.00, 'g'),
  (4, 3, 30.00, 'm'),
  (5, 3, 31.00, 'g');

-- Pedido 4
INSERT INTO tb_pizzas (id_tipo_pizza, id_pedido, valor, tamanho)
VALUES
  (1, 4, 25.00, 'm'),
  (2, 4, 28.00, 'p'),
  (3, 4, 32.00, 'g'),
  (4, 4, 30.00, 'm'),
  (5, 4, 31.00, 'g');

-- Pedido 5
INSERT INTO tb_pizzas (id_tipo_pizza, id_pedido, valor, tamanho)
VALUES
  (2, 5, 28.00, 'p'),
  (3, 5, 30.00, 'm'),
  (4, 5, 31.00, 'g'),
  (5, 5, 25.00, 'm'),
  (6, 5, 28.00, 'g');

-- Pedido 6
INSERT INTO tb_pizzas (id_tipo_pizza, id_pedido, valor, tamanho)
VALUES
  (3, 6, 30.00, 'm'),
  (4, 6, 31.00, 'g'),
  (5, 6, 25.00, 'm'),
  (6, 6, 28.00, 'g'),
  (7, 6, 32.00, 'm');

-- Pedido 7
INSERT INTO tb_pizzas (id_tipo_pizza, id_pedido, valor, tamanho)
VALUES
  (4, 7, 25.00, 'm'),
  (5, 7, 28.00, 'p'),
  (6, 7, 32.00, 'g'),
  (1, 7, 30.00, 'm'),
  (2, 7, 31.00, 'g');

-- Pedido 8
INSERT INTO tb_pizzas (id_tipo_pizza, id_pedido, valor, tamanho)
VALUES
  (5, 8, 28.00, 'p'),
  (6, 8, 30.00, 'm'),
  (7, 8, 32.00, 'g'),
  (1, 8, 25.00, 'm'),
  (2, 8, 28.00, 'g');

-- Pedido 9
INSERT INTO tb_pizzas (id_tipo_pizza, id_pedido, valor, tamanho)
VALUES
  (6, 9, 30.00, 'm'),
  (7, 9, 31.00, 'g'),
  (1, 9, 25.00, 'm'),
  (2, 9, 28.00, 'g'),
  (3, 9, 32.00, 'm');

-- Pedido 10
INSERT INTO tb_pizzas (id_tipo_pizza, id_pedido, valor, tamanho)
VALUES
  (3, 10, 30.00, 'm'),
  (4, 10, 31.00, 'g'),
  (5, 10, 25.00, 'm'),
  (6, 10, 28.00, 'g'),
  (7, 10, 32.00, 'm');
