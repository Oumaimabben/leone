
package Bean;


import Entities.Biens;
import Entities.Materielles;
import Session.SortieB1personnelFacade;
import Entities.SortieB1personnel;
import Entities.Utilisateur;
import Session.BiensFacade;
import Session.MateriellesFacade;
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
public class SortieB1persBean {

    @EJB
    private MateriellesFacade materiellesFacade;

    @EJB
    private BiensFacade biensFacade;
    private Biens bien;
    @EJB
    private SortieB1personnelFacade sortieB1personnelFacade;
    private SortieB1personnel sortieB1pers;
    private SortieB1personnel sortieB1persChoisit;
    @EJB
    private UtilisateurFacade utilisateurFacade;
    private Utilisateur utilisateur = new Utilisateur();
    private Date currentDate = new Date();
    private String msg;
    private String userName;
    List<String> l = new ArrayList<>();
    private int nextId;
    private int nbSB1persaValider;
    private List<String> fakeList;

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

    public List<Materielles> getListeMat() {
        return listeMat;
    }

    public void setListeMat(List<Materielles> listeMat) {
        this.listeMat = listeMat;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public int getNextId() {
        List<SortieB1personnel> l = sortieB1personnelFacade.findAll();
        int maxId = 0;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getIdsortieB1personnel() > maxId) {
                maxId = l.get(i).getIdsortieB1personnel();
            }
        }

