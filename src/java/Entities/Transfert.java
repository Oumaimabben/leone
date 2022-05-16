
package Entities;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BAYA
 */
@Entity
@Table(name = "transfert")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transfert.findAll", query = "SELECT t FROM Transfert t"),
    @NamedQuery(name = "Transfert.findByIdtransfert", query = "SELECT t FROM Transfert t WHERE t.idtransfert = :idtransfert"),
    @NamedQuery(name = "Transfert.findByCentreEmetteur", query = "SELECT t FROM Transfert t WHERE t.centreEmetteur = :centreEmetteur"),
    @NamedQuery(name = "Transfert.findByCentreReceveur", query = "SELECT t FROM Transfert t WHERE t.centreReceveur = :centreReceveur"),
    @NamedQuery(name = "Transfert.findByDateEntre", query = "SELECT t FROM Transfert t WHERE t.dateEntre = :dateEntre"),
    @NamedQuery(name = "Transfert.findByDateTransfert", query = "SELECT t FROM Transfert t WHERE t.dateTransfert = :dateTransfert"),
    @NamedQuery(name = "Transfert.findByDesignation", query = "SELECT t FROM Transfert t WHERE t.designation = :designation"),
    @NamedQuery(name = "Transfert.findByObservation", query = "SELECT t FROM Transfert t WHERE t.observation = :observation"),
    @NamedQuery(name = "Transfert.findByValeurResiduelle", query = "SELECT t FROM Transfert t WHERE t.valeurResiduelle = :valeurResiduelle"),
    @NamedQuery(name = "Transfert.findByValidationG", query = "SELECT t FROM Transfert t WHERE t.validationG = :validationG"),
    @NamedQuery(name = "Transfert.findByVisaResCoutEmetteur", query = "SELECT t FROM Transfert t WHERE t.visaResCoutEmetteur = :visaResCoutEmetteur"),
    @NamedQuery(name = "Transfert.findByVisaResCoutReceveur", query = "SELECT t FROM Transfert t WHERE t.visaResCoutReceveur = :visaResCoutReceveur"),
    @NamedQuery(name = "Transfert.findByVisaResGestionImm", query = "SELECT t FROM Transfert t WHERE t.visaResGestionImm = :visaResGestionImm"),
    @NamedQuery(name = "Transfert.findByVisaServiceCompetent", query = "SELECT t FROM Transfert t WHERE t.visaServiceCompetent = :visaServiceCompetent")})
public class Transfert implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtransfert")
    private Integer idtransfert;
    @Size(max = 255)
    @Column(name = "CentreEmetteur")
    private String centreEmetteur;
    @Size(max = 255)
    @Column(name = "CentreReceveur")
    private String centreReceveur;
    @NotNull
    @Column(name = "Quantite")
    private Integer quantite;
    @Column(name = "DateEntre")
    @Temporal(TemporalType.DATE)
    private Date dateEntre;
    @Column(name = "DateTransfert")
    @Temporal(TemporalType.DATE)
    private Date dateTransfert;
    @Size(max = 255)
    @Column(name = "Designation")
    private String designation;
    @Size(max = 255)
    @Column(name = "Observation")
    private String observation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ValeurResiduelle")
    private Float valeurResiduelle;
    @Column(name = "ValidationG")
    private Boolean validationG;
    @Column(name = "VisaResCoutEmetteur")
    private Boolean visaResCoutEmetteur;
    @Column(name = "VisaResCoutReceveur")
    private Boolean visaResCoutReceveur;
    @Column(name = "VisaResGestionImm")
    private Boolean visaResGestionImm;
    @Column(name = "VisaServiceCompetent")
    private Boolean visaServiceCompetent;
    @JoinColumn(name = "biens_id_biens", referencedColumnName = "id_biens")
    @ManyToOne(optional = false)
    private Biens biensIdBiens;
    @JoinColumn(name = "utilisateur_idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur utilisateuridUtilisateur;

    public Transfert() {
    }

    public Transfert(Integer idtransfert) {
        this.idtransfert = idtransfert;
    }

    public Integer getIdtransfert() {
        return idtransfert;
    }

    public void setIdtransfert(Integer idtransfert) {
        this.idtransfert = idtransfert;
    }

    public String getCentreEmetteur() {
        return centreEmetteur;
    }

    public void setCentreEmetteur(String centreEmetteur) {
        this.centreEmetteur = centreEmetteur;
    }

    public String getCentreReceveur() {
        return centreReceveur;
    }

    public void setCentreReceveur(String centreReceveur) {
        this.centreReceveur = centreReceveur;
    }

    public Date getDateEntre() {
        return dateEntre;
    }

    public void setDateEntre(Date dateEntre) {
        this.dateEntre = dateEntre;
    }

    public Date getDateTransfert() {
        return dateTransfert;
    }

    public void setDateTransfert(Date dateTransfert) {
        this.dateTransfert = dateTransfert;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Float getValeurResiduelle() {
        return valeurResiduelle;
    }

    public void setValeurResiduelle(Float valeurResiduelle) {
        this.valeurResiduelle = valeurResiduelle;
    }

    public Boolean getValidationG() {
        return validationG;
    }

    public void setValidationG(Boolean validationG) {
        this.validationG = validationG;
    }

    public Boolean getVisaResCoutEmetteur() {
        return visaResCoutEmetteur;
    }

    public void setVisaResCoutEmetteur(Boolean visaResCoutEmetteur) {
        this.visaResCoutEmetteur = visaResCoutEmetteur;
    }

    public Boolean getVisaResCoutReceveur() {
        return visaResCoutReceveur;
    }

    public void setVisaResCoutReceveur(Boolean visaResCoutReceveur) {
        this.visaResCoutReceveur = visaResCoutReceveur;
    }

    public Boolean getVisaResGestionImm() {
        return visaResGestionImm;
    }

    public void setVisaResGestionImm(Boolean visaResGestionImm) {
        this.visaResGestionImm = visaResGestionImm;
    }

    public Boolean getVisaServiceCompetent() {
        return visaServiceCompetent;
    }

    public void setVisaServiceCompetent(Boolean visaServiceCompetent) {
        this.visaServiceCompetent = visaServiceCompetent;
    }

    public Biens getBiensIdBiens() {
        return biensIdBiens;
    }

    public void setBiensIdBiens(Biens biensIdBiens) {
        this.biensIdBiens = biensIdBiens;
    }

    public Utilisateur getUtilisateuridUtilisateur() {
        return utilisateuridUtilisateur;
    }

    public void setUtilisateuridUtilisateur(Utilisateur utilisateuridUtilisateur) {
        this.utilisateuridUtilisateur = utilisateuridUtilisateur;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtransfert != null ? idtransfert.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfert)) {
            return false;
        }
        Transfert other = (Transfert) object;
        if ((this.idtransfert == null && other.idtransfert != null) || (this.idtransfert != null && !this.idtransfert.equals(other.idtransfert))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Transfert[ idtransfert=" + idtransfert + " ]";
    }
    
}
