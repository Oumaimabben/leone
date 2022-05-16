
package Bean;


import Entities.TypeSortie;
import Session.TypeSortieFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author BAYA
 */
@ManagedBean
@RequestScoped
public class TypeSortieBean {

    @EJB

    private TypeSortieFacade TypeSortieFacade;
    private TypeSortie TypeSortie;
    private List<TypeSortie> Sortielist ;
    private TypeSortie selectedType;
    private String msg;

    @PostConstruct
    public void init() {
        TypeSortie = new TypeSortie();
        Sortielist= TypeSortieFacade.findAll();
        selectedType= new TypeSortie();
    }

    public List<TypeSortie> getSortielist() {
        return Sortielist;
    }

    public void setSortielist(List<TypeSortie> Sortielist) {
        this.Sortielist = Sortielist;
    }

    public TypeSortie getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(TypeSortie selectedType) {
        this.selectedType = selectedType;
    }
    
    public TypeSortie getTypeSortie() {
        return TypeSortie;
    }

    public void setTypeSortie(TypeSortie TypeSortie) {
        this.TypeSortie = TypeSortie;
    }

    public TypeSortieBean() {
    }

    public void create() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            TypeSortieFacade.create(TypeSortie);

            msg = "Type ajouté avec succés ";
            this.TypeSortie = new TypeSortie();
        } catch (Exception e) {
            msg = "erreuuuuuuuuur ";
        }
        context.addMessage(null, new FacesMessage("Info:", msg));

    }
    
public void edit() {
        TypeSortieFacade.edit(selectedType);
    }

public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Type de sortie modifié", ((TypeSortie) event.getObject()).getTypeSortie());  
        selectedType = (TypeSortie) event.getObject();
        TypeSortieFacade.edit(selectedType);
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            TypeSortieFacade.remove((TypeSortie) event.getObject());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Supression avec succes"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info:", "Erreur lors de la supression : fonction utilisée!"));
        }  
    }
}
