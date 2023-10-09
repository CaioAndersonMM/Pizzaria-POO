package Model.BO;

import java.util.List;

import Dao.ClienteDao;
import Model.Entity.Cliente;

public class ClienteBo {
static private ClienteDao dao = new ClienteDao();

  public List<Cliente> listar(){
    return dao.listar(); // Recupere e retorne a lista de pizzas
  }
}
