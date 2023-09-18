package Model.Entity;

import Dao.ClienteDao;

public class Felicidade {
    public static void main(String[] args) {
        /*
        try {
            Connection conexcao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pizzaria",
                    "postgres", "caiosql");
            if (conexcao != null) {
                System.out.println("Banco de dados conectado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */

    ClienteDao cliente = new ClienteDao();
    Cliente cli = new Cliente("029312", "rua 14", "caio");

    cliente.inserir(cli);
    }
}
