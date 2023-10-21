package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.Cliente;
import Model.Entity.Pedido;
import Model.Entity.Pizza;

import Dao.PizzaDao;

public class PedidoDao extends BaseDaoImp<Pedido> {
    private PizzaDao pizzaDAO = new PizzaDao();

    private List<Pizza> buscarPizzas() {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        return pizzas;
    }

    @Override
    public Long inserir(Pedido entity) {
        String sql = "INSERT INTO tb_pedidos (id_cliente, valor, data_criacao, status) VALUES (?,?,?,?)";
        connection = getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, entity.getCliente().getId());
            ps.setFloat(2, entity.getValor());
            ps.setDate(3, java.sql.Date.valueOf(entity.getData()));
            ps.setBoolean(4, false);
            ps.execute();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            // ps.close();

            if (generatedKeys.next()) {
                Long pedidoId = generatedKeys.getLong(1);
                entity.setId(pedidoId); // Define o ID gerado no objeto Pedido

                // Associar as pizzas ao pedido na tabela de relação tb_pedidos_pizzas
                for (Pizza pizza: entity.getPizzas()) {
                    Long pizzaId = pizza.getId();
                    
                    if (pizzaId == null) {
                        pizzaId = pizzaDAO.inserir(pizza);
                    }
                    
                    pizzaDAO.associarPizzaAoPedido(pedidoId, pizzaId);
                }

                return pedidoId;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deletar(Pedido entity) {
        String sql = "DELETE FROM tb_pedidos WHERE id = ?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void alterar(Pedido entity) {
        String sql = "UPDATE tb_pedidos SET cliente=?, pizzas=?, valor=?, data=?, status=? WHERE id=?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setObject(1, entity.getCliente());
            stmt.setObject(2, entity.getPizzas());
            stmt.setFloat(3, entity.getValor());
            stmt.setDate(4, java.sql.Date.valueOf(entity.getData()));
            stmt.setBoolean(5, entity.getStatus());
            stmt.setLong(6, entity.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public Pedido buscar(Pedido entity) {
        String sql = "SELECT * FROM tb_pedidos WHERE id = ?";
        connection = getConnection();

        Pedido pedido = new Pedido();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());

            ResultSet rs = stmt.executeQuery();
            stmt.close();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("cliente"));
                Pizza[] pizza = new Pizza[10];
                // pizza.setNome(rs.getString("pizza"));
                pedido = new Pedido();
                pedido.setId(rs.getLong("id"));
                pedido.setCliente(cliente);
                // TODO: Associar as pizzas
                pedido.setValor(rs.getFloat("valor"));
                pedido.setData(rs.getDate("data").toLocalDate());
                pedido.setStatus(rs.getBoolean("status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }

    @Override
    public List<Pedido> listar() {
        String sql = "SELECT * FROM tb_pedidos;";
        connection = getConnection();
        List<Pedido> pedidos = new ArrayList<>();
        ClienteDao clientedao = new ClienteDao();
        PizzaDao pizzadao = new PizzaDao();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            

            while (rs.next()) {
                Pedido pedido = new Pedido();

                // Pizza pizza = new Pizza();
                Cliente cliente = new Cliente();
                // pizza.setId(rs.getLong("pizza"));
                // pizza = pizzadao.buscar(pizza);

                cliente.setId(rs.getLong("id_cliente"));
                
                if (cliente.getId() != null) {
                    cliente = clientedao.buscar(cliente);
                    pedido.setCliente(cliente);
                } else{
                    //Cliente foi apagado
                }

                pedido.setId(rs.getLong("id"));
                pedido.setValor(rs.getFloat("valor"));
                pedido.setStatus(rs.getBoolean("status"));
                pedido.setData(rs.getDate("data_criacao").toLocalDate());
                pedidos.add(pedido);
            }
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {            
            closeConnection();
        }
        return pedidos;
    }

    public void finalizarPedido(Pedido entity) {
        String sql = "UPDATE tb_pedidos SET status=? WHERE id=?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setBoolean(1, true);
            stmt.setLong(2, entity.getId());

            stmt.execute();
            stmt.close();

            System.out.println("AQUI");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }







}
