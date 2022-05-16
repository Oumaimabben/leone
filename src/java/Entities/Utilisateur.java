/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;


import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BAYA
 */
@Entity
@Table(name = "utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.findByIdUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.idUtilisateur = :idUtilisateur"),
    @NamedQuery(name = "Utilisateur.findByEmail", query = "SELECT u FROM Utilisateur u WHERE u.email = :email"),
    @NamedQuery(name = "Utilisateur.findByService", query = "SELECT u FROM Utilisateur u WHERE u.serviceIdService.desigService = :service"),
    @NamedQuery(name = "Utilisateur.findByFonction", query = "SELECT u FROM Utilisateur u WHERE u.fonctionIdFct.desigFct = :fonction"),
    @NamedQuery(name = "Utilisateur.findByUsertype", query = "SELECT u FROM Utilisateur u WHERE u.usertype = :usertype"),
    @NamedQuery(name = "Utilisateur.findByUtilisateurNom", query = "SELECT u FROM Utilisateur u WHERE u.utilisateurNom = :utilisateurNom"),
    @NamedQuery(name = "Utilisateur.findByUtilisateurPrenom", query = "SELECT u FROM Utilisateur u WHERE u.utilisateurPrenom = :utilisateurPrenom")})
public class Utilisateur implements Serializable {
    @OneToMany(mappedBy = "utilisateuridUtilisateur")
    private List<SortieB1societe> sortieB1societeList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUtilisateur")
    private Integer idUtilisateur;
    @Size(max = 255)
    @Column(name = "Email")
    private String email;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "usertype")
    private String usertype;
    @Size(max = 255)
    @Column(name = "UtilisateurNom")
    private String utilisateurNom;
    @Size(max = 255)
    @Column(name = "UtilisateurPrenom")
    private String utilisateurPrenom;
    @JoinColumn(name = "service_id_service", referencedColumnName = "id_service")
    @ManyToOne(optional = false)
    private Service serviceIdService;
    @JoinColumn(name = "fonction_id_fct", referencedColumnName = "id_fct")
    @ManyToOne(optional = false)
    private Fonction fonctionIdFct;

    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }

    public String getUtilisateurPrenom() {
        return utilisateurPrenom;
    }

    public void setUtilisateurPrenom(String utilisateurPrenom) {
        this.utilisateurPrenom = utilisateurPrenom;
    }

    public Service getServiceIdService() {
        return serviceIdService;
    }

    public void setServiceIdService(Service serviceIdService) {
        this.serviceIdService = serviceIdService;
    }

    public Fonction getFonctionIdFct() {
        return fonctionIdFct;
    }

    public void setFonctionIdFct(Fonction fonctionIdFct) {
        this.fonctionIdFct = fonctionIdFct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idUtilisateur == null && other.idUtilisateur != null) || (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Utilisateur[ idUtilisateur=" + idUtilisateur + " ]";
    }

    @XmlTransient
    public List<SortieB1societe> getSortieB1societeList() {
        return sortieB1societeList;
    }

    public void setSortieB1societeList(List<SortieB1societe> sortieB1societeList) {
        this.sortieB1societeList = sortieB1societeList;
    }
    
}
