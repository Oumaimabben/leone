

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
@Table(name = "biens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Biens.findAll", query = "SELECT b FROM Biens b"),
    @NamedQuery(name = "Biens.findByIdBiens", query = "SELECT b FROM Biens b WHERE b.idBiens = :idBiens"),
    @NamedQuery(name = "Biens.findByNatureBien", query = "SELECT b FROM Biens b WHERE b.natureBien = :natureBien")})
public class Biens implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_biens")
    private Integer idBiens;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nature_bien")
    private String natureBien;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "biensIdBiens")
    private List<Rebut> rebutList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "biensIdBiens")
    private List<Transfert> transfertList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "biensIdBiens")
    private List<SortieB1societe> sortieB1societeList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "biensIdBiens")
    private List<Materielles> materiellesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "biensIdBiens")
    private List<SortieB1personnel> sortieB1personnelList;

    public Biens() {
    }

    public Biens(Integer idBiens) {
        this.idBiens = idBiens;
    }

    public Biens(Integer idBiens, String natureBien) {
        this.idBiens = idBiens;
        this.natureBien = natureBien;
    }

    public Integer getIdBiens() {
        return idBiens;
    }

    public void setIdBiens(Integer idBiens) {
        this.idBiens = idBiens;
    }

    public String getNatureBien() {
        return natureBien;
    }

    public void setNatureBien(String natureBien) {
        this.natureBien = natureBien;
    }

    @XmlTransient
    public List<Rebut> getRebutList() {
        return rebutList;
    }

    public void setRebutList(List<Rebut> rebutList) {
        this.rebutList = rebutList;
    }

    @XmlTransient
    public List<Transfert> getTransfertList() {
        return transfertList;
    }

    public void setTransfertList(List<Transfert> transfertList) {
        this.transfertList = transfertList;
    }

    @XmlTransient
    public List<SortieB1societe> getSortieB1societeList() {
        return sortieB1societeList;
    }

    public void setSortieB1societeList(List<SortieB1societe> sortieB1societeList) {
        this.sortieB1societeList = sortieB1societeList;
    }

    @XmlTransient
    public List<Materielles> getMateriellesList() {
        return materiellesList;
    }

    public void setMateriellesList(List<Materielles> materiellesList) {
        this.materiellesList = materiellesList;
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
        hash += (idBiens != null ? idBiens.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Biens)) {
            return false;
        }
        Biens other = (Biens) object;
        if ((this.idBiens == null && other.idBiens != null) || (this.idBiens != null && !this.idBiens.equals(other.idBiens))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return natureBien ;
    }
    
}
