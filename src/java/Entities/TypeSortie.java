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
@Table(name = "type_sortie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeSortie.findAll", query = "SELECT t FROM TypeSortie t"),
    @NamedQuery(name = "TypeSortie.findByIdTypeSortie", query = "SELECT t FROM TypeSortie t WHERE t.idTypeSortie = :idTypeSortie"),
    @NamedQuery(name = "TypeSortie.findByTypeSortie", query = "SELECT t FROM TypeSortie t WHERE t.typeSortie = :typeSortie")})
public class TypeSortie implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeSortieidTypeSortie")
    private List<SortieB1societe> sortieB1societeList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idType_Sortie")
    private Integer idTypeSortie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TypeSortie")
    private String typeSortie;
    

    public TypeSortie() {
    }

    public TypeSortie(Integer idTypeSortie) {
        this.idTypeSortie = idTypeSortie;
    }

    public TypeSortie(Integer idTypeSortie, String typeSortie) {
        this.idTypeSortie = idTypeSortie;
        this.typeSortie = typeSortie;
    }
    

    public Integer getIdTypeSortie() {
        return idTypeSortie;
    }

    public void setIdTypeSortie(Integer idTypeSortie) {
        this.idTypeSortie = idTypeSortie;
    }

    public String getTypeSortie() {
        return typeSortie;
    }

    public void setTypeSortie(String typeSortie) {
        this.typeSortie = typeSortie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeSortie != null ? idTypeSortie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeSortie)) {
            return false;
        }
        TypeSortie other = (TypeSortie) object;
        if ((this.idTypeSortie == null && other.idTypeSortie != null) || (this.idTypeSortie != null && !this.idTypeSortie.equals(other.idTypeSortie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return typeSortie;
    }

    @XmlTransient
    public List<SortieB1societe> getSortieB1societeList() {
        return sortieB1societeList;
    }

    public void setSortieB1societeList(List<SortieB1societe> sortieB1societeList) {
        this.sortieB1societeList = sortieB1societeList;
    }
    
}
