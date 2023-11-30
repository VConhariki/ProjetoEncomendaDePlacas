package projetoencomendadeplacas.DAOs;

import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface IDAO<J, E> {
    public EntityManagerFactory getEntityManagerFactory();
    public void inserir(E objeto) throws Exception;
    public void editar(E objeto) throws Exception;
    public void deletar(E objeto) throws Exception;
    public E get(E objeto);
    public List<E> getAll();
}
