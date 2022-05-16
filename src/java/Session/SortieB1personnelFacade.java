/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Session;

import Entities.SortieB1personnel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BAYA
 */
@Stateless
public class SortieB1personnelFacade extends AbstractFacade<SortieB1personnel> {
    @PersistenceContext(unitName = "autorisation_bienPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SortieB1personnelFacade() {
        super(SortieB1personnel.class);
    }
    
    
    
    
    
}
