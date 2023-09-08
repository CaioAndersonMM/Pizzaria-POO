package Model.BO;

import Dao.BaseDao;
import Dao.UsuarioDao;
import Model.Entity.Usuario;

public class UsuarioBo {
    public void criar(Usuario usu) {
        // BaseDao usuDao = new UsuarioDao();
        UsuarioDao usuDao = new UsuarioDao();
        // usu.setEndereco(end);
        usuDao.inserir(usu);
    }
}
