 ALTER TABLE tb_pedidos
DROP CONSTRAINT tb_pedidos_id_cliente_fkey, -- Exclua a chave estrangeira existente.
ADD CONSTRAINT tb_pedidos_id_cliente_fkey
  FOREIGN KEY (id_cliente)
  REFERENCES tb_clientes(id)
  ON DELETE SET NULL; -- Defina a opção "ON DELETE SET NULL".


  ALTER TABLE tb_clientes
ADD CONSTRAINT unique_cpf UNIQUE (cpf);