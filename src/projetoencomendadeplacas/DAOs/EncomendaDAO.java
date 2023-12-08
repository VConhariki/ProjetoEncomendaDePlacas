package projetoencomendadeplacas.DAOs;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import projetoencomendadeplacas.DAOs.JPAControllers.EncomendaJPAController;
import projetoencomendadeplacas.Entities.Encomenda;


public class EncomendaDAO extends AbstractDAO<EncomendaJPAController, Encomenda>{
        public EncomendaDAO() {
        objetoJPA = new EncomendaJPAController(getEntityManagerFactory());
    }
    
    @Override
    public void inserir(Encomenda objeto) throws Exception {
        objetoJPA.create(objeto);
    }

    @Override
    public void editar(Encomenda objeto) throws Exception {
        objetoJPA.edit(objeto);
    }

    @Override
    public void deletar(Encomenda objeto) throws Exception {
        objetoJPA.destroy(objeto.getId());
    }

    @Override
    public Encomenda get(Encomenda objeto) {
        return objetoJPA.findEncomenda(objeto.getId());
    }

    @Override
    public List<Encomenda> getAll() {
        return objetoJPA.findEncomendaEntities();
    }
    
    public List<Encomenda> getUnidade(String unidade){
        EntityManager banco = objetoJPA.getEntityManager();
        try{
            Query consulta = banco.createNamedQuery("Encomenda.findByUnidade",
                    Encomenda.class);
            consulta.setParameter("unidade", unidade);
            return consulta.getResultList();
            
        }finally{
            banco.close();
        }
               
    }
}
