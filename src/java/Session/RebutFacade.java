/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Session;

import Entities.Rebut;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BAYA
 */
@Stateless
public class RebutFacade extends AbstractFacade<Rebut> {
    @PersistenceContext(unitName = "autorisation_bienPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RebutFacade() {
        super(Rebut.class);
    }
    
}
