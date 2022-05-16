/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Session;

import Entities.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BAYA
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {
    @PersistenceContext(unitName = "autorisation_bienPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
    public Utilisateur findUtilParService(String service){
        return em.createNamedQuery("Utilisateur.findByService", Utilisateur.class)
                .setParameter("service", service)
                .getResultList().get(0);
    }
    public Utilisateur findUtilFonction(String fonction){
        return em.createNamedQuery("Utilisateur.findByFonction", Utilisateur.class)
               .setParameter("fonction", fonction)
                .getResultList().get(0);
    }
    
}
