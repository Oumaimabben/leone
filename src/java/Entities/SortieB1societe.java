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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BAYA
 */
@Entity
@Table(name = "sortie_b1societe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SortieB1societe.findAll", query = "SELECT s FROM SortieB1societe s"),
    @NamedQuery(name = "SortieB1societe.findByIdsortieb1Societe", query = "SELECT s FROM SortieB1societe s WHERE s.idsortieb1Societe = :idsortieb1Societe"),
    @NamedQuery(name = "SortieB1societe.findByDateRetour", query = "SELECT s FROM SortieB1societe s WHERE s.dateRetour = :dateRetour"),
    @NamedQuery(name = "SortieB1societe.findByDateSortie", query = "SELECT s FROM SortieB1societe s WHERE s.dateSortie = :dateSortie"),
    @NamedQuery(name = "SortieB1societe.findByDestination", query = "SELECT s FROM SortieB1societe s WHERE s.destination = :destination"),
    @NamedQuery(name = "SortieB1societe.findByNatureBien", query = "SELECT s FROM SortieB1societe s WHERE s.natureBien = :natureBien"),
    @NamedQuery(name = "SortieB1societe.findByNumRebut", query = "SELECT s FROM SortieB1societe s WHERE s.numRebut = :numRebut"),
    @NamedQuery(name = "SortieB1societe.findByNumeroImmo", query = "SELECT s FROM SortieB1societe s WHERE s.numeroImmo = :numeroImmo"),
    @NamedQuery(name = "SortieB1societe.findByObservation", query = "SELECT s FROM SortieB1societe s WHERE s.observation = :observation"),
    @NamedQuery(name = "SortieB1societe.findByQuantite", query = "SELECT s FROM SortieB1societe s WHERE s.quantite = :quantite"),
    @NamedQuery(name = "SortieB1societe.findByResponsableRetour", query = "SELECT s FROM SortieB1societe s WHERE s.responsableRetour = :responsableRetour"),
    @NamedQuery(name = "SortieB1societe.findByTransporter", query = "SELECT s FROM SortieB1societe s WHERE s.transporter = :transporter"),
    @NamedQuery(name = "SortieB1societe.findByTypeSortie", query = "SELECT s FROM SortieB1societe s WHERE s.typeSortie = :typeSortie"),
    @NamedQuery(name = "SortieB1societe.findByVaisaBMRT", query = "SELECT s FROM SortieB1societe s WHERE s.vaisaBMRT = :vaisaBMRT"),
    @NamedQuery(name = "SortieB1societe.findByValidationG", query = "SELECT s FROM SortieB1societe s WHERE s.validationG = :validationG"),
    @NamedQuery(name = "SortieB1societe.findByVisaDirFinancier", query = "SELECT s FROM SortieB1societe s WHERE s.visaDirFinancier = :visaDirFinancier"),
    @NamedQuery(name = "SortieB1societe.findByVisaDirGeneral", query = "SELECT s FROM SortieB1societe s WHERE s.visaDirGeneral = :visaDirGeneral"),
    @NamedQuery(name = "SortieB1societe.findByVisaSMRT", query = "SELECT s FROM SortieB1societe s WHERE s.visaSMRT = :visaSMRT"),
    @NamedQuery(name = "SortieB1societe.findByVisaServiceCompetent", query = "SELECT s FROM SortieB1societe s WHERE s.visaServiceCompetent = :visaServiceCompetent"),
    @NamedQuery(name = "SortieB1societe.findByVisacomptabilite", query = "SELECT s FROM SortieB1societe s WHERE s.visacomptabilite = :visacomptabilite"),
    @NamedQuery(name = "SortieB1societe.findByVisaSOS", query = "SELECT s FROM SortieB1societe s WHERE s.visaSOS = :visaSOS")})
