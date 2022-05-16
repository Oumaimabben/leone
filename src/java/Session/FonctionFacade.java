

package Session;

import Entities.Fonction;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BAYA
 */
@Stateless
public class FonctionFacade extends AbstractFacade<Fonction> {
    @PersistenceContext(unitName = "autorisation_bienPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FonctionFacade() {
        super(Fonction.class);
    }
    
}
