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
    Cliente cli = new Cliente("029312321492", "rua 15", "caio");

    cli.setId(cliente.inserir(cli));
   
    //cliente.deletar(cli);
   // System.out.println(cli.getId());
    //System.out.println(cliente.buscar(cli));


    //Alteração
    //Cliente cli = new Cliente("029312321432", "rua 15", "caio");
    //cli.setId(4l);
   // cliente.alterar(cli);

   //cliente.listar();
    }
}
