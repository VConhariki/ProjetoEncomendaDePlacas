package projetoencomendadeplacas.DAOs;

import javax.persistence.EntityManagerFactory;
import projetoencomendadeplacas.Utils.JPAUtils;

public abstract class AbstractDAO<J, E> implements IDAO<J, E> {

    J objetoJPA;

    public J getObjetoJPA() {
        return objetoJPA;
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return JPAUtils.getEntityManagerFactory();
    }
}
