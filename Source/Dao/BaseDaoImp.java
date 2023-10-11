package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDaoImp<E> implements BaseDao<E> {
    final static String URL = "jdbc:postgresql://localhost:5432/pizzaria";
    final static String USER = "thiago";
    final static String PASS = "minhasenha";
    static Connection connection = null;

    // Abrir Conexão
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        } else
            return connection;

    }

    // Fechar Conexão
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }

    }

    public abstract Long inserir(E entity);

    public abstract void deletar(E entity);

    public abstract void alterar(E entity);

    public abstract List<E> buscar(E entity);

    public abstract List<E> listar();

}
