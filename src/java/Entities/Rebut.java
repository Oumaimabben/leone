/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
@Table(name = "rebut")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rebut.findAll", query = "SELECT r FROM Rebut r"),
    @NamedQuery(name = "Rebut.findByIdrebut", query = "SELECT r FROM Rebut r WHERE r.idrebut = :idrebut"),
    @NamedQuery(name = "Rebut.findByVisaBMRT", query = "SELECT r FROM Rebut r WHERE r.visaBMRT = :visaBMRT"),
    @NamedQuery(name = "Rebut.findByVisaSMRT", query = "SELECT r FROM Rebut r WHERE r.visaSMRT = :visaSMRT"),
    @NamedQuery(name = "Rebut.findByCauseRebut", query = "SELECT r FROM Rebut r WHERE r.causeRebut = :causeRebut"),
    @NamedQuery(name = "Rebut.findByCentreCout", query = "SELECT r FROM Rebut r WHERE r.centreCout = :centreCout"),
    @NamedQuery(name = "Rebut.findByDateAcquistion", query = "SELECT r FROM Rebut r WHERE r.dateAcquistion = :dateAcquistion"),
    @NamedQuery(name = "Rebut.findByDesignation", query = "SELECT r FROM Rebut r WHERE r.designation = :designation"),
    @NamedQuery(name = "Rebut.findByMontantAcqusition", query = "SELECT r FROM Rebut r WHERE r.montantAcqusition = :montantAcqusition"),
    @NamedQuery(name = "Rebut.findByNatureInvestissement", query = "SELECT r FROM Rebut r WHERE r.natureInvestissement = :natureInvestissement"),
    @NamedQuery(name = "Rebut.findByPerte", query = "SELECT r FROM Rebut r WHERE r.perte = :perte"),
    @NamedQuery(name = "Rebut.findByRqs", query = "SELECT r FROM Rebut r WHERE r.rqs = :rqs"),
    @NamedQuery(name = "Rebut.findByValeurResiduelle", query = "SELECT r FROM Rebut r WHERE r.valeurResiduelle = :valeurResiduelle"),
    @NamedQuery(name = "Rebut.findByValidationG", query = "SELECT r FROM Rebut r WHERE r.validationG = :validationG"),
    @NamedQuery(name = "Rebut.findByVisaDirFinancier", query = "SELECT r FROM Rebut r WHERE r.visaDirFinancier = :visaDirFinancier"),
    @NamedQuery(name = "Rebut.findByVisaDirGeneral", query = "SELECT r FROM Rebut r WHERE r.visaDirGeneral = :visaDirGeneral"),
    @NamedQuery(name = "Rebut.findByVisaDouanier", query = "SELECT r FROM Rebut r WHERE r.visaDouanier = :visaDouanier"),
    @NamedQuery(name = "Rebut.findByVisaServiceCompetent", query = "SELECT r FROM Rebut r WHERE r.visaServiceCompetent = :visaServiceCompetent"),
    @NamedQuery(name = "Rebut.findByDateSortie", query = "SELECT r FROM Rebut r WHERE r.dateSortie = :dateSortie")})
