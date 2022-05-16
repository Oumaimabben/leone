/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BAYA
 */
@Entity
@Table(name = "type_stage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeStage.findAll", query = "SELECT t FROM TypeStage t"),
    @NamedQuery(name = "TypeStage.findByIdtypeStage", query = "SELECT t FROM TypeStage t WHERE t.idtypeStage = :idtypeStage"),
    @NamedQuery(name = "TypeStage.findByTypeStage", query = "SELECT t FROM TypeStage t WHERE t.typeStage = :typeStage")})
public class TypeStage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtype_stage")
    private Integer idtypeStage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TypeStage")
    private String typeStage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeStageIdtypeStage")
    private List<SortieB1personnel> sortieB1personnelList;

    public TypeStage() {
    }

    public TypeStage(Integer idtypeStage) {
        this.idtypeStage = idtypeStage;
    }

    public TypeStage(Integer idtypeStage, String typeStage) {
        this.idtypeStage = idtypeStage;
        this.typeStage = typeStage;
    }

    public Integer getIdtypeStage() {
        return idtypeStage;
    }

    public void setIdtypeStage(Integer idtypeStage) {
        this.idtypeStage = idtypeStage;
    }

    public String getTypeStage() {
        return typeStage;
    }

    public void setTypeStage(String typeStage) {
        this.typeStage = typeStage;
    }

    @XmlTransient
    public List<SortieB1personnel> getSortieB1personnelList() {
        return sortieB1personnelList;
    }

    public void setSortieB1personnelList(List<SortieB1personnel> sortieB1personnelList) {
        this.sortieB1personnelList = sortieB1personnelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypeStage != null ? idtypeStage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeStage)) {
            return false;
        }
        TypeStage other = (TypeStage) object;
        if ((this.idtypeStage == null && other.idtypeStage != null) || (this.idtypeStage != null && !this.idtypeStage.equals(other.idtypeStage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return typeStage;
    }
    
}
