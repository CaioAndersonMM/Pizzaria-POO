CREATE TABLE tb_produtos (
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(40) NOT NULL,
	quantidade float NOT NULL CHECK (quantidade >= 0),
	valor NUMERIC(6, 2) NOT NULL CHECK (valor > 0),
	is_adicional BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE tb_tipos_pizzas (
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(40) NOT NULL,
	valor_p NUMERIC(6, 2) NOT NULL CHECK (valor_p > 0),
	valor_m NUMERIC(6, 2) NOT NULL CHECK (valor_m > 0),
	valor_g NUMERIC(6, 2) NOT NULL CHECK (valor_g > 0)
);

CREATE TABLE tb_funcionarios (
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(40) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	senha VARCHAR(40) NOT NULL,
	id_admin BOOLEAN NOT NULL
);

CREATE TABLE tb_clientes (
	id BIGSERIAL PRIMARY KEY,
	cpf VARCHAR(11) NOT NULL,
	nome VARCHAR(40) NOT NULL,
	endereco VARCHAR(80)
);

CREATE TABLE tb_pedidos (
	id BIGSERIAL PRIMARY KEY,
	id_cliente BIGINT,
	id_funcionario BIGINT,
	valor NUMERIC(6, 2) NOT NULL CHECK (valor >= 0),
	status BOOLEAN NOT NULL DEFAULT FALSE,
	data_criacao DATE DEFAULT current_date,
	FOREIGN KEY (id_cliente) REFERENCES tb_clientes(id),
	FOREIGN KEY (id_funcionario) REFERENCES tb_funcionarios(id)
);

CREATE TABLE tb_pizzas (
	id BIGSERIAL PRIMARY KEY,
	id_tipo_pizza BIGINT,
	valor NUMERIC(6, 2) NOT NULL CHECK (valor > 0),
	tamanho CHAR(1) NOT NULL CHECK (tamanho IN ('p', 'm', 'g')), -- VERIFICAR SE O VALOR DE TAMANHO É 'p', 'm' ou 'g'
	FOREIGN KEY (id_tipo_pizza) REFERENCES tb_tipos_pizzas(id)
	-- FOREIGN KEY (id_pedido) REFERENCES tb_pedidos(id)
);

CREATE TABLE tb_vendas (
  id BIGSERIAL PRIMARY KEY,
  id_pedido BIGINT,
  id_produto BIGINT,
  ganhos FLOAT,
  gastos FLOAT,
  FOREIGN KEY (id_pedido) REFERENCES tb_pedidos(id),
  FOREIGN KEY (id_produto) REFERENCES tb_produtos(id)
);

-- TABELA DE RELAÇÃO ENTRE TIPO_PIZZA (tb_tipos_pizzas) E INGREDIENTES (tb_produtos)
CREATE TABLE tb_tipos_pizzas_ingredientes (
	id_tipo_pizza BIGINT,
	id_ingrediente BIGINT,
	quantidade float CHECK (quantidade > 0),
	FOREIGN KEY (id_tipo_pizza) REFERENCES tb_tipos_pizzas(id),
	FOREIGN KEY (id_ingrediente) REFERENCES tb_produtos(id)
);

-- TABELA DE RELAÇÂO ENTRE PIZZA (tb_pizzas) E ADICIONAIS (tb_produtos)
CREATE TABLE tb_pizzas_adicionais (
	id_pizza BIGINT,
	id_ingrediente BIGINT,
	quantidade float CHECK (quantidade > 0),
	FOREIGN KEY (id_pizza) REFERENCES tb_pizzas(id),
	FOREIGN KEY (id_ingrediente) REFERENCES tb_produtos(id)
);

-- TABELA DE RELAÇÂO ENTRE PIZZA (tb_pizzas) E PEDIDO (tb_pedidos)
CREATE TABLE tb_pedidos_pizzas (
    id_pedido BIGINT,
    id_pizza BIGINT UNIQUE,
    FOREIGN KEY (id_pedido) REFERENCES tb_pedidos(id),
    FOREIGN KEY (id_pizza) REFERENCES tb_pizzas(id)
);