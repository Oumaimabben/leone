
package Bean;


import Entities.Biens;
import Entities.Materielles;
import Entities.Rebut;
import Entities.SortieB1societe;
import Entities.Utilisateur;
import Session.BiensFacade;
import Session.MateriellesFacade;
import Session.RebutFacade;
import Session.UtilisateurFacade;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class RebutBean {

    @EJB
    private MateriellesFacade materiellesFacade;

    @EJB
    private BiensFacade biensFacade;
    private Biens bien;
    @EJB
    private UtilisateurFacade utilisateurFacade;
    private Utilisateur utilisateur;
    @EJB
    private RebutFacade rebutFacade;
    private Rebut rebut;
    private Rebut rebutchoisit;
    private Date currentDate = new Date();
    private int numMaterielle;

    public Date getDateAcquistion() {
        return dateAcquistion;
    }

    public void setDateAcquistion(Date dateAcquistion) {
        this.dateAcquistion = dateAcquistion;
    }
private Date dateAcquistion;

    public Biens getBiensIdBiens() {
        return biensIdBiens;
    }

    public void setBiensIdBiens(Biens biensIdBiens) {
        this.biensIdBiens = biensIdBiens;
    }
 private Biens biensIdBiens;
    public Float getPerte() {
        return perte;
    }

    public void setPerte(Float perte) {
        this.perte = perte;
    }
 private Float perte;
    public Float getMontantAcqusition() {
        return montantAcqusition;
    }

    public void setMontantAcqusition(Float montantAcqusition) {
        this.montantAcqusition = montantAcqusition;
    }
     private Float montantAcqusition;

    public Float getValeurResiduelle() {
        return valeurResiduelle;
    }

    public void setValeurResiduelle(Float valeurResiduelle) {
        this.valeurResiduelle = valeurResiduelle;
    }
      private Float valeurResiduelle;
    private int quantite;
    private Materielles materielle;
    private Rebut Rebut;
    private List<Materielles> listeMat;
    private boolean renderListMaterielle;
    private int nextId;
    private int id;
    
        public int getNextId() {
        List<Rebut> l = rebutFacade.findAll();
        int maxId = 0;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getIdrebut()> maxId) {
                maxId = l.get(i).getIdrebut();
            }
        }

        nextId = maxId + 1;
        return nextId;
    }
    
    
    

    public String getCentreCout() {
        return centreCout;
    }

    public void setCentreCout(String centreCout) {
        this.centreCout = centreCout;
    }
     private String centreCout;

    public String getNatureInvestissement() {
        return natureInvestissement;
    }

    public void setNatureInvestissement(String natureInvestissement) {
        this.natureInvestissement = natureInvestissement;
    }
private String natureInvestissement;
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
   private String designation;
    public String getCauseRebut() {
        return causeRebut;
    }

    public void setCauseRebut(String causeRebut) {
        this.causeRebut = causeRebut;
    }
    private String causeRebut;
    public void setRenderListMaterielle(boolean renderListMaterielle) {
        this.renderListMaterielle = renderListMaterielle;
    }

    public List<Materielles> getListeMat() {
        return listeMat;
    }

    public void setListeMat(List<Materielles> listeMat) {
        this.listeMat = listeMat;
    }

    public MateriellesFacade getMateriellesFacade() {
        return materiellesFacade;
    }

    public void setMateriellesFacade(MateriellesFacade materiellesFacade) {
        this.materiellesFacade = materiellesFacade;
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

    public Date getCurrentDate() {
        return currentDate;
    }
     

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    private List<String> typeList;

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public Rebut getRebutchoisit() {
        return rebutchoisit;
    }

    public void setRebutchoisit(Rebut rebutchoisit) {
        this.rebutchoisit = rebutchoisit;
    }
  

    /**
     * Creates a new instance of RebutBean
     */

    @PostConstruct
    public void init() {
       
         this.Rebut = new Rebut();
        
        
    }

   
    public RebutBean() {
    }

   

  

    public RebutFacade getRebutFacade() {
        return rebutFacade;
    }

    public void setRebutFacade(RebutFacade rebutFacade) {
        this.rebutFacade = rebutFacade;
    }

    public Rebut getRebut() {
        return rebut;
    }

    public void setRebut(Rebut rebut) {
        this.rebut = rebut;
    }
private String userName;
 private String msg;

    public Utilisateur getUtilisateuridUtilisateur() {
        return utilisateuridUtilisateur;
    }

    public void setUtilisateuridUtilisateur(Utilisateur utilisateuridUtilisateur) {
        this.utilisateuridUtilisateur = utilisateuridUtilisateur;
    }
 private Utilisateur utilisateuridUtilisateur;
   public void createRebut(){
       
      FacesContext context = FacesContext.getCurrentInstance(); 
       Rebut rebut1=new Rebut();
      try{ 
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", msg));
          userName = context.getExternalContext().getUserPrincipal().toString();
            utilisateur.setIdUtilisateur(Integer.parseInt(userName));
            utilisateur = utilisateurFacade.find(utilisateur.getIdUtilisateur());
           
           rebut1.setUtilisateuridUtilisateur(utilisateur);
           
       rebut1.setCauseRebut(causeRebut);
       rebut1.setDateAcquistion(dateAcquistion);
       rebut1.setDesignation(designation);
       rebut1.setMontantAcqusition(montantAcqusition);
       rebut1.setNatureInvestissement(natureInvestissement);
       rebut1.setCentreCout(centreCout);
       rebutFacade.create(rebut1);
      
      
            msg = " ajoutée avec succés ";   
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", msg));
       }
       catch(Exception e){
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur:", "erreur d'ajout!!"));
       }
       
       
       
       
   }

    public void edit() {
   
    }

    public void remove() {
        
        
    }
       
    public Rebut find() {
        return rebutFacade.find(rebut.getIdrebut());
    }

   

    public List<Rebut> findRange(int[] range) {
        return rebutFacade.findRange(range);
    }

    public int count() {
        return rebutFacade.count();
    }



    

    
    



   

 
  

   

  
  
    
    
    

}
