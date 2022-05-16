
package Bean;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import Entities.TypeStage;
import Session.TypeStageFacade;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author BAYA
 */
@ManagedBean
@RequestScoped
public class TypeStageBean {
    
    @EJB
    private TypeStageFacade typeStageFacade;
    private TypeStage typeStage;
    private List<TypeStage> Stagelist;
    private TypeStage selectedTypeStage;
     private String msg;
   
    @PostConstruct
    public void init() {
       typeStage = new TypeStage();
       selectedTypeStage =new TypeStage();
       Stagelist = typeStageFacade.findAll();
    }

    public List<TypeStage> getStagelist() {
        return Stagelist;
    }

    public void setStagelist(List<TypeStage> Stagelist) {
        this.Stagelist = Stagelist;
    }

    public TypeStage getSelectedTypeStage() {
        return selectedTypeStage;
    }

    public void setSelectedTypeStage(TypeStage selectedTypeStage) {
        this.selectedTypeStage = selectedTypeStage;
    }

    public TypeStageFacade getTypeStageFacade() {
        return typeStageFacade;
    }

    public void setTypeStageFacade(TypeStageFacade typeStageFacade) {
        this.typeStageFacade = typeStageFacade;
    }

    public TypeStage getTypeStage() {
        return typeStage;
    }

    public void setTypeStage(TypeStage typeStage) {
        this.typeStage = typeStage;
    }
    
    /**
     * Creates a new instance of TypeStageBean
     */
    public TypeStageBean() {
    }
    public List<TypeStage> findAll(){
        return typeStageFacade.findAll();
    }
    public void create() {
         FacesContext context = FacesContext.getCurrentInstance();
         
         try {
       typeStageFacade.create(typeStage);
    
         msg = "Type ajouté avec succés ";
                  this.typeStage= new TypeStage();
                } catch (Exception e) {
                   msg = "erreur d'ajout ";
    }
     context.addMessage(null, new FacesMessage("Info:", msg));
    
}
    public void edit() {
        typeStageFacade.edit(selectedTypeStage);
    }
    
    public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Type de stage modifié", ((TypeStage) event.getObject()).getTypeStage());  
        selectedTypeStage = (TypeStage) event.getObject();
        typeStageFacade.edit(selectedTypeStage);
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {  
         FacesContext context = FacesContext.getCurrentInstance();
        try {
            typeStageFacade.remove((TypeStage) event.getObject());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Supression avec succes"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info:", "Erreur lors de la supression : fonction utilisée!"));
        }  
    }
}
