/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BAYA
 */
@Entity
@Table(name = "materielles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materielles.findAll", query = "SELECT m FROM Materielles m"),
    @NamedQuery(name = "Materielles.findByNumImm", query = "SELECT m FROM Materielles m WHERE m.numImm = :numImm"),
    @NamedQuery(name = "Materielles.findByNumSerie", query = "SELECT m FROM Materielles m WHERE m.numSerie = :numSerie"),
    @NamedQuery(name = "Materielles.findByMarque", query = "SELECT m FROM Materielles m WHERE m.marque = :marque")})
public class Materielles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_imm")
    private Integer numImm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "num_serie")
    private String numSerie;
    @Size(max = 45)
    @Column(name = "marque")
    private String marque;
    @JoinColumn(name = "biens_id_biens", referencedColumnName = "id_biens")
    @ManyToOne(optional = false)
    private Biens biensIdBiens;

    public Materielles() {
    }

    public Materielles(Integer numImm) {
        this.numImm = numImm;
    }

    public Materielles(Integer numImm, String numSerie) {
        this.numImm = numImm;
        this.numSerie = numSerie;
    }

    public Integer getNumImm() {
        return numImm;
    }

    public void setNumImm(Integer numImm) {
        this.numImm = numImm;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
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
        hash += (numImm != null ? numImm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materielles)) {
            return false;
        }
        Materielles other = (Materielles) object;
        if ((this.numImm == null && other.numImm != null) || (this.numImm != null && !this.numImm.equals(other.numImm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Materielles[ numImm=" + numImm + " ]";
    }
    
}
