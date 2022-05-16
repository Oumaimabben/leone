/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Session;

import Entities.SortieB1societe;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BAYA
 */
@Stateless
@Named
public class SortieB1societeFacade extends AbstractFacade<SortieB1societe> {
    @PersistenceContext(unitName = "autorisation_bienPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SortieB1societeFacade() {
        super(SortieB1societe.class);
    }
    
    public SortieB1societe findById(int x){
        return em.createNamedQuery("SortieB1societe.findByIdsortieb1Societe", SortieB1societe.class)
                .setParameter("idsortieb1Societe", x)
                .getSingleResult();
    }
    
    
    
}
