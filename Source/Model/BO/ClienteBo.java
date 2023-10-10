package Model.BO;

import java.util.List;

import Dao.ClienteDao;
import Model.Entity.Cliente;
import Model.Entity.TipoPizza;

public class ClienteBo {
static private ClienteDao dao = new ClienteDao();

  public List<Cliente> listar(){
    return dao.listar(); // Recupere e retorne a lista de pizzas
  }

  public void editar(Long id, Cliente clientenovo){
    Cliente cliente = new Cliente();
    cliente.setId(id);
    cliente = dao.buscar(cliente);    
    dao.alterar(clientenovo);
  }

  public void deletar(Long id){
    Cliente cliente = new Cliente();
    cliente.setId(id);
    cliente = dao.buscar(cliente);
    dao.deletar(cliente);
  }

  public void criar(Cliente cliente){
    dao.inserir(cliente);
  }

}
