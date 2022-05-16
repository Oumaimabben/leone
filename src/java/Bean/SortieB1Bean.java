
package Bean;

import Bean.UtilisateurBean;
import Entities.Biens;
import Entities.Materielles;
import Entities.SortieB1societe;
import Entities.Utilisateur;
import Session.BiensFacade;
import Session.MateriellesFacade;
import Session.SortieB1societeFacade;
import Session.UtilisateurFacade;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class SortieB1Bean {

    @EJB
    private MateriellesFacade materiellesFacade;

    @EJB
    private BiensFacade biensFacade;
    private Biens bien;

    @EJB
    private UtilisateurFacade utilisateurFacade;
    @EJB
    private SortieB1societeFacade sortieB1societeFacade;

    private UtilisateurBean ub = new UtilisateurBean();
    private SortieB1societe sortieB1societe;
    private SortieB1societe sortieB1Choisit;
    private SortieB1societe sortieB1retour;

    private String msg;
    private Utilisateur utilisateur = new Utilisateur();
    private Date currentDate = new Date();
    private int idSortieB1;
    private List<String> typeList;
    private Boolean renderRetour = Boolean.TRUE;
    private int nextId;
    private int id;
    private int nbSB1aValider;

    private int numMaterielle;
    private int quantite;
    private Materielles materielle;
    private List<Materielles> listeMat;
    private boolean renderListMaterielle;
    

    public boolean isRenderListMaterielle() {
        if (numMaterielle <= quantite) {
            return true;
        } else {
            return false;
        }
    }

    public void setRenderListMaterielle(boolean renderListMaterielle) {
        this.renderListMaterielle = renderListMaterielle;
    }

    public Biens getBien() {
        return bien;
    }

    public void setBien(Biens bien) {
        this.bien = bien;
    }

    public List<Materielles> getListeMat() {
        return listeMat;
    }

    public void setListeMat(List<Materielles> listeMat) {
        this.listeMat = listeMat;
    }

    public Materielles getMaterielle() {
        return materielle;
    }

    public void setMaterielle(Materielles materielle) {
        this.materielle = materielle;
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

    //donne combien on a des autorisations 
    public int getNextId() {
        List<SortieB1societe> l = sortieB1societeFacade.findAll();
        int maxId = 0;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getIdsortieb1Societe() > maxId) {
                maxId = l.get(i).getIdsortieb1Societe();
            }
        }

        nextId = maxId + 1;
        return nextId;
    }

    public int getNbSB1aValider() {

        if (findSB1NonVAlider() != null) {
            nbSB1aValider = findSB1NonVAlider().size();
            return nbSB1aValider;
        } else {
            return 0;
        }
    }

    public void setNbSB1aValider(int nbSB1aValider) {
        this.nbSB1aValider = nbSB1aValider;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public Boolean getRenderRetour() {
        return renderRetour;
    }

    public void setRenderRetour(Boolean renderRetour) {
        this.renderRetour = renderRetour;
    }

    public SortieB1societe getSortieB1Choisit() {
        return sortieB1Choisit;
    }

    public void setSortieB1Choisit(SortieB1societe sortieB1Choisit) {
        this.sortieB1Choisit = sortieB1Choisit;
    }

    public int getIdSortieB1() {
        return idSortieB1;
    }

    public void setIdSortieB1(int idSortieB1) {
        this.idSortieB1 = idSortieB1;
    }

    @PostConstruct
    public void init() {
        this.sortieB1Choisit = new SortieB1societe();
        this.sortieB1societe = new SortieB1societe();
        this.sortieB1retour = new SortieB1societe();

        this.typeList = new ArrayList<String>();
        this.nbSB1aValider = 0;
        this.bien = new Biens();
        listeMat = new ArrayList<>();
        materielle = new Materielles();
        numMaterielle = 1;
        
    }

    public SortieB1societe getSortieB1retour() {
        return sortieB1retour;
    }

    public void setSortieB1retour(SortieB1societe sortieB1retour) {
        this.sortieB1retour = sortieB1retour;
    }

    public UtilisateurBean getUb() {
        return ub;
    }

    public void setUb(UtilisateurBean ub) {
        this.ub = ub;
    }

    private String userName;

    public Date getCurrentDate() {
        return currentDate;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SortieB1societeFacade getSortieB1societeFacade() {
        return sortieB1societeFacade;
    }

    public void setSortieB1societeFacade(SortieB1societeFacade sortieB1societeFacade) {
        this.sortieB1societeFacade = sortieB1societeFacade;
    }

    public SortieB1societe getSortieB1societe() {

        return sortieB1societe;
    }

    public void setSortieB1societe(SortieB1societe sortieB1societe) {
        this.sortieB1societe = sortieB1societe;
    }

    /**
     * Creates a new instance of SortieB1Bean
     */
    public SortieB1Bean() {
    }

    public void create() {

        FacesContext context = FacesContext.getCurrentInstance();
        //VAlider materielle

        try {

            bien.setMateriellesList(listeMat);
            biensFacade.create(bien);
            context.addMessage(null, new FacesMessage("Info:", "Bien ajouté avec succes"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur:", "ereuur d'ajout bien!!"));
        }
        numMaterielle = 1;
        listeMat = new ArrayList<>();
        //Valider sortie
        try {
            userName = context.getExternalContext().getUserPrincipal().toString();
            utilisateur.setIdUtilisateur(Integer.parseInt(userName));
            utilisateur = utilisateurFacade.find(utilisateur.getIdUtilisateur());
        } catch (Exception e) {

            userName = e.getMessage();

        }

        sortieB1societe.setUtilisateuridUtilisateur(utilisateur);
        if (sortieB1societe.getDateRetour() != null) {
            if (sortieB1societe.getDateRetour().after(sortieB1societe.getDateSortie())) {
                try {
                    sortieB1societe.setQuantite(quantite);
                    sortieB1societe.setBiensIdBiens(bien);
                    sortieB1societeFacade.create(sortieB1societe);
                    msg = "autorisation ajoutée avec succés ";
                    this.sortieB1societe = new SortieB1societe();
                    this.bien = new Biens();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", msg));
                } catch (Exception e) {
                    msg = e.getMessage();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur:", msg));

                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur", " Date de retour < date de sortie"));

            }

        } else {
            try {
                sortieB1societeFacade.create(sortieB1societe);
                msg = "autorisation ajoutée avec succés ";
                this.sortieB1societe = new SortieB1societe();
                this.bien = new Biens();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", msg));

            } catch (Exception e) {
                msg = e.getMessage();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur:", "erreur d'ajout!!"));

            }
        }

    }

    public void edit() {
        sortieB1societeFacade.edit(sortieB1societe);
    }

    public void remove() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            sortieB1societeFacade.remove(sortieB1Choisit);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Suppression avec succes"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur:", "Erreur lors de la suppression"));
        }
        
    }

    public SortieB1societe find() {
        return sortieB1societeFacade.find(sortieB1societe.getIdsortieb1Societe());
    }

 

    public List<SortieB1societe> findAll() {
        return sortieB1societeFacade.findAll();
    }

    // lister les validations  accepté
    public List<SortieB1societe> findValidatedAut() {
        FacesContext context = FacesContext.getCurrentInstance();
        List<SortieB1societe> all = new ArrayList<SortieB1societe>();
        List<SortieB1societe> validated = new ArrayList<SortieB1societe>();

        try {
            all = sortieB1societeFacade.findAll();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Info:", e.getMessage()));
        }

        for (int i = 0; i < all.size(); i++) {

            if (all.get(i).getValidationG() != null) {
                validated.add(all.get(i));
            }

        }
        return validated;
    }


    // lister les validations en cours de validation de chaque utilisateur
    public List<SortieB1societe> findInvalidatedAut() {
        FacesContext context = FacesContext.getCurrentInstance();
        List<SortieB1societe> all = new ArrayList<SortieB1societe>();
        List<SortieB1societe> invalidated = new ArrayList<SortieB1societe>();

        try {
            all = sortieB1societeFacade.findAll();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Info:", e.getMessage()));
        }

        for (int i = 0; i < all.size(); i++) {

            if (all.get(i).getValidationG() == null) {
                switch (ServiceUtilisateurConnecter()) {
                    case 1:
                        if (all.get(i).getVisaServiceCompetent() == null) {
                            invalidated.add(all.get(i));
                        }
                        break;
                    case 2:
                        if (all.get(i).getVaisaBMRT() == null) {
                            invalidated.add(all.get(i));
                        }
                        break;
                    case 3:
                        if (all.get(i).getVisaSMRT() == null) {
                            invalidated.add(all.get(i));
                        }
                        break;
                    case 4:
                        if (all.get(i).getVisacomptabilite() == null) {
                            invalidated.add(all.get(i));
                        }
                        break;
                    case 5:
                        if (all.get(i).getVisaDirFinancier() == null) {
                            invalidated.add(all.get(i));
                        }
                        break;
                    case 6:
                        if (all.get(i).getVisaDirGeneral() == null) {
                            invalidated.add(all.get(i));
                        }
                        break;
                    case 7:
                        if (all.get(i).getVisaSOS() == null) {
                            invalidated.add(all.get(i));
                        }
                        break;

                }

            }

        }
        return invalidated;
    }

    public int ServiceUtilisateurConnecter() {
        FacesContext context = FacesContext.getCurrentInstance();

        userName = context.getExternalContext().getUserPrincipal().toString();

        utilisateur = utilisateurFacade.find(Integer.parseInt(userName));
        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("competent")) {
            return 1;
        }
        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("BMRT")) {
            return 2;
        }
        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SMRT")) {
            return 3;
        }
        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("comptabilite")) {
            return 4;
        }
        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("financier")) {
            return 5;
        }

        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("direction general")) {
            return 6;
        }
        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SOS")) {
            return 7;
        }
        return 0;
    }

    public List<SortieB1societe> findRange(int[] range) {
        return sortieB1societeFacade.findRange(range);
    }

    public int count() {
        return sortieB1societeFacade.count();
    }

    public List<String> retournerValidations() {

        List<String> l = new ArrayList<String>();
        l.add(sortieB1societe.getVaisaBMRT().toString());
        l.add(sortieB1societe.getVisaServiceCompetent().toString());
        l.add(sortieB1societe.getVisaSMRT().toString());
        l.add(sortieB1societe.getVisacomptabilite().toString());
        l.add(sortieB1societe.getVisaDirFinancier().toString());
        l.add(sortieB1societe.getVisaDirGeneral().toString());
        l.add(sortieB1societe.getVisaSOS().toString());

        return l;
    }

    public void validerSortie() {
        FacesContext context = FacesContext.getCurrentInstance();

        userName = context.getExternalContext().getUserPrincipal().toString();

        utilisateur = utilisateurFacade.find(Integer.parseInt(userName));

        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("competent")) {
            sortieB1Choisit.setVisaServiceCompetent(Boolean.TRUE);
            context.addMessage(null, new FacesMessage("Info:", "Autorisation validé"));
            sortieB1societeFacade.edit(sortieB1Choisit);
            return;
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("BMRT")) {
            if (sortieB1Choisit.getVisaServiceCompetent() != null) {
                sortieB1Choisit.setVaisaBMRT(Boolean.TRUE);
                sortieB1societeFacade.edit(sortieB1Choisit);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation  validé"));
                return;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SMRT")) {
            if (sortieB1Choisit.getVaisaBMRT() != null) {
                sortieB1Choisit.setVisaSMRT(Boolean.TRUE);
                sortieB1societeFacade.edit(sortieB1Choisit);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation validé"));
                return;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("comptabilite")) {
            if (sortieB1Choisit.getVisaSMRT() != null) {
                sortieB1Choisit.setVisacomptabilite(Boolean.TRUE);
                sortieB1societeFacade.edit(sortieB1Choisit);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation validé"));
                return;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("financier")) {
            if (sortieB1Choisit.getVisacomptabilite() != null) {
                sortieB1Choisit.setVisaDirFinancier(Boolean.TRUE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation validé"));
                sortieB1societeFacade.edit(sortieB1Choisit);
                return;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("direction general")) {
            if (sortieB1Choisit.getVisaDirFinancier() != null) {
                sortieB1Choisit.setVisaDirGeneral(Boolean.TRUE);
                sortieB1societeFacade.edit(sortieB1Choisit);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation validé"));
                return;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SOS")) {
            if (sortieB1Choisit.getVisaDirGeneral() != null) {
                sortieB1Choisit.setVisaSOS(Boolean.TRUE);
                sortieB1societeFacade.edit(sortieB1Choisit);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation validé"));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
            return;
        }
        Boolean b = null;
        if (sortieB1Choisit.getVaisaBMRT() != null
                && sortieB1Choisit.getVisaDirFinancier() != null
                && sortieB1Choisit.getVisaDirGeneral() != null
                && sortieB1Choisit.getVisaSMRT() != null
                && sortieB1Choisit.getVisaServiceCompetent() != null) {
            b = sortieB1Choisit.getVaisaBMRT()
                    && sortieB1Choisit.getVisaDirFinancier()
                    && sortieB1Choisit.getVisaDirGeneral()
                    && sortieB1Choisit.getVisaSMRT()
                    && sortieB1Choisit.getVisaServiceCompetent();
        }

        if (b != null) {
            if (b.booleanValue() == Boolean.FALSE) {
                sortieB1Choisit.setValidationG(Boolean.FALSE);
            } else {
                sortieB1Choisit.setValidationG(Boolean.TRUE);
            }
            sortieB1societeFacade.edit(sortieB1Choisit);
        }

    }

    public void refuserSortie() {

        FacesContext context = FacesContext.getCurrentInstance();

        userName = context.getExternalContext().getUserPrincipal().toString();
        utilisateur = utilisateurFacade.find(Integer.parseInt(userName));

        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("competent")) {
            sortieB1Choisit.setVisaServiceCompetent(Boolean.FALSE);
            context.addMessage(null, new FacesMessage("Info:", "Autorisation refusé"));
            sortieB1societeFacade.edit(sortieB1Choisit);
            return;
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("BMRT")) {
            if (sortieB1Choisit.getVisaServiceCompetent() != null) {
                sortieB1Choisit.setVaisaBMRT(Boolean.FALSE);
                sortieB1societeFacade.edit(sortieB1Choisit);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation  refusé"));
                return;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SMRT")) {
            if (sortieB1Choisit.getVaisaBMRT() != null) {
                sortieB1Choisit.setVisaSMRT(Boolean.FALSE);
                sortieB1societeFacade.edit(sortieB1Choisit);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation refusé"));
                return;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("comptabilite")) {
            if (sortieB1Choisit.getVisaSMRT() != null) {
                sortieB1Choisit.setVisacomptabilite(Boolean.FALSE);
                sortieB1societeFacade.edit(sortieB1Choisit);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation refusé"));
                return;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("financier")) {
            if (sortieB1Choisit.getVisacomptabilite() != null) {
                sortieB1Choisit.setVisaDirFinancier(Boolean.FALSE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation refusé"));
                sortieB1societeFacade.edit(sortieB1Choisit);
                return;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("direction general")) {
            if (sortieB1Choisit.getVisaDirFinancier() != null) {
                sortieB1Choisit.setVisaDirGeneral(Boolean.FALSE);
                sortieB1societeFacade.edit(sortieB1Choisit);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation refusé"));
                return;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SOS")) {
            if (sortieB1Choisit.getVisaDirGeneral() != null) {
                sortieB1Choisit.setVisaSOS(Boolean.FALSE);
                sortieB1societeFacade.edit(sortieB1Choisit);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation refusé"));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
            return;
        }

        Boolean b = null;
        if (sortieB1Choisit.getVaisaBMRT() != null
                && sortieB1Choisit.getVisaDirFinancier() != null
                && sortieB1Choisit.getVisaDirGeneral() != null
                && sortieB1Choisit.getVisaSMRT() != null
                && sortieB1Choisit.getVisacomptabilite() != null
                && sortieB1Choisit.getVisaSOS() != null
                && sortieB1Choisit.getVisaServiceCompetent() != null) {
            b = sortieB1Choisit.getVaisaBMRT()
                    && sortieB1Choisit.getVisaDirFinancier()
                    && sortieB1Choisit.getVisaDirGeneral()
                    && sortieB1Choisit.getVisaSMRT()
                    && sortieB1Choisit.getVisacomptabilite()
                    && sortieB1Choisit.getVisaSOS()
                    && sortieB1Choisit.getVisaServiceCompetent();

        }

        if (b != null) {
            if (b.booleanValue() == Boolean.FALSE) {
                sortieB1Choisit.setValidationG(Boolean.FALSE);
            } else {
                sortieB1Choisit.setValidationG(Boolean.TRUE);
            }
            sortieB1societeFacade.edit(sortieB1Choisit);
        }

    }

   

    private void writePDFToResponse(ExternalContext externalContext, ByteArrayOutputStream baos, String fileName) {
        try {
            externalContext.responseReset();
            externalContext.setResponseContentType("application/pdf");
            externalContext.setResponseHeader("Expires", "0");
            externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            externalContext.setResponseHeader("Pragma", "public");
            externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
            externalContext.setResponseContentLength(baos.size());
            OutputStream out = externalContext.getResponseOutputStream();
            baos.writeTo(out);
            externalContext.responseFlushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SortieB1societe> findSB1NonVAlider() {
        FacesContext context = FacesContext.getCurrentInstance();

        userName = context.getExternalContext().getUserPrincipal().toString();

        utilisateur = utilisateurFacade.find(Integer.parseInt(userName));
        List<SortieB1societe> all = sortieB1societeFacade.findAll();
        List<SortieB1societe> nonValider = new ArrayList<>();

        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SMRT")) {
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getVisaSMRT() == null) {
                    nonValider.add(all.get(i));
                }
            }
            return nonValider;
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("competent")) {
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getVisaServiceCompetent() == null) {
                    nonValider.add(all.get(i));
                }
            }
            return nonValider;
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("BMRT")) {
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getVaisaBMRT() == null) {
                    nonValider.add(all.get(i));
                }
            }
            return nonValider;
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("comptabilite")) {
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getVisacomptabilite() == null) {
                    nonValider.add(all.get(i));
                }
            }
            return nonValider;
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("financier")) {
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getVisaDirFinancier() == null) {
                    nonValider.add(all.get(i));
                }
            }
            return nonValider;
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("direction general")) {

            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getVisaDirGeneral() == null) {
                    nonValider.add(all.get(i));
                }
            }
            return nonValider;
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SOS")) {

            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getVisaSOS() == null) {
                    nonValider.add(all.get(i));
                }
            }
            return nonValider;
        }

        return null;
    }

    private static class BienBean {

        public BienBean() {
        }
    }

    public void AjoutMat() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (numMaterielle <= quantite) {
                materielle.setBiensIdBiens(bien);
                listeMat.add(materielle);
                materielle = new Materielles();
                numMaterielle++;
                context.addMessage(null, new FacesMessage("Info:", "materiel ajouté"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Info:", "Quantité atteint"));
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "erreur d'ajout"));

        }

    }
    
    
     public void createPDF() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            String in = "../../resources/sortieSb1.pdf";

            PdfReader reader = new PdfReader(in);

            PdfStamper stamper = new PdfStamper(reader, baos);

            AcroFields form = stamper.getAcroFields();

            form.setField("Num", sortieB1Choisit.getIdsortieb1Societe().toString());
            form.setField("demandeur", sortieB1Choisit.getUtilisateuridUtilisateur().getUtilisateurNom() + " " + sortieB1Choisit.getUtilisateuridUtilisateur().getUtilisateurPrenom());
            form.setField("service", sortieB1Choisit.getUtilisateuridUtilisateur().getServiceIdService().getDesigService());
            form.setField("fonction", sortieB1Choisit.getUtilisateuridUtilisateur().getFonctionIdFct().getDesigFct());
            form.setField("TypeS", sortieB1Choisit.getTypeSortieidTypeSortie().getTypeSortie());
            form.setField("natureB", sortieB1Choisit.getBiensIdBiens().getNatureBien());
            form.setField("quantite", sortieB1Choisit.getQuantite().toString());
            String listNumImmo = "";
            String listMarque = "";
            List<Materielles> l = sortieB1Choisit.getBiensIdBiens().getMateriellesList();
            for (int i = 0; i < l.size(); i++) {
                listNumImmo = listNumImmo + l.get(i).getNumImm() + ",";
                listMarque = listMarque + l.get(i).getMarque() + ",";
            }

            form.setField("numimm", listNumImmo);
            form.setField("Marque", listMarque);
            form.setField("numR", sortieB1Choisit.getNumRebut().toString());
            form.setField("destination", sortieB1Choisit.getDestination());
            form.setField("transporteur", sortieB1Choisit.getTransporter());
            SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
            form.setField("dateS", dt1.format(sortieB1Choisit.getDateSortie()));
            form.setField("dater", dt1.format(sortieB1Choisit.getDateRetour()));
            form.setField("Resp", sortieB1Choisit.getResponsableRetour().toString());
            form.setField("observation", sortieB1Choisit.getObservation());
            Boolean visa = sortieB1Choisit.getVisaServiceCompetent();
            if (visa != null) {

                if (visa) {
                    form.setField("competent_nom", utilisateurFacade.findUtilParService("competent").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("competent").getUtilisateurPrenom());
                    form.setField("competent_validation", "valider");
                } else {
                    form.setField("competent_nom", utilisateurFacade.findUtilParService("competent").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("competent").getUtilisateurPrenom());
                    form.setField("competent_validation", "refuser");
                }
            } else {
                form.setField("competent_validation", "En cours");
            }
            //...........
            visa = sortieB1Choisit.getVaisaBMRT();
            if (visa != null) {
                if (visa) {
                    form.setField("bmrt_nom", utilisateurFacade.findUtilParService("BMRT").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("BMRT").getUtilisateurPrenom());
                    form.setField("bmrt_validation", "valider");
                } else {
                    form.setField("bmrt_nom", utilisateurFacade.findUtilParService("BMRT").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("BMRT").getUtilisateurPrenom());
                    form.setField("bmrt_validation", "refuser");
                }
            } else {
                form.setField("bmrt_validation", "En cours");
            }
            //.........
            visa = sortieB1Choisit.getVisaSMRT();
            if (visa != null) {
                if (visa) {
                    form.setField("SMRT_nom", utilisateurFacade.findUtilParService("SMRT").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("SMRT").getUtilisateurPrenom());
                    form.setField("SMRT_validation", "valider");
                } else {
                    form.setField("SMRT_nom", utilisateurFacade.findUtilParService("SMRT").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("SMRT").getUtilisateurPrenom());
                    form.setField("SMRT_validation", "refuser");
                }
            } else {
                form.setField("SMRT_validation", "En cours");
            }
            //.........
            visa = sortieB1Choisit.getVisacomptabilite();
            if (visa != null) {
                if (visa) {
                    form.setField("comptabilite_nom", utilisateurFacade.findUtilParService("comptabilite").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("comptabilite").getUtilisateurPrenom());
                    form.setField("comptabilite_validation", "valider");
                } else {
                    form.setField("comptabilite_nom", utilisateurFacade.findUtilParService("comptabilite").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("comptabilite").getUtilisateurPrenom());
                    form.setField("comptabilite_validation", "refuser");
                }
            } else {
                form.setField("comptabilite_validation", "En cours");
            }
            //.........
            visa = sortieB1Choisit.getVisaDirFinancier();
            if (visa != null) {
                if (visa) {
                    form.setField("financier_nom", utilisateurFacade.findUtilParService("financier").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("financier").getUtilisateurPrenom());
                    form.setField("financier_validation", "valider");
                } else {
                    form.setField("financier_nom", utilisateurFacade.findUtilParService("financier").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("financier").getUtilisateurPrenom());
                    form.setField("financier_validation", "refuser");
                }
            } else {
                form.setField("financier_validation", "En cours");
            }
            //.........
            visa = sortieB1Choisit.getVisaDirGeneral();
            if (visa != null) {
                if (visa) {
                    form.setField("general_nom", utilisateurFacade.findUtilParService("general").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("general").getUtilisateurPrenom());
                    form.setField("general_validation", "valider");
                } else {
                    form.setField("general_nom", utilisateurFacade.findUtilParService("general").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("financier").getUtilisateurPrenom());
                    form.setField("general_validation", "refuser");
                }
            } else {
                form.setField("general_validation", "En cours");
            }
            //.........
            visa = sortieB1Choisit.getVisaSOS();
            if (visa != null) {
                if (visa) {
                    form.setField("SOS_nom", utilisateurFacade.findUtilParService("SOS").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("SOS").getUtilisateurPrenom());
                    form.setField("SOS_validation", "valider");
                } else {
                    form.setField("SOS_nom", utilisateurFacade.findUtilParService("SOS").getUtilisateurNom()
                            + " " + utilisateurFacade.findUtilParService("financier").getUtilisateurPrenom());
                    form.setField("SOS_validation", "refuser");
                }
            } else {
                form.setField("SOS_validation", "En cours");
            }
            //.........

            stamper.setFormFlattening(true);
            stamper.close();

            String fileName = "SortieB1_" + sortieB1Choisit.getIdsortieb1Societe();

            writePDFToResponse(context.getExternalContext(), baos, fileName);

            context.responseComplete();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    
    
    
    

}
