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
@Table(name = "sortie_b1personnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SortieB1personnel.findAll", query = "SELECT s FROM SortieB1personnel s"),
    @NamedQuery(name = "SortieB1personnel.findByIdsortieB1personnel", query = "SELECT s FROM SortieB1personnel s WHERE s.idsortieB1personnel = :idsortieB1personnel"),
    @NamedQuery(name = "SortieB1personnel.findByAccordRefus", query = "SELECT s FROM SortieB1personnel s WHERE s.accordRefus = :accordRefus"),
    @NamedQuery(name = "SortieB1personnel.findByDestination", query = "SELECT s FROM SortieB1personnel s WHERE s.destination = :destination"),
    @NamedQuery(name = "SortieB1personnel.findByInstitut", query = "SELECT s FROM SortieB1personnel s WHERE s.institut = :institut"),
     @NamedQuery(name = "SortieB1personnel.findByNomT", query = "SELECT s FROM SortieB1personnel s WHERE s.nomT = :nomT"),
    @NamedQuery(name = "SortieB1personnel.findByObservation", query = "SELECT s FROM SortieB1personnel s WHERE s.observation = :observation"),
    @NamedQuery(name = "SortieB1personnel.findByPrenomT", query = "SELECT s FROM SortieB1personnel s WHERE s.prenomT = :prenomT"),
    @NamedQuery(name = "SortieB1personnel.findByQuantite", query = "SELECT s FROM SortieB1personnel s WHERE s.quantite = :quantite"),
    @NamedQuery(name = "SortieB1personnel.findByRemarque", query = "SELECT s FROM SortieB1personnel s WHERE s.remarque = :remarque"),
    @NamedQuery(name = "SortieB1personnel.findByValidationG", query = "SELECT s FROM SortieB1personnel s WHERE s.validationG = :validationG"),
    @NamedQuery(name = "SortieB1personnel.findByVisaBMRT", query = "SELECT s FROM SortieB1personnel s WHERE s.visaBMRT = :visaBMRT"),
    @NamedQuery(name = "SortieB1personnel.findByVisaDirFinancier", query = "SELECT s FROM SortieB1personnel s WHERE s.visaDirFinancier = :visaDirFinancier"),
    @NamedQuery(name = "SortieB1personnel.findByVisaDirGeneral", query = "SELECT s FROM SortieB1personnel s WHERE s.visaDirGeneral = :visaDirGeneral"),
    @NamedQuery(name = "SortieB1personnel.findByVisaSMRT", query = "SELECT s FROM SortieB1personnel s WHERE s.visaSMRT = :visaSMRT"),
    @NamedQuery(name = "SortieB1personnel.findByVisaServiceCompetent", query = "SELECT s FROM SortieB1personnel s WHERE s.visaServiceCompetent = :visaServiceCompetent"),
    @NamedQuery(name = "SortieB1personnel.findByDatedebut", query = "SELECT s FROM SortieB1personnel s WHERE s.datedebut = :datedebut"),
    @NamedQuery(name = "SortieB1personnel.findByDatefin", query = "SELECT s FROM SortieB1personnel s WHERE s.datefin = :datefin"),
    @NamedQuery(name = "SortieB1personnel.findByVisaSOS", query = "SELECT s FROM SortieB1personnel s WHERE s.visaSOS = :visaSOS")})
