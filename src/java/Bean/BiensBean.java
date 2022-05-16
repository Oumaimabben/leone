
package Bean;


import Entities.Biens;
import Entities.Materielles;
import Session.BiensFacade;
import Session.MateriellesFacade;
import java.util.ArrayList
       ;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author BAYA
 */
@ManagedBean
@SessionScoped
public class BiensBean {
    @EJB
    private MateriellesFacade materiellesFacade;
    @EJB
    private BiensFacade biensFacade;
    private Biens bien;  
    private Biens selectedbien;
    private Materielles materielle;
    private int quantite;
    private int numMaterielle;
    private List<Materielles> listeMat;
    private List<String> listeBiens;
   
    
    @PostConstruct
    public void Init(){
        bien = new Biens();
        selectedbien = new Biens();
        numMaterielle = 1;
        listeMat = new ArrayList<>();
        materielle = new Materielles();
        
    }


    public List<Materielles> getListeMat() {
        return listeMat;
    }

    public void setListeMat(List<Materielles> listeMat) {
        this.listeMat = listeMat;
    }

    public int getNumMaterielle() {
        return numMaterielle;
    }

    public void setNumMaterielle(int numMaterielle) {
        this.numMaterielle = numMaterielle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Materielles getMaterielle() {
        return materielle;
    }

    public void setMaterielle(Materielles materielle) {
        this.materielle = materielle;
    }
    

    public Biens getSelectedbien() {
        return selectedbien;
    }

    public void setSelectedbien(Biens selectedbien) {
        this.selectedbien = selectedbien;
    }

    public BiensFacade getBiensFacade() {
        return biensFacade;
    }

    public void setBiensFacade(BiensFacade biensFacade) {
        this.biensFacade = biensFacade;
    }

    public Biens getBien() {
        return bien;
    }

    public void setBien(Biens bien) {
        this.bien = bien;
    }
    private String msg;
    /**
     * Creates a new instance of BiensBean
     */
    public BiensBean() {
    }

    public void create() {
         FacesContext context = FacesContext.getCurrentInstance();
         
         try {
        biensFacade.create(bien);
    
         msg = "bien ajoutée avec succés ";
                  this.bien= new Biens();
                } catch (Exception e) {
                   msg = "erreur ";
    }
     context.addMessage(null, new FacesMessage("Info:", msg));
    
}

    public void edit() {
        biensFacade.edit(bien);
    }

    public void remove() {
        biensFacade.remove(selectedbien);
    }

    public Biens find() {
        return biensFacade.find(bien.getNatureBien());
    }

    public List<Biens> findAll() {
        return biensFacade.findAll();
    }
    public void AjoutMat(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
          if (numMaterielle <= quantite ) {
            materielle.setBiensIdBiens(bien);
            listeMat.add(materielle);
            materielle = new Materielles();
            numMaterielle++;
            context.addMessage(null, new FacesMessage("Info:", "materiel ajouté avec succés"));
        } else   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Erreur","Quantité atteint!"));
          
        } catch (Exception e) {
              context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "ERREUR"));
        }
        
        
        
    }
    
    public void validMaterielle(){
        FacesContext context = FacesContext.getCurrentInstance();
        numMaterielle = 0;
       try {
            
            bien.setMateriellesList(listeMat);
            biensFacade.create(bien);
            context.addMessage(null, new FacesMessage("Info:", "Bien ajouté avec succés"));
       } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"ERREUR:", "erreur : "+e.getMessage()));
        }
        
    }
}
