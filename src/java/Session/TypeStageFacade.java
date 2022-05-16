/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Session;

import Entities.TypeStage;
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
public class TypeStageFacade extends AbstractFacade<TypeStage> {
    @PersistenceContext(unitName = "autorisation_bienPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeStageFacade() {
        super(TypeStage.class);
    }
    
    public List<TypeStage> allTypeStage(){
        return em.createNamedQuery("TypeStage.findAll").getResultList();
    }
    
}
