package projetoencomendadeplacas.Utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAUtils {
    private static EntityManagerFactory EMF = null;
    
    public static EntityManagerFactory getEntityManagerFactory(){
        if (EMF == null){
            EMF = Persistence.createEntityManagerFactory("ProjetoEncomendaDePlacasPU");
        }
        return EMF;
    }
}