public class Rebut implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrebut")
    private Integer idrebut;
    @Column(name = "VisaBMRT")
    private Boolean visaBMRT;
    @Column(name = "VisaSMRT")
    private Boolean visaSMRT;
    @Size(max = 255)
    @Column(name = "CauseRebut")
    private String causeRebut;
    @Size(max = 255)
    @Column(name = "CentreCout")
    private String centreCout;
    @Column(name = "DateAcquistion")
    @Temporal(TemporalType.DATE)
    private Date dateAcquistion;
    @Size(max = 255)
    @Column(name = "Designation")
    private String designation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MontantAcqusition")
    private Float montantAcqusition;
    @NotNull
    @Column(name = "Quantite")
    private Integer quantite;
    @Size(max = 255)
    @Column(name = "NatureInvestissement")
    private String natureInvestissement;
    @Column(name = "Perte")
    private Float perte;
    @Size(max = 255)
    @Column(name = "Rqs")
    private String rqs;
    @Column(name = "ValeurResiduelle")
    private Float valeurResiduelle;
    @Column(name = "ValidationG")
    private Boolean validationG;
    @Column(name = "VisaDirFinancier")
    private Boolean visaDirFinancier;
    @Column(name = "VisaDirGeneral")
    private Boolean visaDirGeneral;
    @Column(name = "VisaDouanier")
    private Boolean visaDouanier;
    @Column(name = "VisaServiceCompetent")
    private Boolean visaServiceCompetent;
    @Column(name = "Date_Sortie")
    @Temporal(TemporalType.DATE)
    private Date dateSortie;
    @JoinColumn(name = "biens_id_biens", referencedColumnName = "id_biens")
    @ManyToOne(optional = false)
    private Biens biensIdBiens;
    @JoinColumn(name = "utilisateur_idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur utilisateuridUtilisateur;

    public Rebut() {
    }

    public Rebut(Integer idrebut) {
        this.idrebut = idrebut;
    }

    public Integer getIdrebut() {
        return idrebut;
    }

    public void setIdrebut(Integer idrebut) {
        this.idrebut = idrebut;
    }

    public Boolean getVisaBMRT() {
        return visaBMRT;
    }

    public void setVisaBMRT(Boolean visaBMRT) {
        this.visaBMRT = visaBMRT;
    }

    public Boolean getVisaSMRT() {
        return visaSMRT;
    }

    public void setVisaSMRT(Boolean visaSMRT) {
        this.visaSMRT = visaSMRT;
    }

    public String getCauseRebut() {
        return causeRebut;
    }

    public void setCauseRebut(String causeRebut) {
        this.causeRebut = causeRebut;
    }

    public String getCentreCout() {
        return centreCout;
    }

    public void setCentreCout(String centreCout) {
        this.centreCout = centreCout;
    }

    public Date getDateAcquistion() {
        return dateAcquistion;
    }

    public void setDateAcquistion(Date dateAcquistion) {
        this.dateAcquistion = dateAcquistion;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Float getMontantAcqusition() {
        return montantAcqusition;
    }

    public void setMontantAcqusition(Float montantAcqusition) {
        this.montantAcqusition = montantAcqusition;
    }

    public String getNatureInvestissement() {
        return natureInvestissement;
    }

    public void setNatureInvestissement(String natureInvestissement) {
        this.natureInvestissement = natureInvestissement;
    }

    public Float getPerte() {
        return perte;
    }

    public void setPerte(Float perte) {
        this.perte = perte;
    }

    public String getRqs() {
        return rqs;
    }

    public void setRqs(String rqs) {
        this.rqs = rqs;
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

    public Boolean getVisaDirFinancier() {
        return visaDirFinancier;
    }

    public void setVisaDirFinancier(Boolean visaDirFinancier) {
        this.visaDirFinancier = visaDirFinancier;
    }

    public Boolean getVisaDirGeneral() {
        return visaDirGeneral;
    }

    public void setVisaDirGeneral(Boolean visaDirGeneral) {
        this.visaDirGeneral = visaDirGeneral;
    }

    public Boolean getVisaDouanier() {
        return visaDouanier;
    }

    public void setVisaDouanier(Boolean visaDouanier) {
        this.visaDouanier = visaDouanier;
    }

    public Boolean getVisaServiceCompetent() {
        return visaServiceCompetent;
    }

    public void setVisaServiceCompetent(Boolean visaServiceCompetent) {
        this.visaServiceCompetent = visaServiceCompetent;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
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
        hash += (idrebut != null ? idrebut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rebut)) {
            return false;
        }
        Rebut other = (Rebut) object;
        if ((this.idrebut == null && other.idrebut != null) || (this.idrebut != null && !this.idrebut.equals(other.idrebut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Rebut[ idrebut=" + idrebut + " ]";
    }
    
}