        nextId = maxId + 1;
        return nextId;
    }

    @PostConstruct
    public void init() {
        this.sortieB1persChoisit = new SortieB1personnel();
        this.sortieB1pers = new SortieB1personnel();
        fakeList = new ArrayList<>();
        this.bien = new Biens();
        listeMat = new ArrayList<>();
        materielle = new Materielles();
        numMaterielle = 1;
    }

    public List<String> getFakeList() {
        fakeList.add("|");
        return fakeList;
    }

    public void setFakeList(List<String> fakeList) {
        this.fakeList = fakeList;
    }

    public int getNbSB1persaValider() {
        
        if(findSB1persNonVAlider()!=null){
        nbSB1persaValider = findSB1persNonVAlider().size();
        return nbSB1persaValider;}
        else return 0;
    }

    public void setNbSB1persaValider(int nbSB1persaValider) {
        this.nbSB1persaValider = nbSB1persaValider;
    }

    public SortieB1personnel getSortieB1persChoisit() {
        return sortieB1persChoisit;
    }

    public Biens getBien() {
        return bien;
    }

    public void setBien(Biens bien) {
        this.bien = bien;
    }

    public void setSortieB1persChoisit(SortieB1personnel sortieB1persChoisit) {
        this.sortieB1persChoisit = sortieB1persChoisit;
    }

    public List<SortieB1personnel> findValidatedAut() {
        FacesContext context = FacesContext.getCurrentInstance();
        List<SortieB1personnel> all = new ArrayList<SortieB1personnel>();
        List<SortieB1personnel> validated = new ArrayList<SortieB1personnel>();

        try {
            all = sortieB1personnelFacade.findAll();
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

    public List<SortieB1personnel> findInvalidatedAut() {
        FacesContext context = FacesContext.getCurrentInstance();
        List<SortieB1personnel> all = new ArrayList<SortieB1personnel>();
        List<SortieB1personnel> invalidated = new ArrayList<SortieB1personnel>();

        try {
            all = sortieB1personnelFacade.findAll();
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
                        if (all.get(i).getVisaBMRT() == null) {
                            invalidated.add(all.get(i));
                        }
                        break;
                    case 3:
                        if (all.get(i).getVisaSMRT() == null) {
                            invalidated.add(all.get(i));
                        }
                        break;
                    
                    case 4:
                        if (all.get(i).getVisaDirFinancier() == null) {
                            invalidated.add(all.get(i));
                        }
                        break;
                    case 5:
                        if (all.get(i).getVisaDirGeneral() == null) {
                            invalidated.add(all.get(i));
                        }
                        break;
                    case 6:
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
        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("financier")) {
            return 4;
        }

        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("direction general")) {
            return 5;
        }
        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SOS")) {
            return 6;
        }
        return 0;
    }


    public List<String> getL() {

        l.add("Notebook");
        l.add("Ordinateur");
        l.add("Autre");

        return l;
    }

    public void setL(List<String> l) {

        this.l = l;
    }

    /**
     * Creates a new instance of SortieB1persBean
     */
    public SortieB1persBean() {
    }

    public SortieB1personnelFacade getSortieB1personnelFacade() {
        return sortieB1personnelFacade;
    }

    public void setSortieB1personnelFacade(SortieB1personnelFacade sortieB1personnelFacade) {
        this.sortieB1personnelFacade = sortieB1personnelFacade;
    }

    public SortieB1personnel getSortieB1pers() {
        return sortieB1pers;
    }

    public void setSortieB1pers(SortieB1personnel sortieB1pers) {
        this.sortieB1pers = sortieB1pers;
    }

    public void create() {
        FacesContext context = FacesContext.getCurrentInstance();

      
        try {
            //bien.setNatureBien(msg);
            bien.setMateriellesList(listeMat);
            biensFacade.create(bien);
            context.addMessage(null, new FacesMessage("Info:", "Bien ajouté avec succes"));
        } catch (Exception e) {
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur", "erreur d'ajout"));
         }
         numMaterielle = 1;
        listeMat = new ArrayList<>();
        
        try {
            userName = context.getExternalContext().getUserPrincipal().toString();
            utilisateur.setIdUtilisateur(Integer.parseInt(userName));
            utilisateur = utilisateurFacade.find(utilisateur.getIdUtilisateur());
        } catch (Exception e) {

            userName = e.getMessage();

        }

        sortieB1pers.setUtilisateuridUtilisateur(utilisateur);
        if (sortieB1pers.getDatefin() != null) {
            if (sortieB1pers.getDatefin().after(sortieB1pers.getDatedebut())) {

                try {
                    sortieB1pers.setQuantite(quantite);
                    sortieB1pers.setBiensIdBiens(bien);
                    sortieB1personnelFacade.create(sortieB1pers);
                    msg = "autorisation ajoutée avec succés :) ";
                    this.sortieB1pers = new SortieB1personnel();
                    this.bien = new Biens();
                } catch (Exception e) {
                    msg = e.getMessage();

                }
            } else {
                  context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur", "Erreur! Date de fin < date de debut"));

                
            }

        } else {
            try {
                sortieB1personnelFacade.create(sortieB1pers);
                msg = "autorisation ajoutée avec succés :) ";
                this.sortieB1pers = new SortieB1personnel();
                this.bien = new Biens();
            } catch (Exception e) {
                msg = e.getMessage();

            }
        }

        context.addMessage(null, new FacesMessage("Info:", msg));

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void edit() {
        sortieB1personnelFacade.edit(sortieB1pers);
    }

    public void remove() {
         FacesContext context = FacesContext.getCurrentInstance();
        try {
            sortieB1personnelFacade.remove(sortieB1persChoisit);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Suppression avec succes"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur:", "Erreur lors de la suppression"));
        }
        
    }
        
  

    public SortieB1personnel find() {
        return sortieB1personnelFacade.find(sortieB1pers.getIdsortieB1personnel());
    }

    public List<SortieB1personnel> findAll() {
        return sortieB1personnelFacade.findAll();
    }

    public int count() {
        return sortieB1personnelFacade.count();
    }

    public void validerSortie() {
        FacesContext context = FacesContext.getCurrentInstance();
        // String userName ;

        userName = context.getExternalContext().getUserPrincipal().toString();
        //utilisateur.setIdUtilisateur(Integer.parseInt(userName));
        utilisateur = utilisateurFacade.find(Integer.parseInt(userName));

        utilisateur = utilisateurFacade.find(Integer.parseInt(userName));
        if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("competent")) {
            sortieB1persChoisit.setVisaServiceCompetent(Boolean.TRUE);
            context.addMessage(null, new FacesMessage("Info:", "Autorisation validé"));
            sortieB1personnelFacade.edit(sortieB1persChoisit);
            return;
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("BMRT")) {
            if (sortieB1persChoisit.getVisaServiceCompetent() != null) {
                sortieB1persChoisit.setVisaBMRT(Boolean.TRUE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation  validé"));
                sortieB1personnelFacade.edit(sortieB1persChoisit);
                return;
                
            }
            else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SMRT")) {
            if (sortieB1persChoisit.getVisaBMRT() != null) {
                sortieB1persChoisit.setVisaSMRT(Boolean.TRUE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation validé"));
                sortieB1personnelFacade.edit(sortieB1persChoisit);
                return;
            }
            else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("financier")) {
            if (sortieB1persChoisit.getVisaSMRT() != null) {
                sortieB1persChoisit.setVisaDirFinancier(Boolean.TRUE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation validé"));
                sortieB1personnelFacade.edit(sortieB1persChoisit);
                return;
            }
            else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("direction general")) {
            if (sortieB1persChoisit.getVisaDirFinancier() != null) {
                sortieB1persChoisit.setVisaDirGeneral(Boolean.TRUE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation validé"));
                sortieB1personnelFacade.edit(sortieB1persChoisit);
                return;

            }
            else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SOS")) {
            if (sortieB1persChoisit.getVisaDirGeneral() != null) {
                sortieB1persChoisit.setVisaSOS(Boolean.TRUE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation validé"));
                sortieB1personnelFacade.edit(sortieB1persChoisit);
            }
        }
        else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }

           Boolean b = null;
        if (sortieB1persChoisit.getVisaBMRT() != null
                && sortieB1persChoisit.getVisaDirFinancier() != null
                && sortieB1persChoisit.getVisaDirGeneral() != null
                && sortieB1persChoisit.getVisaSMRT() != null
                && sortieB1persChoisit.getVisaServiceCompetent() != null) {
            b = sortieB1persChoisit.getVisaBMRT()
                    && sortieB1persChoisit.getVisaDirFinancier()
                    && sortieB1persChoisit.getVisaDirGeneral()
                    && sortieB1persChoisit.getVisaSMRT()
                    && sortieB1persChoisit.getVisaServiceCompetent();
        }

        if (b != null) {
            if (b.booleanValue() == Boolean.FALSE) {
                sortieB1persChoisit.setValidationG(Boolean.FALSE);
            } else {
                sortieB1persChoisit.setValidationG(Boolean.TRUE);
            }
                sortieB1personnelFacade.edit(sortieB1persChoisit);
        }

    }

    public void refuserSortie() {

        FacesContext context = FacesContext.getCurrentInstance();

        userName = context.getExternalContext().getUserPrincipal().toString();

        utilisateur = utilisateurFacade.find(Integer.parseInt(userName));

          if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("competent")) {
            sortieB1persChoisit.setVisaServiceCompetent(Boolean.FALSE);
            context.addMessage(null, new FacesMessage("Info:", "Autorisation refusé"));
            sortieB1personnelFacade.edit(sortieB1persChoisit);
            return;
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("BMRT")) {
            if (sortieB1persChoisit.getVisaServiceCompetent() != null) {
                sortieB1persChoisit.setVisaBMRT(Boolean.FALSE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation  refusé"));
                sortieB1personnelFacade.edit(sortieB1persChoisit);
                return;
                
            }
            else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SMRT")) {
            if (sortieB1persChoisit.getVisaBMRT() != null) {
                sortieB1persChoisit.setVisaSMRT(Boolean.FALSE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation refusé"));
                sortieB1personnelFacade.edit(sortieB1persChoisit);
                return;
            }
            else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("financier")) {
            if (sortieB1persChoisit.getVisaSMRT() != null) {
                sortieB1persChoisit.setVisaDirFinancier(Boolean.FALSE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation refusé"));
                sortieB1personnelFacade.edit(sortieB1persChoisit);
                return;
            }
            else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("direction general")) {
            if (sortieB1persChoisit.getVisaDirFinancier() != null) {
                sortieB1persChoisit.setVisaDirGeneral(Boolean.FALSE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation refusé"));
                sortieB1personnelFacade.edit(sortieB1persChoisit);
                return;

            }
            else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        } else if (utilisateur.getServiceIdService().getDesigService().equalsIgnoreCase("SOS")) {
            if (sortieB1persChoisit.getVisaDirGeneral() != null) {
                sortieB1persChoisit.setVisaSOS(Boolean.FALSE);
                context.addMessage(null, new FacesMessage("Info:", "Autorisation refusé"));
                sortieB1personnelFacade.edit(sortieB1persChoisit);
            }
        }
        else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
                return;
            }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", "Validation précedente est en cours!"));
        Boolean b = null;
        if (sortieB1persChoisit.getVisaBMRT() != null
                && sortieB1persChoisit.getVisaDirFinancier() != null
                && sortieB1persChoisit.getVisaDirGeneral() != null
                && sortieB1persChoisit.getVisaSMRT() != null
                && sortieB1persChoisit.getVisaSOS() != null
                && sortieB1persChoisit.getVisaServiceCompetent() != null) {
            b = sortieB1persChoisit.getVisaBMRT()
                    && sortieB1persChoisit.getVisaDirFinancier()
                    && sortieB1persChoisit.getVisaDirGeneral()
                    && sortieB1persChoisit.getVisaSMRT()
                    && sortieB1persChoisit.getVisaSOS()
                    && sortieB1persChoisit.getVisaServiceCompetent();
        }

        if (b != null) {
            if (b.booleanValue() == Boolean.FALSE) {
                sortieB1persChoisit.setValidationG(Boolean.FALSE);
            } else {
                sortieB1persChoisit.setValidationG(Boolean.TRUE);
            }
                sortieB1personnelFacade.edit(sortieB1persChoisit);
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

    public List<SortieB1personnel> findSB1persNonVAlider() {
        FacesContext context = FacesContext.getCurrentInstance();

        userName = context.getExternalContext().getUserPrincipal().toString();

        utilisateur = utilisateurFacade.find(Integer.parseInt(userName));
        List<SortieB1personnel> all = sortieB1personnelFacade.findAll();
        List<SortieB1personnel> nonValider = new ArrayList<>();

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
                if (all.get(i).getVisaSMRT() == null) {
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
              context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur", "erreur d'ajout"));
      
        }

    }
      public void createPDF() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            String in = "../../resources/b1perso.pdf";

            PdfReader reader = new PdfReader(in);

            PdfStamper stamper = new PdfStamper(reader, baos);

            AcroFields form = stamper.getAcroFields();

            form.setField("Num", sortieB1persChoisit.getIdsortieB1personnel().toString());
            SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
            form.setField("dateD", dt1.format(sortieB1persChoisit.getDatedebut()));
            form.setField("dateF", dt1.format(sortieB1persChoisit.getDatefin()));

            form.setField("demandeur", sortieB1persChoisit.getUtilisateuridUtilisateur().getUtilisateurNom() + " " + sortieB1persChoisit.getUtilisateuridUtilisateur().getUtilisateurPrenom());
            form.setField("service", sortieB1persChoisit.getUtilisateuridUtilisateur().getServiceIdService().getDesigService());
            form.setField("fonction", sortieB1persChoisit.getUtilisateuridUtilisateur().getFonctionIdFct().getDesigFct());
            form.setField("natureB", sortieB1persChoisit.getBiensIdBiens().getNatureBien());
            form.setField("quantite", sortieB1persChoisit.getQuantite().toString());

            form.setField("destination", sortieB1persChoisit.getDestination());
            form.setField("transporteurN", sortieB1persChoisit.getNomT());
            form.setField("transporteurP", sortieB1persChoisit.getPrenomT());
            form.setField("institut", sortieB1persChoisit.getInstitut());
            form.setField("TypeS", sortieB1persChoisit.getTypeStageIdtypeStage().getTypeStage());
            String listNumImmo = "";
            String listMarque = "";
            List<Materielles> l = sortieB1persChoisit.getBiensIdBiens().getMateriellesList();
            for (int i = 0; i < l.size(); i++) {
                listNumImmo = listNumImmo + l.get(i).getNumImm() + ",";
                listMarque = listMarque + l.get(i).getMarque() + ",";
            }

            form.setField("numimm", listNumImmo);
            form.setField("Marque", listMarque);

            Boolean visa = sortieB1persChoisit.getVisaServiceCompetent();
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
            visa = sortieB1persChoisit.getVisaBMRT();
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
            visa = sortieB1persChoisit.getVisaSMRT();
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
            visa = sortieB1persChoisit.getVisaDirFinancier();
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
            visa = sortieB1persChoisit.getVisaDirGeneral();
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
            visa = sortieB1persChoisit.getVisaSOS();
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

            stamper.setFormFlattening(true);
            stamper.close();

            String fileName = "Sortie bien personnel_" + sortieB1persChoisit.getIdsortieB1personnel();

            writePDFToResponse(context.getExternalContext(), baos, fileName);

            context.responseComplete();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
