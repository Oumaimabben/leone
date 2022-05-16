

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
@Table(name = "fonction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fonction.findAll", query = "SELECT f FROM Fonction f"),
    @NamedQuery(name = "Fonction.findByIdFct", query = "SELECT f FROM Fonction f WHERE f.idFct = :idFct"),
    @NamedQuery(name = "Fonction.findByDesigFct", query = "SELECT f FROM Fonction f WHERE f.desigFct = :desigFct")})
public class Fonction implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fonctionIdFct")
    private List<Utilisateur> utilisateurList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fct")
    private Integer idFct;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "desig_fct")
    private String desigFct;

    public Fonction() {
    }

    public Fonction(Integer idFct) {
        this.idFct = idFct;
    }

    public Fonction(Integer idFct, String desigFct) {
        this.idFct = idFct;
        this.desigFct = desigFct;
    }

    public Integer getIdFct() {
        return idFct;
    }

    public void setIdFct(Integer idFct) {
        this.idFct = idFct;
    }

    public String getDesigFct() {
        return desigFct;
    }

    public void setDesigFct(String desigFct) {
        this.desigFct = desigFct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFct != null ? idFct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fonction)) {
            return false;
        }
        Fonction other = (Fonction) object;
        if ((this.idFct == null && other.idFct != null) || (this.idFct != null && !this.idFct.equals(other.idFct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return desigFct;
    }

    @XmlTransient
    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }
    
}