public class SortieB1personnel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsortie_b1personnel")
    private Integer idsortieB1personnel;
    @Column(name = "AccordRefus")
    private Boolean accordRefus;
    @Size(max = 255)
    @Column(name = "Destination")
    private String destination;
    @Size(max = 255)
    @Column(name = "Institut")
    private String institut;
    @Size(max = 255)
    @Column(name = "NomT")
    private String nomT;
    @Size(max = 255)
    @Column(name = "Observation")
    private String observation;
    @Size(max = 255)
    @Column(name = "PrenomT")
    private String prenomT;
    @NotNull
    @Column(name = "Quantite")
    private Integer quantite;
    @Size(max = 255)
    @Column(name = "Remarque")
    private String remarque;
    @Column(name = "ValidationG")
    private Boolean validationG;
    @Column(name = "VisaBMRT")
    private Boolean visaBMRT;
    @Column(name = "VisaDirFinancier")
    private Boolean visaDirFinancier;
    @Column(name = "VisaDirGeneral")
    private Boolean visaDirGeneral;
    @Column(name = "VisaSMRT")
    private Boolean visaSMRT;
    @Column(name = "VisaServiceCompetent")
    private Boolean visaServiceCompetent;
    @Column(name = "datedebut")
    @Temporal(TemporalType.DATE)
    private Date datedebut;
    @Column(name = "datefin")
    @Temporal(TemporalType.DATE)
    private Date datefin;
    @Column(name = "visaSOS")
    private Boolean visaSOS;
    @JoinColumn(name = "biens_id_biens", referencedColumnName = "id_biens")
    @ManyToOne(optional = false)
    private Biens biensIdBiens;
    @JoinColumn(name = "type_stage_idtype_stage", referencedColumnName = "idtype_stage")
    @ManyToOne(optional = false)
    private TypeStage typeStageIdtypeStage;
    @JoinColumn(name = "utilisateur_idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur utilisateuridUtilisateur;

    public SortieB1personnel() {
    }

    public SortieB1personnel(Integer idsortieB1personnel) {
        this.idsortieB1personnel = idsortieB1personnel;
    }

    public Integer getIdsortieB1personnel() {
        return idsortieB1personnel;
    }

    public void setIdsortieB1personnel(Integer idsortieB1personnel) {
        this.idsortieB1personnel = idsortieB1personnel;
    }

    public Boolean getAccordRefus() {
        return accordRefus;
    }

    public void setAccordRefus(Boolean accordRefus) {
        this.accordRefus = accordRefus;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getInstitut() {
        return institut;
    }

    public void setInstitut(String institut) {
        this.institut = institut;
    }

   

    public String getNomT() {
        return nomT;
    }

    public void setNomT(String nomT) {
        this.nomT = nomT;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getPrenomT() {
        return prenomT;
    }

    public void setPrenomT(String prenomT) {
        this.prenomT = prenomT;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Boolean getValidationG() {
        return validationG;
    }

    public void setValidationG(Boolean validationG) {
        this.validationG = validationG;
    }

    public Boolean getVisaBMRT() {
        return visaBMRT;
    }

    public void setVisaBMRT(Boolean visaBMRT) {
        this.visaBMRT = visaBMRT;
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

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public Boolean getVisaSOS() {
        return visaSOS;
    }

    public void setVisaSOS(Boolean visaSOS) {
        this.visaSOS = visaSOS;
    }

    public Biens getBiensIdBiens() {
        return biensIdBiens;
    }

    public void setBiensIdBiens(Biens biensIdBiens) {
        this.biensIdBiens = biensIdBiens;
    }

    public TypeStage getTypeStageIdtypeStage() {
        return typeStageIdtypeStage;
    }

    public void setTypeStageIdtypeStage(TypeStage typeStageIdtypeStage) {
        this.typeStageIdtypeStage = typeStageIdtypeStage;
    }

    public Utilisateur getUtilisateuridUtilisateur() {
        return utilisateuridUtilisateur;
    }

    public void setUtilisateuridUtilisateur(Utilisateur utilisateuridUtilisateur) {
        this.utilisateuridUtilisateur = utilisateuridUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsortieB1personnel != null ? idsortieB1personnel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SortieB1personnel)) {
            return false;
        }
        SortieB1personnel other = (SortieB1personnel) object;
        if ((this.idsortieB1personnel == null && other.idsortieB1personnel != null) || (this.idsortieB1personnel != null && !this.idsortieB1personnel.equals(other.idsortieB1personnel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.SortieB1personnel[ idsortieB1personnel=" + idsortieB1personnel + " ]";
    }
    
}