public class SortieB1societe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsortie_b1Societe")
    private Integer idsortieb1Societe;
    @Column(name = "DateRetour")
    @Temporal(TemporalType.DATE)
    private Date dateRetour;
    @Column(name = "dateSortie")
    @Temporal(TemporalType.DATE)
    private Date dateSortie;
    @Size(max = 255)
    @Column(name = "Destination")
    private String destination;
    @Size(max = 255)
    @Column(name = "NatureBien")
    private String natureBien;
    @Column(name = "NumRebut")
    private Integer numRebut;
    @Column(name = "NumeroImmo")
    private Integer numeroImmo;
    @Size(max = 255)
    @Column(name = "Observation")
    private String observation;
    @Column(name = "Quantite")
    private Integer quantite;
    @Size(max = 255)
    @Column(name = "ResponsableRetour")
    private String responsableRetour;
    @Size(max = 255)
    @Column(name = "Transporter")
    private String transporter;
    @Size(max = 255)
    @Column(name = "typeSortie")
    private String typeSortie;
    @Column(name = "VaisaBMRT")
    private Boolean vaisaBMRT;
    @Column(name = "ValidationG")
    private Boolean validationG;
    @Column(name = "VisaDirFinancier")
    private Boolean visaDirFinancier;
    @Column(name = "VisaDirGeneral")
    private Boolean visaDirGeneral;
    @Column(name = "VisaSMRT")
    private Boolean visaSMRT;
    @Column(name = "VisaServiceCompetent")
    private Boolean visaServiceCompetent;
    @Column(name = "visacomptabilite")
    private Boolean visacomptabilite;
    @Column(name = "visaSOS")
    private Boolean visaSOS;
    @JoinColumn(name = "utilisateur_idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur utilisateuridUtilisateur;
    @JoinColumn(name = "Type_Sortie_idType_Sortie", referencedColumnName = "idType_Sortie")
    @ManyToOne(optional = false)
    private TypeSortie typeSortieidTypeSortie;
    @JoinColumn(name = "biens_id_biens", referencedColumnName = "id_biens")
    @ManyToOne(optional = false)
    private Biens biensIdBiens;

    public SortieB1societe() {
    }

    public SortieB1societe(Integer idsortieb1Societe) {
        this.idsortieb1Societe = idsortieb1Societe;
    }

    public Integer getIdsortieb1Societe() {
        return idsortieb1Societe;
    }

    public void setIdsortieb1Societe(Integer idsortieb1Societe) {
        this.idsortieb1Societe = idsortieb1Societe;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNatureBien() {
        return natureBien;
    }

    public void setNatureBien(String natureBien) {
        this.natureBien = natureBien;
    }

    public Integer getNumRebut() {
        return numRebut;
    }

    public void setNumRebut(Integer numRebut) {
        this.numRebut = numRebut;
    }

    public Integer getNumeroImmo() {
        return numeroImmo;
    }

    public void setNumeroImmo(Integer numeroImmo) {
        this.numeroImmo = numeroImmo;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getResponsableRetour() {
        return responsableRetour;
    }

    public void setResponsableRetour(String responsableRetour) {
        this.responsableRetour = responsableRetour;
    }

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public String getTypeSortie() {
        return typeSortie;
    }

    public void setTypeSortie(String typeSortie) {
        this.typeSortie = typeSortie;
    }

    public Boolean getVaisaBMRT() {
        return vaisaBMRT;
    }

    public void setVaisaBMRT(Boolean vaisaBMRT) {
        this.vaisaBMRT = vaisaBMRT;
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

    public Boolean getVisaSMRT() {
        return visaSMRT;
    }

    public void setVisaSMRT(Boolean visaSMRT) {
        this.visaSMRT = visaSMRT;
    }

    public Boolean getVisaServiceCompetent() {
        return visaServiceCompetent;
    }

    public void setVisaServiceCompetent(Boolean visaServiceCompetent) {
        this.visaServiceCompetent = visaServiceCompetent;
    }

    public Boolean getVisacomptabilite() {
        return visacomptabilite;
    }

    public void setVisacomptabilite(Boolean visacomptabilite) {
        this.visacomptabilite = visacomptabilite;
    }

    public Boolean getVisaSOS() {
        return visaSOS;
    }

    public void setVisaSOS(Boolean visaSOS) {
        this.visaSOS = visaSOS;
    }

    public Utilisateur getUtilisateuridUtilisateur() {
        return utilisateuridUtilisateur;
    }

    public void setUtilisateuridUtilisateur(Utilisateur utilisateuridUtilisateur) {
        this.utilisateuridUtilisateur = utilisateuridUtilisateur;
    }

    public TypeSortie getTypeSortieidTypeSortie() {
        return typeSortieidTypeSortie;
    }

    public void setTypeSortieidTypeSortie(TypeSortie typeSortieidTypeSortie) {
        this.typeSortieidTypeSortie = typeSortieidTypeSortie;
    }

    public Biens getBiensIdBiens() {
        return biensIdBiens;
    }

    public void setBiensIdBiens(Biens biensIdBiens) {
        this.biensIdBiens = biensIdBiens;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsortieb1Societe != null ? idsortieb1Societe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SortieB1societe)) {
            return false;
        }
        SortieB1societe other = (SortieB1societe) object;
        if ((this.idsortieb1Societe == null && other.idsortieb1Societe != null) || (this.idsortieb1Societe != null && !this.idsortieb1Societe.equals(other.idsortieb1Societe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.SortieB1societe[ idsortieb1Societe=" + idsortieb1Societe + " ]";
    }
    
}
