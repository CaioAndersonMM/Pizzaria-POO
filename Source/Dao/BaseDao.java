package Dao;

import java.util.List;

public interface BaseDao<E> {
    public Long inserir(E entity);

    public void deletar(E entity);

    public void alterar(E entity);

    public List<E> buscar(E entity);

    public List<E> listar();
}
