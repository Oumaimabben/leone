
package Bean;


import Entities.Utilisateur;
import Session.UtilisateurFacade;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;

@ManagedBean
@SessionScoped
public class UtilisateurBean implements Serializable {
    @EJB
    private UtilisateurFacade utilisateurFacade;
    private Utilisateur utilisateur = new Utilisateur();
    private Utilisateur nouveauUtilisateur;
    private Utilisateur utilisateurChoisit = new Utilisateur();
    private Utilisateur userdb = new Utilisateur();

    private String userName = new String();
    private static Logger log = Logger.getLogger(UtilisateurBean.class.getName());
    private String msg;
    private String ancienpass;
    private String nouveaupass;

    @PostConstruct
    public void init() {
        this.nouveauUtilisateur = new Utilisateur();
        this.utilisateurChoisit = new Utilisateur();
    }

    public String getAncienpass() {
        return ancienpass;
    }

    public void setAncienpass(String ancienpass) {
        this.ancienpass = ancienpass;
    }

    public String getNouveaupass() {
        return nouveaupass;
    }

    public void setNouveaupass(String nouveaupass) {
        this.nouveaupass = nouveaupass;
    }

    public Utilisateur getNouveauUtilisateur() {
        return nouveauUtilisateur;
    }

    public void setNouveauUtilisateur(Utilisateur nouveauUtilisateur) {
        this.nouveauUtilisateur = nouveauUtilisateur;
    }

    public Utilisateur getUtilisateurChoisit() {
        return utilisateurChoisit;
    }

    public void setUtilisateurChoisit(Utilisateur utilisateurChoisit) {
        this.utilisateurChoisit = utilisateurChoisit;
    }

    public Utilisateur getUserdb() {
        return userdb;
    }

    public void setUserdb(Utilisateur userdb) {
        this.userdb = userdb;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserName() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            userName = context.getExternalContext().getUserPrincipal().toString();
        } catch (Exception e) {
            userName = e.getMessage();
        }

        return userName;
    }

    public UtilisateurFacade getUtilisateurFacade() {
        return utilisateurFacade;
    }

    public void setUtilisateurFacade(UtilisateurFacade utilisateurFacade) {
        this.utilisateurFacade = utilisateurFacade;
    }

    public Utilisateur getUtilisateur() {

        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     * Creates a new instance of UtilisateurBean
     */
    public UtilisateurBean() {
    }

    public void AjouterUtilisateur() throws NoSuchAlgorithmException {
        FacesContext context = FacesContext.getCurrentInstance();
        nouveauUtilisateur.setPassword(HashMD5());
        List<Utilisateur> l = utilisateurFacade.findAll();
        boolean UtilExiste = false;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getIdUtilisateur() == nouveauUtilisateur.getIdUtilisateur()) {
                UtilExiste = true;
            }
        }

        if (UtilExiste) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur", "matricule utilisateur Existe deja!"));
        } else {
            try {
                utilisateurFacade.create(nouveauUtilisateur);
                msg = "utilisateur ajouté avec succés";
                nouveauUtilisateur = new Utilisateur();
           
            } catch (ConstraintViolationException e) {
                msg = e.getMessage();
                msg = "Erreur!" + msg;
            }
            context.addMessage(null, new FacesMessage("Info:", msg));
        }
    }

    private String HashMD5() throws NoSuchAlgorithmException {
        String password = nouveauUtilisateur.getPassword();

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
    private String HashMD5pass(String password) throws NoSuchAlgorithmException {
        

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public void edit() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            utilisateurFacade.edit(utilisateurChoisit);
            msg = "Utilisateur modifié avec succés";
        } catch (Exception e) {

            msg = e.getMessage();
        }

        context.addMessage(null, new FacesMessage("Info:", msg));
    }

    public void remove() {

        utilisateurFacade.remove(utilisateurChoisit);
    }

    public Utilisateur find() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            userName = context.getExternalContext().getUserPrincipal().toString();
            utilisateur.setIdUtilisateur(Integer.parseInt(userName));
            utilisateur = utilisateurFacade.find(utilisateur.getIdUtilisateur());
        } catch (Exception e) {

            userName = e.getMessage();

        }

        return utilisateur;
    }

    public Boolean isAdmin() {
        Boolean b;
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            userName = context.getExternalContext().getUserPrincipal().toString();
            utilisateur.setIdUtilisateur(Integer.parseInt(userName));
            utilisateur = utilisateurFacade.find(utilisateur.getIdUtilisateur());
            b = utilisateur.getUsertype().equalsIgnoreCase("admin");

        } catch (Exception e) {
            b = false;
        }

        return b;
    }

    public List<Utilisateur> findAll() {
        return utilisateurFacade.findAll();
    }

    public int count() {
        return utilisateurFacade.count();
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        return "index";
    }
    
    public void changerMotDePasse(){
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {
            userName = context.getExternalContext().getUserPrincipal().toString();
            utilisateur.setIdUtilisateur(Integer.parseInt(userName));
            utilisateur = utilisateurFacade.find(utilisateur.getIdUtilisateur());
            if(utilisateur.getPassword().equals(HashMD5pass(ancienpass))){
                utilisateur.setPassword(HashMD5pass(nouveaupass));
                utilisateurFacade.edit(utilisateur);
                msg = "Mot de passe changé avec succes";
            } else {
                msg = "Ancien mot de passe incorrecte";
            }
            ancienpass = "";
            nouveaupass = "";
        } catch (Exception e) {

            msg = e.getMessage();
        }

        context.addMessage(null, new FacesMessage("Info:", msg));
    }

}
