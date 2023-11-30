package projetoencomendadeplacas.DAOs;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import projetoencomendadeplacas.DAOs.JPAControllers.ClienteJPAController;
import projetoencomendadeplacas.Entities.Cliente;

public class ClienteDAO extends AbstractDAO<ClienteJPAController, Cliente>{
    public ClienteDAO() {
        objetoJPA = new ClienteJPAController(getEntityManagerFactory());
    }
    
    @Override
    public void inserir(Cliente objeto) throws Exception {
        objetoJPA.create(objeto);
    }

    @Override
    public void editar(Cliente objeto) throws Exception {
//        objetoJPA.edit(objeto);
    }

    @Override
    public void deletar(Cliente objeto) throws Exception {
//        objetoJPA.destroy(objeto.getId());
    }

    @Override
    public Cliente get(Cliente objeto) {
//        return objetoJPA.findCliente(objeto.getId());
        return new Cliente();
    }

    @Override
    public List<Cliente> getAll() {
        return objetoJPA.findClienteEntities();
    }
    
    public List<Cliente> getUnidade(String unidade){
        EntityManager banco = objetoJPA.getEntityManager();
        try{
            Query consulta = banco.createNamedQuery("Cliente.findByUnidade",
                    Cliente.class);
            consulta.setParameter("unidade", unidade);
            return consulta.getResultList();
            
        }finally{
            banco.close();
        }
               
    }
}
