package beans;

import javax.persistence.*;

/**
 * Created by Sylvain on 24/04/2017.
 */
@Entity
@Table(name = "participation", schema = "sr03_project", catalog = "")
@IdClass(ParticipationEntityPK.class)
public class ParticipationEntity {
    private int etu;
    private int cren;
    private EtudiantEntity etudiantByEtu;
    private CreneauEntity creneauByCren;

    @Id
    @Column(name = "Etu")
    public int getEtu() {
        return etu;
    }

    public void setEtu(int etu) {
        this.etu = etu;
    }

    @Id
    @Column(name = "Cren")
    public int getCren() {
        return cren;
    }

    public void setCren(int cren) {
        this.cren = cren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipationEntity that = (ParticipationEntity) o;

        if (etu != that.etu) return false;
        if (cren != that.cren) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = etu;
        result = 31 * result + cren;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Etu", referencedColumnName = "IdEtudiant", nullable = false)
    public EtudiantEntity getEtudiantByEtu() {
        return etudiantByEtu;
    }

    public void setEtudiantByEtu(EtudiantEntity etudiantByEtu) {
        this.etudiantByEtu = etudiantByEtu;
    }

    @ManyToOne
    @JoinColumn(name = "Cren", referencedColumnName = "IdCreneau", nullable = false)
    public CreneauEntity getCreneauByCren() {
        return creneauByCren;
    }

    public void setCreneauByCren(CreneauEntity creneauByCren) {
        this.creneauByCren = creneauByCren;
    }
}
