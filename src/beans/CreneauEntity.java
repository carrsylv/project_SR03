package beans;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Sylvain on 24/04/2017.
 */
@Entity
@Table(name = "creneau", schema = "sr03_project", catalog = "")
public class CreneauEntity {
    private String salle;
    private String type;
    private String alternance;
    private int uv;
    private int horraire;
    private String groupe;
    private int idCreneau;
    private UvEntity uvByUv;
    private HorraireEntity horraireByHorraire;
    private Collection<ParticipationEntity> participationsByIdCreneau;

    @Basic
    @Column(name = "Salle")
    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    @Basic
    @Column(name = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "Alternance")
    public String getAlternance() {
        return alternance;
    }

    public void setAlternance(String alternance) {
        this.alternance = alternance;
    }

    @Basic
    @Column(name = "Uv")
    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    @Basic
    @Column(name = "Horraire")
    public int getHorraire() {
        return horraire;
    }

    public void setHorraire(int horraire) {
        this.horraire = horraire;
    }

    @Basic
    @Column(name = "Groupe")
    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    @Id
    @Column(name = "IdCreneau")
    public int getIdCreneau() {
        return idCreneau;
    }

    public void setIdCreneau(int idCreneau) {
        this.idCreneau = idCreneau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreneauEntity that = (CreneauEntity) o;

        if (uv != that.uv) return false;
        if (horraire != that.horraire) return false;
        if (idCreneau != that.idCreneau) return false;
        if (salle != null ? !salle.equals(that.salle) : that.salle != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (alternance != null ? !alternance.equals(that.alternance) : that.alternance != null) return false;
        if (groupe != null ? !groupe.equals(that.groupe) : that.groupe != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = salle != null ? salle.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (alternance != null ? alternance.hashCode() : 0);
        result = 31 * result + uv;
        result = 31 * result + horraire;
        result = 31 * result + (groupe != null ? groupe.hashCode() : 0);
        result = 31 * result + idCreneau;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Uv", referencedColumnName = "IdUv", nullable = false)
    public UvEntity getUvByUv() {
        return uvByUv;
    }

    public void setUvByUv(UvEntity uvByUv) {
        this.uvByUv = uvByUv;
    }

    @ManyToOne
    @JoinColumn(name = "Horraire", referencedColumnName = "IdHorraire", nullable = false)
    public HorraireEntity getHorraireByHorraire() {
        return horraireByHorraire;
    }

    public void setHorraireByHorraire(HorraireEntity horraireByHorraire) {
        this.horraireByHorraire = horraireByHorraire;
    }

    @OneToMany(mappedBy = "creneauByCren")
    public Collection<ParticipationEntity> getParticipationsByIdCreneau() {
        return participationsByIdCreneau;
    }

    public void setParticipationsByIdCreneau(Collection<ParticipationEntity> participationsByIdCreneau) {
        this.participationsByIdCreneau = participationsByIdCreneau;
    }
}
