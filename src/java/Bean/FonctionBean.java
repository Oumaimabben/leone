

package Bean;


import Entities.Fonction;
import Session.FonctionFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;


@ManagedBean
@RequestScoped
public class FonctionBean {
    @EJB
    private FonctionFacade fonctionFacade;
    private Fonction fonction;
    private List<Fonction> fonctionList;
    private Fonction selectedfonction;
    private String msg;

    @PostConstruct
    public void init() {
        fonctionList = fonctionFacade.findAll();
        fonction = new Fonction();
        selectedfonction = new Fonction();
    }

    public List<Fonction> getFonctionList() {
        return fonctionList;
    }

    public void setFonctionList(List<Fonction> fonctionList) {
        this.fonctionList = fonctionList;
    }

    public Fonction getSelectedfonction() {
        return selectedfonction;
    }

    public void setSelectedfonction(Fonction selectedfonction) {
        this.selectedfonction = selectedfonction;
    }
    
    public FonctionFacade getFonctionFacade() {
        return fonctionFacade;
    }

    public void setFonctionFacade(FonctionFacade fonctionFacade) {
        this.fonctionFacade = fonctionFacade;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }
    /**
     * Creates a new instance of FonctionBean
     */
    public FonctionBean() {
    }

    public void edit() {
        fonctionFacade.edit(selectedfonction);
    }

    public Fonction find(Object id) {
        return fonctionFacade.find(id);
    }

    public List<Fonction> findAll() {
        return fonctionFacade.findAll();
    }

    public int count() {
        return fonctionFacade.count();
    }

    public void remove() {
       
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            fonctionFacade.remove(fonction);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Supression avec succes"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERREUR:", "Erreur lors de la supression"));
        }
    }

    public void create() {
         FacesContext context = FacesContext.getCurrentInstance();
         
         try {
        fonctionFacade.create(fonction);
    
          msg = "fonction ajoutée avec succés ";
                   fonction=new Fonction();
                } catch (Exception e) {
                    msg = e.getMessage();
    }
     context.addMessage(null, new FacesMessage("Info:", msg));
    }
    
      public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("fonction modifié", ((Fonction) event.getObject()).getDesigFct());  
        selectedfonction = (Fonction) event.getObject();
        fonctionFacade.edit(selectedfonction);
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("annulation", ((Fonction) event.getObject()).getDesigFct());  
  
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            fonctionFacade.remove((Fonction) event.getObject());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Supression avec succes"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERREUR:", "Erreur lors de la supression : fonction utilisée!"));
        }
        
    } 
}
    

