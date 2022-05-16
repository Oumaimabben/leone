
package Bean;

import Entities.Service;
import Session.ServiceFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@RequestScoped
public class ServiceBean {
    @EJB
    private ServiceFacade serviceFacade;
    private Service service;
    private List<Service> serviceList;
    private Service selectedservice;
    
    private String msg;
    
 @PostConstruct
    public void init() {
        service = new Service();
        serviceList = serviceFacade.findAll();
        selectedservice = new Service();
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

  
    
    public ServiceFacade getServiceFacade() {
        return serviceFacade;
    }

    public void setServiceFacade(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void create() {
        FacesContext context = FacesContext.getCurrentInstance();
      
        
        try {
                    serviceFacade.create(service);
                    msg = "Service ajoutée avec succés ";
                    this.service = new Service();
                } catch (Exception e) {
                    msg = e.getMessage();
                    msg = "Erreur!" + msg;
    }
     context.addMessage(null, new FacesMessage("Info:", msg));
    }

    public void edit(Service entity) {
        serviceFacade.edit(entity);
    }

    

    public Service find(Object id) {
        return serviceFacade.find(id);
    }

    public List<Service> findAll() {
        return serviceFacade.findAll();
    }

    public int count() {
        return serviceFacade.count();
    }

    public void remove() {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            serviceFacade.remove(selectedservice);
            context.addMessage(null, new FacesMessage("Info:", "Service supprimé!"));
            
        } catch(Exception e){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur:", "ereur de suppresion !"));
        }
        
    }

    
    /**
     * Creates a new instance of ServiceBean
     */
    public ServiceBean() {
    }

    public Service getSelectedservice() {
        return selectedservice;
    }

    public void setSelectedservice(Service selectedservice) {
        this.selectedservice = selectedservice;
    }
    
    public void onServiceEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        selectedservice.setDesigService((String)newValue);
        serviceFacade.edit(selectedservice);
        
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
     public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Service modifié", ((Service) event.getObject()).getDesigService());  
        selectedservice = (Service) event.getObject();
        serviceFacade.edit(selectedservice);
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {  
       
  
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            serviceFacade.remove((Service) event.getObject());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Supression avec succes"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info:", "Erreur lors de la supression : fonction utilisée!"));
        }  
    }
    
    
}
