package beans;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Sylvain on 24/04/2017.
 */
@Entity
@Table(name = "uv", schema = "sr03_project", catalog = "")
public class UvEntity {
    private String identifiant;
    private String description;
    private int responsable;
    private int idUv;
    private Collection<CreneauEntity> creneausByIdUv;
    private ProfesseurEntity professeurByResponsable;

    @Basic
    @Column(name = "Identifiant")
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "Responsable")
    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    @Id
    @Column(name = "IdUv")
    public int getIdUv() {
        return idUv;
    }

    public void setIdUv(int idUv) {
        this.idUv = idUv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UvEntity uvEntity = (UvEntity) o;

        if (responsable != uvEntity.responsable) return false;
        if (idUv != uvEntity.idUv) return false;
        if (identifiant != null ? !identifiant.equals(uvEntity.identifiant) : uvEntity.identifiant != null)
            return false;
        if (description != null ? !description.equals(uvEntity.description) : uvEntity.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = identifiant != null ? identifiant.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + responsable;
        result = 31 * result + idUv;
        return result;
    }

    @OneToMany(mappedBy = "uvByUv")
    public Collection<CreneauEntity> getCreneausByIdUv() {
        return creneausByIdUv;
    }

    public void setCreneausByIdUv(Collection<CreneauEntity> creneausByIdUv) {
        this.creneausByIdUv = creneausByIdUv;
    }

    @ManyToOne
    @JoinColumn(name = "Responsable", referencedColumnName = "IdProfesseur", nullable = false)
    public ProfesseurEntity getProfesseurByResponsable() {
        return professeurByResponsable;
    }

    public void setProfesseurByResponsable(ProfesseurEntity professeurByResponsable) {
        this.professeurByResponsable = professeurByResponsable;
    }
}
