package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalhesFiltro {

    private final String queryBase = "SELECT * FROM vw_detalhes_pedidos2";
    private final String cliente;
    private final String sabor;
    private final String dataInicio;
    private final String dataFinal;

    private final Boolean status;

    private DetalhesFiltro(String cliente, String sabor, String dataInicio, String dataFinal, Boolean status) {
        this.cliente = cliente;
        this.sabor = sabor;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.status = status;
    }

    public static DetalhesFiltro com(String cliente, String sabor, String dataInicio, String dataFinal, Boolean status) {
        return new DetalhesFiltro(cliente, sabor, dataInicio, dataFinal, status);
    }

    public PreparedStatement gerarQuery(Connection connection) throws SQLException {
        String result = queryBase + getFiltros();
        var stmt = connection.prepareStatement(result);

        int index = 1;
        if(sabor != null) {
            stmt.setString(index++, sabor);
        }

        if(cliente != null) {
            stmt.setString(index++, cliente + "%"  );
        }

        if(dataInicio != null) {
            stmt.setString(index++, dataInicio);
        }

        if(dataFinal != null) {
            stmt.setString(index++, dataFinal);
        }

        if(status != null) {
            stmt.setBoolean(index, status);
        }
        return stmt;
    }

    private String getFiltros() {
        List<String> filtros = new ArrayList<>();
        if(sabor != null) {
            filtros.add("sabor = ?");
        }

        if(cliente != null) {
            filtros.add("nome_cliente ILIKE ?");
        }

        if(dataInicio != null) {
            filtros.add("data >= ?");
        }

        if(dataFinal != null) {
            filtros.add("data <= ?");
        }

        if(status != null) {
            filtros.add("status = ?");
        }

        if(filtros.isEmpty()) return "";
        return " WHERE " + String.join(" AND ", filtros);
    }
}
