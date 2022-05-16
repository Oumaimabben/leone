
package Bean;
import Entities.Biens;
import Entities.Materielles;
import Entities.Transfert;
import Entities.Utilisateur;
import Session.BiensFacade;
import Session.MateriellesFacade;
import Session.TransfertFacade;
import Session.UtilisateurFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean
@SessionScoped
public class TransfertBean {

    @EJB
    private MateriellesFacade materiellesFacade;
    @EJB
    private BiensFacade biensFacade;
    private Biens bien;

    @EJB
    private UtilisateurFacade utilisateurFacade;
    private Utilisateur utilisateur;
    @EJB
    private TransfertFacade transfertFacade;
    private Transfert transfertchoisit;

    private Transfert transfert;
    private int nextId;
    private String msg;
    private Date currentDate = new Date();
    private String userName;
    private int nbtransfertaValider;

    private int numMaterielle;
    private int quantite;
    private Materielles materielle;
    private List<Materielles> listeMat;
    private boolean renderListMaterielle;
    //@Inject @HttpParam
    private String idTrans;

    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }

    /**
     * Creates a new instance of TransfertBean
     */
    @PostConstruct
    public void init() {
        this.utilisateur = new Utilisateur();
        this.transfert = new Transfert();
        this.transfertchoisit = new Transfert();
        this.nbtransfertaValider = 0;

        listeMat = new ArrayList<>();
        materielle = new Materielles();
        numMaterielle = 1;
        bien = new Biens();
    }

    

    public void setRenderListMaterielle(boolean renderListMaterielle) {
        this.renderListMaterielle = renderListMaterielle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getCurrentDate() {
        return currentDate;
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

   

        
    private List<String> typeList;

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public TransfertBean() {
    }

   

    public void setNbtransfertaValider(int nbtransfertaValider) {
        this.nbtransfertaValider = nbtransfertaValider;
    }

    public TransfertFacade getTransfertFacade() {
        return transfertFacade;
    }

    public void setTransfertFacade(TransfertFacade transfertFacade) {
        this.transfertFacade = transfertFacade;
    }

    public Transfert getTransfertchoisit() {
        return transfertchoisit;
    }

    public void setTransfertchoisit(Transfert transfertchoisit) {
        this.transfertchoisit = transfertchoisit;
    }

    public Transfert getTransfert() {
        return transfert;
    }

    public void setTransfert(Transfert transfert) {
        this.transfert = transfert;
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

    public MateriellesFacade getMateriellesFacade() {
        return materiellesFacade;
    }

    public void setMateriellesFacade(MateriellesFacade materiellesFacade) {
        this.materiellesFacade = materiellesFacade;
    }

    public Materielles getMaterielle() {
        return materielle;
    }

    public void setMaterielle(Materielles materielle) {
        this.materielle = materielle;
    }

   

    public void edit() {
        transfertFacade.edit(transfert);
    }

    

    public Transfert find() {
        return transfertFacade.find(transfert.getIdtransfert());
    }

    public List<Transfert> findAll() {
        return transfertFacade.findAll();
    }

    public int count() {
        return transfertFacade.count();
    }

  
    
     

  
   

   

  

  

 

    
  
  
   
    
}
