package beans;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

/**
 * Created by Sylvain on 24/04/2017.
 */
@Entity
@Table(name = "horraire", schema = "sr03_project", catalog = "")
public class HorraireEntity {
    private Time heureDébut;
    private Time heureFin;
    private String jourSemaine;
    private int idHorraire;
    private Collection<CreneauEntity> creneausByIdHorraire;

    @Basic
    @Column(name = "Heure_début")
    public Time getHeureDébut() {
        return heureDébut;
    }

    public void setHeureDébut(Time heureDébut) {
        this.heureDébut = heureDébut;
    }

    @Basic
    @Column(name = "Heure_fin")
    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    @Basic
    @Column(name = "Jour_semaine")
    public String getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(String jourSemaine) {
        this.jourSemaine = jourSemaine;
    }

    @Id
    @Column(name = "IdHorraire")
    public int getIdHorraire() {
        return idHorraire;
    }

    public void setIdHorraire(int idHorraire) {
        this.idHorraire = idHorraire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HorraireEntity that = (HorraireEntity) o;

        if (idHorraire != that.idHorraire) return false;
        if (heureDébut != null ? !heureDébut.equals(that.heureDébut) : that.heureDébut != null) return false;
        if (heureFin != null ? !heureFin.equals(that.heureFin) : that.heureFin != null) return false;
        if (jourSemaine != null ? !jourSemaine.equals(that.jourSemaine) : that.jourSemaine != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = heureDébut != null ? heureDébut.hashCode() : 0;
        result = 31 * result + (heureFin != null ? heureFin.hashCode() : 0);
        result = 31 * result + (jourSemaine != null ? jourSemaine.hashCode() : 0);
        result = 31 * result + idHorraire;
        return result;
    }

    @OneToMany(mappedBy = "horraireByHorraire")
    public Collection<CreneauEntity> getCreneausByIdHorraire() {
        return creneausByIdHorraire;
    }

    public void setCreneausByIdHorraire(Collection<CreneauEntity> creneausByIdHorraire) {
        this.creneausByIdHorraire = creneausByIdHorraire;
    }
}
